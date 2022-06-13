package org.technohaven.api.services;

import org.broadleafcommerce.openadmin.server.security.domain.AdminSection;

import java.util.List;

public interface AdminSectionService {

    List<AdminSection> getAdminSections();

    AdminSection findAdminSectionById(Long adminSectionId);

    AdminSection save(AdminSection adminSection);

    AdminSection createAdminSectionFromId(Long adminSectionId);

}
