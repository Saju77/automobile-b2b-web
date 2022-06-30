package org.technohaven.api.services;

import org.broadleafcommerce.openadmin.server.security.domain.AdminModule;
import org.technohaven.core.entities.AdminSecPerm;

import java.util.List;

public interface AdminSecPermService {

    int numberOfSubscriptions();

    List<AdminSecPerm> getAdminSecPerms();

}
