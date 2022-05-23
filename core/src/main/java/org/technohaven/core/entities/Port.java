package org.technohaven.core.entities;

import java.io.Serializable;

public interface Port extends Serializable {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    int getCode();

    void setCode(int code);

    City getCityId();

    void setCityId(City cityId);

}
