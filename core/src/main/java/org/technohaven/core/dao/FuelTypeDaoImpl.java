package org.technohaven.core.dao;

import org.springframework.stereotype.Repository;
import org.technohaven.core.entities.FuelType;
import org.technohaven.core.entities.FuelTypeImpl;
import org.technohaven.core.entities.Transmission;
import org.technohaven.core.entities.TransmissionImpl;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository("blFuelTypeDao")
public class FuelTypeDaoImpl implements FuelTypeDao{

    @PersistenceContext(unitName="blPU")
    protected EntityManager em;

    @Nonnull
    @Override
    @Transactional
    public FuelType save(@Nonnull FuelType fuelType) {
        return em.merge(fuelType);
    }

    @Nonnull
    @Override
    public List<FuelType> readAllFuelType() {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<FuelType> criteria = builder.createQuery(FuelType.class);
        Root<FuelTypeImpl> root = criteria.from(FuelTypeImpl.class);
        criteria.select(root);
        TypedQuery<FuelType> query = this.em.createQuery(criteria);
        return query.getResultList();
    }
}
