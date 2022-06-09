package org.technohaven.api.services;

import org.technohaven.core.entities.Warehouse;

import java.util.List;

public interface WarehouseService {

    Warehouse save(Warehouse warehouse);

    List<Warehouse> getWarehouses();

    Warehouse findWarehouseById(Long warehouseId);

    /**
     * Returns a {@link Warehouse} by first looking in the database, otherwise creating a new non-persisted {@link Warehouse}
     *
     * @param warehouseId the id of the profile to lookup
     */
    Warehouse createWarehouseFromId(Long warehouseId);

    Long findNextWarehouseId();

    void removeWarehouse(Warehouse warehouse);

}
