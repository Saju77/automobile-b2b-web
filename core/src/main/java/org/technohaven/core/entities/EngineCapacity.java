package org.technohaven.core.entities;

import java.io.Serializable;

public interface EngineCapacity extends Serializable {
    long getId();

    void setId(long id);

    VehicleModel getVehicleModel();

    void setVehicleModel(VehicleModel vehicleModel);

    String getEngineCapacity();

    void setEngineCapacity(String engineCapacity);
}
