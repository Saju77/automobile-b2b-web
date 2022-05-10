package org.technohaven.admin.entities;

import java.io.Serializable;

public interface District extends Serializable {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getCode();

    void setCode(String code);

}
