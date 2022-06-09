package org.technohaven.core.dao;

import org.springframework.stereotype.Repository;
import org.technohaven.core.entities.ModelImpl;
import org.technohaven.core.entities.VehicleModel;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("blModelDao")
public class ModelDaoImpl implements ModelDao{

    @PersistenceContext(unitName="blPU")
    protected EntityManager em;


    @Nonnull
    @Override
    public List<VehicleModel> getVehicleModels() {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<VehicleModel> criteria = builder.createQuery(VehicleModel.class);
        Root<ModelImpl> order = criteria.from(ModelImpl.class);
        criteria.select(order);
        TypedQuery<VehicleModel> query = this.em.createQuery(criteria);
        return query.getResultList();
    }

    @Nonnull
    @Override
    public VehicleModel readVehicleModelById(@Nonnull Long modelId) {
        return em.find(ModelImpl.class, modelId);
    }
}
