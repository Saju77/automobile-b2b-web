package org.technohaven.admin.services;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.exception.ServiceException;
import org.broadleafcommerce.openadmin.dto.Entity;
import org.broadleafcommerce.openadmin.dto.PersistencePackage;
import org.broadleafcommerce.openadmin.dto.Property;
import org.broadleafcommerce.openadmin.dto.SectionCrumb;
import org.broadleafcommerce.openadmin.server.domain.PersistencePackageRequest;
import org.broadleafcommerce.openadmin.server.factory.PersistencePackageFactory;
import org.broadleafcommerce.openadmin.server.service.DynamicEntityService;
import org.broadleafcommerce.openadmin.server.service.ValidationException;
import org.broadleafcommerce.openadmin.server.service.persistence.PersistenceResponse;
import org.broadleafcommerce.openadmin.web.form.entity.DynamicEntityFormInfo;
import org.broadleafcommerce.openadmin.web.form.entity.EntityForm;
import org.broadleafcommerce.openadmin.web.form.entity.Field;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.technohaven.core.dao.DistrictDao;
import org.technohaven.core.entities.District;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

@Service("blDistrictService")
public class DistrictServiceImpl implements DistrictService {

	protected static final Log LOG = LogFactory.getLog(DistrictServiceImpl.class);

    @Resource(name = "blDynamicEntityRemoteService")
    protected DynamicEntityService service;
    
    @Resource(name = "blPersistencePackageFactory")
    protected PersistencePackageFactory persistencePackageFactory;

	@Resource(name="blDistrictDao")
	protected DistrictDao districtDao;
	
	@Override
    @Transactional("blTransactionManager")
    public District save(District district) {
        return districtDao.save(district);
    }
    
	@Override
    public void getString(String value) {
    	System.out.println("The value is "+value);
    }
	
	protected List<Property> getPropertiesFromEntityForm(EntityForm entityForm) {
        List<Property> properties = new ArrayList<Property>(entityForm.getFields().size());

        for (Entry<String, Field> entry : entityForm.getFields().entrySet()) {
            Property p = new Property();
            p.setName(entry.getKey());
            p.setValue(entry.getValue().getValue());
            p.setDisplayValue(entry.getValue().getDisplayValue());
            p.setIsDirty(entry.getValue().getIsDirty());
            properties.add(p);
        }

        return properties;
    }
	
	/**
     * <p>
     * Should be invoked when a {@link ValidationException} is thrown to verify that the {@link Entity} contained within the
     * given <b>originalRequest</b> has a validationFailure = true
     *
     * <p>
     * This will also check for a cause of {@link ConstraintViolationException} and add a gloal error to that.
     */
    protected void ensureEntityMarkedAsValidationFailure(ValidationException e, PersistencePackageRequest originalRequest) {
        if (e.containsCause(ConstraintViolationException.class)) {
            e.getEntity().addGlobalValidationError("constraintViolationError");
        } else if (!e.getEntity().isValidationFailure()) {
            e.getEntity().setValidationFailure(true);
            e.getEntity().addGlobalValidationError(e.getMessage());
        }
    }
	
	@Override
    public PersistencePackageRequest getRequestForEntityForm(EntityForm entityForm, String[] customCriteria, List<SectionCrumb> sectionCrumbs) {
        // Ensure the ID property is on the form
        Field idField = entityForm.findField(entityForm.getIdProperty());
        if (idField == null) {
            idField = new Field();
            idField.setName(entityForm.getIdProperty());
            idField.setValue(entityForm.getId());
            entityForm.getFields().put(entityForm.getIdProperty(), idField);
        } else {
            idField.setValue(entityForm.getId());
        }

        List<Property> propList = getPropertiesFromEntityForm(entityForm);
        Property[] properties = new Property[propList.size()];
        properties = propList.toArray(properties);

        Entity entity = new Entity();
        entity.setProperties(properties);
        String entityType = entityForm.getEntityType();
        if (StringUtils.isEmpty(entityType)) {
            entityType = entityForm.getCeilingEntityClassname();
        }
        entity.setType(new String[]{entityType});

        PersistencePackageRequest ppr = PersistencePackageRequest.standard()
                .withEntity(entity)
                .withCustomCriteria(customCriteria)
                .withCeilingEntityClassname(entityForm.getCeilingEntityClassname())
                .withSectionCrumbs(sectionCrumbs)
                .withRequestingEntityName(entityForm.getMainEntityName());
        return ppr;
    }
	
	@Override
    public PersistenceResponse add(PersistencePackageRequest request, boolean transactional) throws ServiceException {
        PersistencePackage pkg = persistencePackageFactory.create(request);
        try {
            if (request.isUpdateLookupType()) {
                if (pkg.getSectionCrumbs() != null && pkg.getSectionCrumbs().length > 0) {
                    SectionCrumb sc = pkg.getSectionCrumbs()[0];
                    if (StringUtils.isNotBlank(sc.getSectionIdentifier())) {
                        pkg.setSecurityCeilingEntityFullyQualifiedClassname(sc.getSectionIdentifier());
                    }
                }
                if (transactional) {
                    return service.update(pkg);
                } else {
                    return service.nonTransactionalUpdate(pkg);
                }
            } else {
                if (transactional) {
                    return service.add(pkg);
                } else {
                    return service.nonTransactionalAdd(pkg);
                }
            }
        } catch (ValidationException e) {
            ensureEntityMarkedAsValidationFailure(e, request);
            return new PersistenceResponse().withEntity(e.getEntity());
        }
    }
	
	@Override
    public PersistenceResponse add(PersistencePackageRequest request) throws ServiceException {
        return add(request, true);
    }
	
	@Override
	public PersistenceResponse addEntity(EntityForm entityForm, String[] customCriteria, List<SectionCrumb> sectionCrumb) throws ServiceException {
		System.out.println("Print from DistrictServiceImpl.addEntity() method.");
        PersistencePackageRequest ppr = getRequestForEntityForm(entityForm, customCriteria, sectionCrumb);
        // If the entity form has dynamic forms inside of it, we need to persist those as well.
        // They are typically done in their own custom persistence handlers, which will get triggered
        // based on the criteria specific in the PersistencePackage.
        for (Entry<String, EntityForm> entry : entityForm.getDynamicForms().entrySet()) {
            DynamicEntityFormInfo info = entityForm.getDynamicFormInfo(entry.getKey());

            if (info.getCustomCriteriaOverride() != null) {
                customCriteria = info.getCustomCriteriaOverride();
            } else {
                String propertyName = info.getPropertyName();
                String propertyValue;
                if (entityForm.getFields().containsKey(propertyName)) {
                    propertyValue = entityForm.findField(propertyName).getValue();
                } else {
                    propertyValue = info.getPropertyValue();
                }
                customCriteria = new String[] {info.getCriteriaName(), entityForm.getId(), propertyName, propertyValue};
            }

            PersistencePackageRequest subRequest = getRequestForEntityForm(entry.getValue(), customCriteria, sectionCrumb);
            ppr.addSubRequest(info.getPropertyName(), subRequest);
        }
        return add(ppr);
    }

    @Override
    public List<District> getDistricts() {
        return districtDao.getDistricts();
    }
}
