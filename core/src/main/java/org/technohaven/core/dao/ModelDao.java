package org.technohaven.core.dao;

import org.technohaven.core.entities.VehicleModel;

import javax.annotation.Nonnull;
import java.util.List;

public interface ModelDao {

    @Nonnull
    List<VehicleModel> getVehicleModels();

    @Nonnull
    VehicleModel readVehicleModelById(@Nonnull Long modelId);

}
