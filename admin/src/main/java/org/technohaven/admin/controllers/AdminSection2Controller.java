package org.technohaven.admin.controllers;

import org.broadleafcommerce.openadmin.dto.ClassMetadata;
import org.broadleafcommerce.openadmin.dto.Entity;
import org.broadleafcommerce.openadmin.dto.SectionCrumb;
import org.broadleafcommerce.openadmin.server.domain.PersistencePackageRequest;
import org.broadleafcommerce.openadmin.server.security.domain.AdminModuleImpl;
import org.broadleafcommerce.openadmin.web.controller.entity.AdminBasicEntityController;
import org.broadleafcommerce.openadmin.web.controller.modal.ModalHeaderType;
import org.broadleafcommerce.openadmin.web.form.entity.EntityForm;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/" + AdminSection2Controller.SECTION_KEY)
@Secured("ROLE_PERMISSION_OTHER_DEFAULT")
public class AdminSection2Controller extends AdminBasicEntityController {

    protected static final String SECTION_KEY = "adminSection";

    @Override
    protected String getSectionKey(Map<String, String> pathVars) {
        //allow external links to work for ToOne items
        if (super.getSectionKey(pathVars) != null) {
            return super.getSectionKey(pathVars);
        }
        return SECTION_KEY;
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
            @PathVariable Map<String, String> pathVars,
            @ModelAttribute(value="entityForm") EntityForm entityForm, BindingResult result) throws Exception {

        String sectionKey = getSectionKey(pathVars);
        String sectionClassName = getClassNameForSection(sectionKey);
        List<SectionCrumb> sectionCrumbs = getSectionCrumbs(request, null, null);

        String adminModuleId = entityForm.findField("module").getValue();
        System.out.println("adminModuleId = "+adminModuleId);
        entityForm.findField("module").setValue(adminModuleId);

        ClassMetadata cmd = service.getClassMetadata(getSectionPersistencePackageRequest(sectionClassName, sectionCrumbs, pathVars)).getDynamicResultSet().getClassMetaData();

        extractDynamicFormFields(cmd, entityForm);
        String[] sectionCriteria = customCriteriaService.mergeSectionCustomCriteria(sectionClassName, getSectionCustomCriteria());
        Entity entity = service.addEntity(entityForm, sectionCriteria, sectionCrumbs).getEntity();
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

}
