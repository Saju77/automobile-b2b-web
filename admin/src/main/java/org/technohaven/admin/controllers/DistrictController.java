package org.technohaven.admin.controllers;

import org.apache.commons.collections.CollectionUtils;
import org.broadleafcommerce.openadmin.dto.ClassMetadata;
import org.broadleafcommerce.openadmin.dto.DynamicResultSet;
import org.broadleafcommerce.openadmin.dto.Entity;
import org.broadleafcommerce.openadmin.dto.SectionCrumb;
import org.broadleafcommerce.openadmin.server.domain.PersistencePackageRequest;
import org.broadleafcommerce.openadmin.web.controller.entity.AdminBasicEntityController;
import org.broadleafcommerce.openadmin.web.controller.modal.ModalHeaderType;
import org.broadleafcommerce.openadmin.web.form.component.ListGrid;
import org.broadleafcommerce.openadmin.web.form.entity.EntityForm;
import org.broadleafcommerce.openadmin.web.form.entity.Field;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UrlPathHelper;
import org.technohaven.admin.services.DistrictService;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping("/" + DistrictController.SECTION_KEY)
@Secured("ROLE_PERMISSION_OTHER_DEFAULT")
public class DistrictController extends AdminBasicEntityController {

    protected static final String SECTION_KEY = "district";
    
    @Resource(name = "blDistrictService")
    protected DistrictService districtService;

    @Override
    protected String getSectionKey(Map<String, String> pathVars) {
        //allow external links to work for ToOne items
        if (super.getSectionKey(pathVars) != null) {
            return super.getSectionKey(pathVars);
        }
        return SECTION_KEY;
    }
    
    @RequestMapping(value = "/getPrint", method = RequestMethod.GET)
    public String printString(HttpServletRequest request, Model model) {
    	
    	districtService.getString("The getPrint method and The section key is "+SECTION_KEY);
    	
    	// This is expected by the modules/emptyContainer template, this is a custom template that gets included into the body
        model.addAttribute("customView", "views/district");

        // ensure navigation gets set up correctly
        setModelAttributes(model, SECTION_KEY);

        // gets the scaffolding set up to display the template from the customView attribute above
        return "modules/emptyContainer";
    }
    
    @RequestMapping(value = "/save", method = RequestMethod.GET)
    public String saveDistrict(HttpServletRequest request, HttpServletResponse response, Model model,
            @PathVariable  Map<String, String> pathVars,
            @RequestParam(defaultValue = "") String entityType) throws Exception {
    	
    	districtService.getString("The saveDistrict method(get) and The section key is "+SECTION_KEY);
    	
    	// This is expected by the modules/emptyContainer template, this is a custom template that gets included into the body
        model.addAttribute("customView", "views/district");

        // ensure navigation gets set up correctly
        setModelAttributes(model, SECTION_KEY);

        // gets the scaffolding set up to display the template from the customView attribute above
        return "modules/emptyContainer";
    }

    @Override
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String viewEntityList(HttpServletRequest request, HttpServletResponse response, Model model,
            @PathVariable Map<String, String> pathVars,
            @RequestParam MultiValueMap<String, String> requestParams) throws Exception {

        String sectionKey = getSectionKey(pathVars);
        String sectionClassName = getClassNameForSection(sectionKey);
        List<SectionCrumb> crumbs = getSectionCrumbs(request, null, null);
        PersistencePackageRequest ppr = getSectionPersistencePackageRequest(sectionClassName, requestParams, crumbs, pathVars);
        ClassMetadata cmd = service.getClassMetadata(ppr).getDynamicResultSet().getClassMetaData();
        DynamicResultSet drs = service.getRecords(ppr).getDynamicResultSet();

        ListGrid listGrid = formService.buildMainListGrid(drs, cmd, sectionKey, crumbs);
        listGrid.setSelectType(ListGrid.SelectType.NONE);

        Set<Field> headerFields = listGrid.getHeaderFields();
        if (CollectionUtils.isNotEmpty(headerFields)) {
            Field firstField = headerFields.iterator().next();
            if (requestParams.containsKey(firstField.getName())) {
                model.addAttribute("mainSearchTerm", requestParams.get(firstField.getName()).get(0));
            }
        }

        model.addAttribute("viewType", "entityList");

        setupViewEntityListBasicModel(request, cmd, sectionKey, sectionClassName, model, requestParams);
        model.addAttribute("listGrid", listGrid);

        return DEFAULT_CONTAINER_VIEW;
    }

