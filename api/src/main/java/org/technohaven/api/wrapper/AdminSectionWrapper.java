package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIUnwrapper;
import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.broadleafcommerce.openadmin.server.security.domain.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.api.services.AdminModuleService;
import org.technohaven.api.services.AdminSectionService;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Component("org.technohaven.api.wrapper.AdminSectionWrapper")
@Scope("prototype")
@XmlRootElement(name = "adminSection")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AdminSectionWrapper extends BaseWrapper implements APIWrapper<AdminSection>, APIUnwrapper<AdminSection> {

    @XmlElement
    protected Long id;

    @XmlElement
    protected String name;

    @XmlElement
    protected String sectionKey;

    @XmlElement
    protected String url;

    @XmlElement
    protected long moduleId;

    @XmlElement
    protected String displayController;

    @XmlElement
    protected Boolean useDefaultHandler;

    @XmlElement
    protected String ceilingEntity;

    @XmlElement
    protected Integer displayOrder;

    @XmlElement
    protected Boolean folderable;

    @XmlElement
    protected Boolean folderedByDefault;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSectionKey() {
        return sectionKey;
    }

    public void setSectionKey(String sectionKey) {
        this.sectionKey = sectionKey;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public long getModuleId() {
        return moduleId;
    }

    public void setModuleId(long moduleId) {
        this.moduleId = moduleId;
    }

    public String getDisplayController() {
        return displayController;
    }

    public void setDisplayController(String displayController) {
        this.displayController = displayController;
    }

    public Boolean getUseDefaultHandler() {
        return useDefaultHandler;
    }

    public void setUseDefaultHandler(Boolean useDefaultHandler) {
        this.useDefaultHandler = useDefaultHandler;
    }

    public String getCeilingEntity() {
        return ceilingEntity;
    }

    public void setCeilingEntity(String ceilingEntity) {
        this.ceilingEntity = ceilingEntity;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public Boolean getFolderable() {
        return folderable;
    }

    public void setFolderable(Boolean folderable) {
        this.folderable = folderable;
    }

    public Boolean getFolderedByDefault() {
        return folderedByDefault;
    }

    public void setFolderedByDefault(Boolean folderedByDefault) {
        this.folderedByDefault = folderedByDefault;
    }

    @Override
    public AdminSection unwrap(HttpServletRequest request, ApplicationContext context) {
        AdminSectionService adminSectionService = (AdminSectionService) context.getBean("blAdminSectionService");
        AdminSection adminSection = adminSectionService.createAdminSectionFromId(this.id);
        adminSection.setName(this.name);
        adminSection.setSectionKey(this.sectionKey);
        adminSection.setUrl(this.url);

        AdminModuleService adminModuleService = (AdminModuleService) context.getBean("blAdminModuleService");
        AdminModule adminModule = adminModuleService.findAdminModuleById(this.moduleId);
        adminSection.setModule(adminModule);

        adminSection.setDisplayController(this.displayController);
        adminSection.setUseDefaultHandler(this.useDefaultHandler);
        adminSection.setCeilingEntity(this.ceilingEntity);
        adminSection.setDisplayOrder(this.displayOrder);
        adminSection.setFolderable(this.folderable);
        adminSection.setFolderedByDefault(this.folderedByDefault);
        return adminSection;
    }

    @Override
    public void wrapDetails(AdminSection adminSection, HttpServletRequest request) {
        this.id = adminSection.getId();
        this.name = adminSection.getName();
        this.sectionKey = adminSection.getSectionKey();
        this.url = adminSection.getUrl();
        this.moduleId = adminSection.getModule().getId();
        this.displayController = adminSection.getDisplayController();
        this.useDefaultHandler = adminSection.getUseDefaultHandler();
        this.ceilingEntity = adminSection.getCeilingEntity();
        this.displayOrder = adminSection.getDisplayOrder();
        this.folderable = adminSection.isFolderable();
        this.folderedByDefault = adminSection.isFolderedByDefault();

    }

    @Override
    public void wrapSummary(AdminSection model, HttpServletRequest request) {
        wrapDetails(model, request);
    }
}
