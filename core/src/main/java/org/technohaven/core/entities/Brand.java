package org.technohaven.core.entities;

import java.io.Serializable;

public interface Brand extends Serializable {
    Long getId();

    void setId(Long brandId);

    String getName();

    void setName(String brandName);

    String getBrandShortName();

    void setBrandShortName(String brandShortName);

    String getBrandCode();

    void setBrandCode(String brandCode);
}
