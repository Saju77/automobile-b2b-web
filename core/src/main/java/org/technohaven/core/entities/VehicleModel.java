package org.technohaven.core.entities;

import java.io.Serializable;

public interface VehicleModel extends Serializable {
    Long getId();

    void setId(Long id);

    String getName();

    void setName(String name);

    String getModelShortName();

    void setModelShortName(String modelShortName);

    String getModelCode();

    void setModelCode(String modelCode);

    Brand getBrand();

    void setBrand(Brand brand);
}
