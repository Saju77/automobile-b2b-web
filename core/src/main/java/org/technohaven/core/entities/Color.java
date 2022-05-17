package org.technohaven.core.entities;

import java.io.Serializable;

public interface Color extends Serializable {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getCode();

    void setCode(String code);

    String getShortName();

    void setShortName(String shortName);

}
