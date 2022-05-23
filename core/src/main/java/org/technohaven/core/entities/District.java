package org.technohaven.core.entities;

import java.io.Serializable;

public interface District extends Serializable {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    int getCode();

    void setCode(int code);

}
