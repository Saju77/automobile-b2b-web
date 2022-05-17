package org.technohaven.admin.services;

import java.util.List;

import org.broadleafcommerce.common.exception.ServiceException;
import org.broadleafcommerce.openadmin.dto.Entity;
import org.broadleafcommerce.openadmin.dto.SectionCrumb;
import org.broadleafcommerce.openadmin.server.domain.PersistencePackageRequest;
import org.broadleafcommerce.openadmin.server.service.DynamicEntityRemoteService;
import org.broadleafcommerce.openadmin.server.service.DynamicEntityService;
import org.broadleafcommerce.openadmin.server.service.ValidationException;
import org.broadleafcommerce.openadmin.server.service.persistence.PersistenceResponse;
import org.broadleafcommerce.openadmin.web.form.entity.EntityForm;
import org.technohaven.core.entities.District;

public interface DistrictService {

	District save(District district);

	void getString(String str);
	
	/**
     * Works the same as {@link #add(PersistencePackageRequest)} but you can optionally invoke the transactional version
     * of {@link DynamicEntityRemoteService#add(org.broadleafcommerce.openadmin.dto.PersistencePackage)} in situations
     * where you want to manage the transactions in a parent component
     * 
     * @param request
     * @param transactional
     * @return
     * @throws ServiceException
     */
    public PersistenceResponse add(PersistencePackageRequest request, boolean transactional) throws ServiceException;
	
	/**
     * Gets the PersistencePackageRequest for the passed in EntityForm
     *
     * @param entityForm
     * @param customCriteria
     * @param sectionCrumbs
     */
    public PersistencePackageRequest getRequestForEntityForm(EntityForm entityForm, String[] customCriteria, List<SectionCrumb> sectionCrumbs);
	
    /**
     * Thin layer on top of {@link DynamicEntityService#add(org.broadleafcommerce.openadmin.dto.PersistencePackage)} that
     * swallows all {@link ValidationException}s that could be thrown and still just returns a {@link PersistenceResponse}
     * with the {@link Entity} that failed validation.
     *
     * @param request
     * @return
     * @throws ServiceException if there were exceptions other than a {@link ValidationException} that was thrown as a
     * result of the attempted add
     */
    public PersistenceResponse add(PersistencePackageRequest request) throws ServiceException;
    
    
	/**
     * Persists the given entity
     * 
     * @param entityForm
     * @param customCriteria
     * @return the persisted Entity
     * @throws ServiceException
     */
    public PersistenceResponse addEntity(EntityForm entityForm, String[] customCriteria, List<SectionCrumb> sectionCrumb)
            throws ServiceException;

    List<District> getDistricts();
	
}
