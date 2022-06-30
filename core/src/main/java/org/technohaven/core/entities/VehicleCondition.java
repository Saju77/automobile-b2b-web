package org.technohaven.core.entities;

import java.io.Serializable;

public interface VehicleCondition extends Serializable {
    long getId();

    void setId(long id);

    String getVehicleCondition();

    void setVehicleCondition(String vehicleCondition);
}
