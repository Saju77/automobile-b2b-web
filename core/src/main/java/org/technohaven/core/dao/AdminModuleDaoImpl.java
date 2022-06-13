package org.technohaven.core.dao;

import org.broadleafcommerce.openadmin.server.security.domain.AdminModule;
import org.broadleafcommerce.openadmin.server.security.domain.AdminModuleImpl;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository("blAdminModuleDao")
public class AdminModuleDaoImpl implements AdminModuleDao{

    @PersistenceContext(unitName="blPU")
    protected EntityManager em;

    @Nonnull
    @Override
    public AdminModule readAdminModuleById(@Nonnull Long adminModuleId) {
        return em.find(AdminModuleImpl.class, adminModuleId);
    }
}
