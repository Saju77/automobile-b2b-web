package org.technohaven.api.services;

import org.broadleafcommerce.common.id.service.IdGenerationService;
import org.broadleafcommerce.common.util.TransactionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.technohaven.core.dao.WarehouseDao;
import org.technohaven.core.entities.Warehouse;

import javax.annotation.Resource;
import java.util.List;

@Service("blWarehouseService")
public class WarehouseServiceImpl implements WarehouseService{

    @Resource(name="blWarehouseDao")
    protected WarehouseDao warehouseDao;

    @Resource(name = "blIdGenerationService")
    protected IdGenerationService idGenerationService;

    @Override
    @Transactional(TransactionUtils.DEFAULT_TRANSACTION_MANAGER)
    public Warehouse save(Warehouse warehouse) {
        if (warehouse.getId() == null) {
            warehouse.setId(findNextWarehouseId());
        }
        return warehouseDao.save(warehouse);
    }

    @Override
    public List<Warehouse> getWarehouses() {
        return warehouseDao.getWarehouses();
    }

    @Override
    public Warehouse findWarehouseById(Long warehouseId) {
        return warehouseDao.readWarehouseById(warehouseId);
    }

    @Override
    public Warehouse createWarehouseFromId(Long warehouseId) {
        Warehouse warehouse = warehouseId != null ? findWarehouseById(warehouseId) : null;
        if (warehouse == null) {
            warehouse = warehouseDao.create();
            if (warehouseId != null) {
                warehouse.setId(warehouseId);
            } else {
                warehouse.setId(findNextWarehouseId());
            }
        }
        return warehouse;
    }

    @Override
    public Long findNextWarehouseId() {
        return idGenerationService.findNextId(Warehouse.class.getCanonicalName());
    }

    @Override
    @Transactional("blTransactionManager")
    public void removeWarehouse(Warehouse warehouse) {
        warehouseDao.deleteWarehouse(warehouse);
    }

}
