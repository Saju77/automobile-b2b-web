package org.technohaven.core.dao;

import org.broadleafcommerce.openadmin.server.security.domain.AdminModule;

import javax.annotation.Nonnull;

public interface AdminModuleDao {

    @Nonnull
    AdminModule readAdminModuleById(@Nonnull Long adminModuleId);

}
