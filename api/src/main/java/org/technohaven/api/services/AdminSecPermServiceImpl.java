package org.technohaven.api.services;

import org.broadleafcommerce.openadmin.server.security.domain.AdminModule;
import org.broadleafcommerce.openadmin.server.security.domain.AdminSection;
import org.springframework.stereotype.Service;
import org.technohaven.core.dao.AdminModuleDao;
import org.technohaven.core.dao.AdminSecPermDao;
import org.technohaven.core.entities.AdminSecPerm;

import javax.annotation.Resource;
import java.util.List;

@Service("blAdminSecPermService")
public class AdminSecPermServiceImpl implements AdminSecPermService{

    @Resource(name="blAdminSecPermDao")
    protected AdminSecPermDao adminSecPermDao;

    @Override
    public int numberOfSubscriptions() {
        return adminSecPermDao.numberOfSubscriptions();
    }

    @Override
    public List<AdminSecPerm> getAdminSecPerms() {
        return adminSecPermDao.getAdminSecPerms();
    }

}
