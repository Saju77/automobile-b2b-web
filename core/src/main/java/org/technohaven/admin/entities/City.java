package org.technohaven.admin.entities;

import java.io.Serializable;

public interface City extends Serializable {

    Long getId();

    void setId(Long id);

    String getDistrict();

    void setDistrict(String district);

    String getCityUpazilaName();

    void setCityUpazilaName(String cityOrPoName);

    String getCityUpazilaCode();

    void setCityUpazilaCode(String cityOrPoCode);

}
