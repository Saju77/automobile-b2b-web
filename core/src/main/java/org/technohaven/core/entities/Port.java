package org.technohaven.core.entities;

import java.io.Serializable;

public interface Port extends Serializable {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getCode();

    void setCode(String code);

    String getDistrict();

    void setDistrict(String district);

    String getCity();

    void setCity(String city);

}
