package org.technohaven.core.dao;

import org.technohaven.core.entities.Showroom;
import org.technohaven.core.entities.Warehouse;

import javax.annotation.Nonnull;
import java.util.List;

public interface WarehouseDao {

    public Warehouse save(Warehouse warehouse);

    @Nonnull
    public Warehouse readWarehouseById(@Nonnull Long warehouseId);

    @Nonnull
    List<Warehouse> getWarehouses();

    public Warehouse create();

    public void deleteWarehouse(Warehouse warehouse);

}
