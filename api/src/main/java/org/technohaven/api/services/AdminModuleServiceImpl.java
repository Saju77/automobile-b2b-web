package org.technohaven.api.services;

import org.broadleafcommerce.openadmin.server.security.domain.AdminModule;
import org.springframework.stereotype.Service;
import org.technohaven.core.dao.AdminModuleDao;

import javax.annotation.Resource;

@Service("blAdminModuleService")
public class AdminModuleServiceImpl implements AdminModuleService{

    @Resource(name="blAdminModuleDao")
    protected AdminModuleDao adminModuleDao;

    @Override
    public AdminModule findAdminModuleById(Long adminModuleId) {
        return adminModuleDao.readAdminModuleById(adminModuleId);
    }
}
