package org.technohaven.admin.entities;

import java.io.Serializable;

public interface Transmission extends Serializable {

    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

}