    @Override
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String viewAddEntityForm(HttpServletRequest request, HttpServletResponse response, Model model,
            @PathVariable  Map<String, String> pathVars,
            @RequestParam(defaultValue = "") String entityType) throws Exception {

        String sectionKey = getSectionKey(pathVars);
        String sectionClassName = getClassNameForSection(sectionKey);
        List<SectionCrumb> sectionCrumbs = getSectionCrumbs(request, null, null);
        PersistencePackageRequest ppr = getSectionPersistencePackageRequest(sectionClassName, sectionCrumbs, pathVars);
        ppr.setAddOperationInspect(true);
        ClassMetadata cmd = service.getClassMetadata(ppr).getDynamicResultSet().getClassMetaData();
        
        entityType = determineEntityType(entityType, cmd);

        EntityForm entityForm = formService.createEntityForm(cmd, sectionCrumbs);

        // We need to make sure that the ceiling entity is set to the interface and the specific entity type
        // is set to the type we're going to be creating.
        entityForm.setCeilingEntityClassname(cmd.getCeilingType());
        entityForm.setEntityType(entityType);

        // When we initially build the class metadata (and thus, the entity form), we had all of the possible
        // polymorphic fields built out. Now that we have a concrete entity type to render, we can remove the
        // fields that are not applicable for this given entity type.
        formService.removeNonApplicableFields(cmd, entityForm, entityType);

        modifyAddEntityForm(entityForm, pathVars);

        model.addAttribute("entityForm", entityForm);
        model.addAttribute("viewType", "modal/entityAdd");

        model.addAttribute("entityFriendlyName", cmd.getPolymorphicEntities().getFriendlyName());
        model.addAttribute("currentUrl", request.getRequestURL().toString());
        model.addAttribute("modalHeaderType", ModalHeaderType.ADD_ENTITY.getType());
        setModelAttributes(model, sectionKey);
        return MODAL_CONTAINER_VIEW;
    }
    
    @Override
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public String addEntity(HttpServletRequest request, HttpServletResponse response, Model model,
            @PathVariable  Map<String, String> pathVars,
            @ModelAttribute(value="entityForm") EntityForm entityForm, BindingResult result) throws Exception {

    	districtService.getString("The save method and The section key is "+SECTION_KEY);
    	
        String sectionKey = getSectionKey(pathVars);
    	String sectionClassName = getClassNameForSection(sectionKey);
        List<SectionCrumb> sectionCrumbs = getSectionCrumbs(request, null, null);
        ClassMetadata cmd = service.getClassMetadata(getSectionPersistencePackageRequest(sectionClassName, sectionCrumbs, pathVars)).getDynamicResultSet().getClassMetaData();

        extractDynamicFormFields(cmd, entityForm);
        String[] sectionCriteria = customCriteriaService.mergeSectionCustomCriteria(sectionClassName, getSectionCustomCriteria());
//        Entity entity = service.addEntity(entityForm, sectionCriteria, sectionCrumbs).getEntity();
        Entity entity = districtService.addEntity(entityForm, sectionCriteria, sectionCrumbs).getEntity();
        entityFormValidator.validate(entityForm, entity, result);

        if (result.hasErrors()) {
            entityForm.clearFieldsMap();
            formService.populateEntityForm(cmd, entity, entityForm, sectionCrumbs);

            formService.removeNonApplicableFields(cmd, entityForm, entityForm.getEntityType());

            modifyAddEntityForm(entityForm, pathVars);

            model.addAttribute("viewType", "modal/entityAdd");
            model.addAttribute("currentUrl", request.getRequestURL().toString());
            model.addAttribute("modalHeaderType", ModalHeaderType.ADD_ENTITY.getType());
            model.addAttribute("entityFriendlyName", cmd.getPolymorphicEntities().getFriendlyName());
            setModelAttributes(model, sectionKey);
            return MODAL_CONTAINER_VIEW;
        }
        // Note that AJAX Redirects need the context path prepended to them
        return "ajaxredirect:" + getContextPath(request) + sectionKey + "/" + entity.getPMap().get("id").getValue();
    }


    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public String viewEntityForm(HttpServletRequest request, HttpServletResponse response, Model model,
            @PathVariable  Map<String, String> pathVars,
            @PathVariable("id") String id) throws Exception {

        String sectionKey = getSectionKey(pathVars);
        String sectionClassName = getClassNameForSection(sectionKey);
        List<SectionCrumb> crumbs = getSectionCrumbs(request, sectionKey, id);
        PersistencePackageRequest ppr = getSectionPersistencePackageRequest(sectionClassName, crumbs, pathVars);

        ClassMetadata cmd = service.getClassMetadata(ppr).getDynamicResultSet().getClassMetaData();
        Entity entity = service.getRecord(ppr, id, cmd, false).getDynamicResultSet().getRecords()[0];

        multipleCatalogExtensionManager.getProxy().setCurrentCatalog(entity, model);

        Map<String, DynamicResultSet> subRecordsMap = getViewSubRecords(request, pathVars, cmd, entity, crumbs);

        EntityForm entityForm = formService.createEntityForm(cmd, entity, subRecordsMap, crumbs);

        modifyEntityForm(entity, entityForm, pathVars);

        if (request.getParameter("headerFlash") != null) {
            model.addAttribute("headerFlash", request.getParameter("headerFlash"));
        }

        // Set the sectionKey again incase this is a typed entity
        entityForm.setSectionKey(sectionKey);

        // Build the current url in the cast that this is a typed entity
        String originatingUri = new UrlPathHelper().getOriginatingRequestUri(request);
        int startIndex = request.getContextPath().length();

        // Remove the context path from servlet path
        String currentUrl = originatingUri.substring(startIndex);

        model.addAttribute("entity", entity);
        model.addAttribute("entityForm", entityForm);
        model.addAttribute("currentUrl", currentUrl);
        model.addAttribute(CURRENT_FOLDER_ID, getCurrentFolderId(request));

        setModelAttributes(model, sectionKey);

        // We want to replace author ids with their names
        addAuditableDisplayFields(entityForm);

        return resolveAppropriateEntityView(request, model, entityForm);
    }

}
