package org.technohaven.core.entities;

import org.apache.commons.lang3.BooleanUtils;
import org.broadleafcommerce.common.copy.MultiTenantCloneable;
import org.broadleafcommerce.profile.core.domain.Customer;

import java.io.Serializable;

public interface Profile extends Serializable {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getDesignation();

    void setDesignation(String designation);

    String getPhoneNumber();

    void setPhoneNumber(String phoneNumber);

    String getBusinessEmail();

    void setBusinessEmail(String businessEmail);

    String getCompanyName();

    void setCompanyName(String companyName);

    String getBinNumber();

    void setBinNumber(String binNumber);

    String getTradeLicense();

    void setTradeLicense(String tradeLicense);

    String getBarvidaMemberId();

    void setBarvidaMemberId(String barvidaMemberId);

    String getAddress();

    void setAddress(String address);

    String getPhotoUrl();

    void setPhotoUrl(String photoUrl);

    public boolean isRegistered();

    public void setRegistered(boolean registered);

}
