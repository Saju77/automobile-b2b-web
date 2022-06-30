package org.technohaven.api.services;

import org.broadleafcommerce.openadmin.server.security.domain.AdminModule;

public interface AdminModuleService {

    AdminModule findAdminModuleById(Long adminModuleId);

}
