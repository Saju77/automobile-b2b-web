package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.broadleafcommerce.openadmin.server.security.domain.AdminSection;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@Component("org.technohaven.api.wrapper.AdminSectionsWrapper")
@Scope("prototype")
public class AdminSectionsWrapper extends BaseWrapper implements APIWrapper<List<AdminSection>> {

    @XmlElement(name = "adminSection")
    protected List<AdminSectionWrapper> adminSections = new ArrayList<AdminSectionWrapper>();

    public List<AdminSectionWrapper> getAdminSections() {
        return adminSections;
    }

    public void setAdminSections(List<AdminSectionWrapper> adminSections) {
        this.adminSections = adminSections;
    }

    @Override
    public void wrapDetails(List<AdminSection> models, HttpServletRequest request) {
        for (AdminSection adminSection : models) {
            AdminSectionWrapper wrapper = (AdminSectionWrapper) context.getBean(AdminSectionWrapper.class.getName());
            wrapper.wrapSummary(adminSection, request);
            adminSections.add(wrapper);
        }
    }

    @Override
    public void wrapSummary(List<AdminSection> model, HttpServletRequest request) {

    }
}
