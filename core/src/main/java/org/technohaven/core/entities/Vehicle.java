package org.technohaven.core.entities;

import java.io.Serializable;

public interface Vehicle extends Serializable {

    public Long getId();

    public void setId(Long id);

    public VehicleModel getVehicleModel();

    public void setVehicleModel(VehicleModel vehicleModel);

    public String getEdition();

    public void setEdition(String edition);

    public String getShortName();

    public void setShortName(String shortName);

}
