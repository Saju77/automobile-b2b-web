package org.technohaven.core.dao;

import org.broadleafcommerce.openadmin.server.security.domain.AdminSection;

import javax.annotation.Nonnull;
import java.util.List;

public interface AdminSectionDao {

    @Nonnull
    AdminSection save(@Nonnull AdminSection adminSection);

    @Nonnull
    List<AdminSection> getAdminSections();

    @Nonnull
    AdminSection readAdminSectionById(@Nonnull Long adminSectionId);

    public AdminSection create();

}
