package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.core.entities.AdminSecPerm;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@Component("org.technohaven.api.wrapper.AdminSecPermsWrapper")
@Scope("prototype")
public class AdminSecPermsWrapper extends BaseWrapper implements APIWrapper<List<AdminSecPerm>> {

    @XmlElement(name = "adminSecPerms")
    protected List<AdminSecPermWrapper> adminSecPerms = new ArrayList<AdminSecPermWrapper>();

    public List<AdminSecPermWrapper> getAdminSecPerms() {
        return adminSecPerms;
    }

    public void setAdminSecPerms(List<AdminSecPermWrapper> adminSecPerms) {
        this.adminSecPerms = adminSecPerms;
    }

    @Override
    public void wrapDetails(List<AdminSecPerm> models, HttpServletRequest request) {
        for (AdminSecPerm adminSecPerm : models) {
            AdminSecPermWrapper wrapper = (AdminSecPermWrapper) context.getBean(AdminSecPermWrapper.class.getName());
            wrapper.wrapSummary(adminSecPerm, request);
            adminSecPerms.add(wrapper);
        }
    }

    @Override
    public void wrapSummary(List<AdminSecPerm> model, HttpServletRequest request) {

    }
}
