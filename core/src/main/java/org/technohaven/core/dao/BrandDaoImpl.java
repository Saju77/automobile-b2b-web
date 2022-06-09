package org.technohaven.core.dao;

import org.springframework.stereotype.Repository;
import org.technohaven.core.entities.Brand;
import org.technohaven.core.entities.BrandImpl;
import org.technohaven.core.entities.Showroom;
import org.technohaven.core.entities.ShowroomImpl;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("blBrandDao")
public class BrandDaoImpl implements BrandDao{

    @PersistenceContext(unitName="blPU")
    protected EntityManager em;

    @Nonnull
    @Override
    public List<Brand> getBrands() {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Brand> criteria = builder.createQuery(Brand.class);
        Root<BrandImpl> order = criteria.from(BrandImpl.class);
        criteria.select(order);
        TypedQuery<Brand> query = this.em.createQuery(criteria);
        return query.getResultList();
    }

    @Nonnull
    @Override
    public Brand readBrandById(@Nonnull Long brandId) {
        return em.find(BrandImpl.class, brandId);
    }
}
