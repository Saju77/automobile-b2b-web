package org.technohaven.core.entities;

import java.io.Serializable;

public interface City extends Serializable {

    Long getId();

    void setId(Long id);

    String getCityUpazilaName();

    void setCityUpazilaName(String cityOrPoName);

    String getCityUpazilaCode();

    void setCityUpazilaCode(String cityOrPoCode);
    
    District getDistrictId();

	void setDistrictId(District districtId);

}
