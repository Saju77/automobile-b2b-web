package org.technohaven.core.dao;

import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;
import org.technohaven.core.entities.City;
import org.technohaven.core.entities.CityImpl;
import org.technohaven.core.entities.District;
import org.technohaven.core.entities.DistrictImpl;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository("blCityDao")
public class CityDaoImpl implements CityDao{

    @PersistenceContext(unitName="blPU")
    protected EntityManager em;

    @Nonnull
    @Override
    @Transactional
    public City save(@Nonnull City city) {
        return em.merge(city);
    }

    @Nonnull
    @Override
    public List<City> getCities() {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<City> criteria = builder.createQuery(City.class);
        Root<CityImpl> root = criteria.from(CityImpl.class);
        criteria.select(root);
        TypedQuery<City> query = this.em.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public List<City> readAllCity(int limit, int offset) {
        TypedQuery<City> query = em.createNamedQuery("CBC_READ_ALL_CITY", City.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        query.setHint(QueryHints.HINT_CACHE_REGION, "query.City");

        return query.getResultList();
    }

    @Override
    public List<City> readCitiesByName(String cityName, int limit, int offset) {
        TypedQuery<City> query = em.createNamedQuery("CBC_READ_CITY_BY_NAME", City.class);
        query.setParameter("cityUpazilaName", cityName);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        query.setHint(QueryHints.HINT_CACHE_REGION, "query.City");
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        return query.getResultList();
    }

}
