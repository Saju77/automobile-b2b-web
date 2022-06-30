package org.technohaven.core.entities;

import org.springframework.ui.Model;

import java.io.Serializable;

public interface PkgTrim extends Serializable {

    public Long getId();

    public void setId(Long id);

    public VehicleModel getVehicleModel();

    public void setVehicleModel(VehicleModel vehicleModel);

    public String getName();

    public void setName(String name);
}
