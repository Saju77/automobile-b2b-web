package org.technohaven.core.entities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class AdminSecPerm {

    private static final Long serialVersionUID = 1L;
    private static final Log LOG = LogFactory.getLog(AuctionGradeImpl.class);

    protected int adminSecId;
    protected int adminPermId;

    public int getAdminSecId() {
        return adminSecId;
    }

    public void setAdminSecId(int adminSecId) {
        this.adminSecId = adminSecId;
    }

    public int getAdminPermId() {
        return adminPermId;
    }

    public void setAdminPermId(int adminPermId) {
        this.adminPermId = adminPermId;
    }
}
