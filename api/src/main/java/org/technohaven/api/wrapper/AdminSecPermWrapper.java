package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIUnwrapper;
import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.broadleafcommerce.openadmin.server.security.domain.AdminPermission;
import org.broadleafcommerce.openadmin.server.security.domain.AdminSection;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.core.entities.AdminSecPerm;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Component("org.technohaven.api.wrapper.AdminSecPermWrapper")
@Scope("prototype")
@XmlRootElement(name = "adminSecPerm")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class AdminSecPermWrapper extends BaseWrapper implements APIWrapper<AdminSecPerm>, APIUnwrapper<AdminSecPerm> {

    @XmlElement
    protected int adminSecId;

    @XmlElement
    protected int adminPermId;

    public int getAdminSecId() {
        return adminSecId;
    }

    public void setAdminSecId(int adminSecId) {
        this.adminSecId = adminSecId;
    }

    public int getAdminPermId() {
        return adminPermId;
    }

    public void setAdminPermId(int adminPermId) {
        this.adminPermId = adminPermId;
    }

    @Override
    public void wrapDetails(AdminSecPerm adminSecPerm, HttpServletRequest request) {
        this.adminSecId = adminSecPerm.getAdminSecId();
        this.adminPermId = adminSecPerm.getAdminPermId();
    }

    @Override
    public void wrapSummary(AdminSecPerm model, HttpServletRequest request) {
        wrapDetails(model, request);
    }

    @Override
    public AdminSecPerm unwrap(HttpServletRequest request, ApplicationContext context) {
        return null;
    }
}
