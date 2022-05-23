package org.technohaven.core.dao;

import org.technohaven.core.entities.FuelType;

import javax.annotation.Nonnull;
import java.util.List;

public interface FuelTypeDao {

    @Nonnull
    FuelType save(@Nonnull FuelType fuelType);

    @Nonnull
    List<FuelType> readAllFuelType();

}
