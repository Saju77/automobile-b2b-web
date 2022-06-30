package org.technohaven.core.entities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.openadmin.server.security.domain.AdminPermission;
import org.broadleafcommerce.openadmin.server.security.domain.AdminSection;

public class AdminSecPermImpl implements AdminSecPerm{

    private static final Long serialVersionUID = 1L;
    private static final Log LOG = LogFactory.getLog(AdminSecPermImpl.class);

    protected AdminSection adminSecId;

    protected AdminPermission adminPermId;

    @Override
    public AdminSection getAdminSecId() {
        return adminSecId;
    }

    public void setAdminSecId(AdminSection adminSecId) {
        this.adminSecId = adminSecId;
    }

    @Override
    public AdminPermission getAdminPermId() {
        return adminPermId;
    }

    public void setAdminPermId(AdminPermission adminPermId) {
        this.adminPermId = adminPermId;
    }
}
