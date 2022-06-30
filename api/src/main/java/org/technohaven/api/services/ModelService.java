package org.technohaven.api.services;

import org.technohaven.core.entities.VehicleModel;

import java.util.List;

public interface ModelService {

    List<VehicleModel> getVehicleModels();

    VehicleModel findVehicleModelById(Long modelId);

}
