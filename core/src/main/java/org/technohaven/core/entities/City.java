package org.technohaven.core.entities;

import java.io.Serializable;

public interface City extends Serializable {

    Long getId();

    void setId(Long id);

    public String getName();

    public void setName(String name);

    public int getCode();

    public void setCode(int code);
    
    District getDistrictId();

	void setDistrictId(District districtId);

}
