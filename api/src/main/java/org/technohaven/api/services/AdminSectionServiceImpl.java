package org.technohaven.api.services;

import org.broadleafcommerce.common.id.service.IdGenerationService;
import org.broadleafcommerce.common.util.TransactionUtils;
import org.broadleafcommerce.openadmin.server.security.domain.AdminSection;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.technohaven.core.dao.AdminSectionDao;

import javax.annotation.Resource;
import java.util.List;

@Service("blAdminSectionService")
public class AdminSectionServiceImpl implements AdminSectionService{

    @Resource(name="blAdminSectionDao")
    protected AdminSectionDao adminSectionDao;

    @Resource(name = "blIdGenerationService")
    protected IdGenerationService idGenerationService;

    @Override
    public List<AdminSection> getAdminSections() {
        return adminSectionDao.getAdminSections();
    }

    @Override
    public AdminSection findAdminSectionById(Long adminSectionId) {
        return adminSectionDao.readAdminSectionById(adminSectionId);
    }

    @Override
    @Transactional(TransactionUtils.DEFAULT_TRANSACTION_MANAGER)
    public AdminSection save(AdminSection adminSection) {
        return adminSectionDao.save(adminSection);
    }

    @Override
    public AdminSection createAdminSectionFromId(Long adminSectionId) {
        AdminSection adminSection = adminSectionId != null ? findAdminSectionById(adminSectionId) : null;
        if (adminSection == null) {
            adminSection = adminSectionDao.create();
        }
        return adminSection;
    }

}
