package org.technohaven.core.dao;

import org.broadleafcommerce.common.persistence.EntityConfiguration;
import org.springframework.stereotype.Repository;
import org.technohaven.core.entities.ShowroomImpl;
import org.technohaven.core.entities.Warehouse;
import org.technohaven.core.entities.WarehouseImpl;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("blWarehouseDao")
public class WarehouseDaoImpl implements WarehouseDao{

    @PersistenceContext(unitName="blPU")
    protected EntityManager em;

    @Resource(name="blEntityConfiguration")
    protected EntityConfiguration entityConfiguration;

    @Override
    public Warehouse save(Warehouse warehouse) {
        return em.merge(warehouse);
    }

    @Nonnull
    @Override
    public Warehouse readWarehouseById(@Nonnull Long warehouseId) {
        return em.find(WarehouseImpl.class, warehouseId);
    }

    @Nonnull
    @Override
    public List<Warehouse> getWarehouses() {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Warehouse> criteria = builder.createQuery(Warehouse.class);
        Root<WarehouseImpl> order = criteria.from(WarehouseImpl.class);
        criteria.select(order);
        TypedQuery<Warehouse> query = this.em.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public Warehouse create() {
        String wh = Warehouse.class.getName();
        Warehouse warehouse =  (Warehouse) entityConfiguration.createEntityInstance(wh);
        return warehouse;
    }

    @Override
    public void deleteWarehouse(Warehouse warehouse) {
        em.remove(warehouse);
    }

}
