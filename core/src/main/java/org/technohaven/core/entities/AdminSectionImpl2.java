package org.technohaven.core.entities;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationToOneLookup;
import org.broadleafcommerce.openadmin.server.security.domain.AdminModule;
import org.broadleafcommerce.openadmin.server.security.domain.AdminModuleImpl;
import org.broadleafcommerce.openadmin.server.security.domain.AdminSectionImpl;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;

@Entity
@Table(name = "BLC_ADMIN_SECTION2")
public class AdminSectionImpl2 extends AdminSectionImpl implements AdminSection2{

    @ManyToOne(optional=false, targetEntity = AdminModuleImpl.class)
    @Fetch(FetchMode.JOIN)
    @JoinColumn(name = "ADMIN_MODULE_ID")
    @AdminPresentation(friendlyName = "AdminSectionImpl_Module", group = "AdminSectionImpl_Section", order = 10, prominent = true, gridOrder = 5)
    @AdminPresentationToOneLookup()
    protected AdminModule module2;

    public AdminModule getModule2() {
        return module2;
    }

    public void setModule2(AdminModule module2) {
        this.module2 = module2;
    }
}
