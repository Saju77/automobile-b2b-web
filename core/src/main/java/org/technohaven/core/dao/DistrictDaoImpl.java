package org.technohaven.core.dao;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;
import org.technohaven.core.entities.District;
import org.technohaven.core.entities.DistrictImpl;

import java.util.List;

@Repository("blDistrictDao")
public class DistrictDaoImpl implements DistrictDao {

	@PersistenceContext(unitName="blPU")
	protected EntityManager em;
	
  	@Nonnull
  	@Override
	@Transactional
	public District save(@Nonnull District district) {
		 return em.merge(district);
	}

	@Override
	public List<District> getDistricts() {
		CriteriaBuilder builder = this.em.getCriteriaBuilder();
		CriteriaQuery<District> criteria = builder.createQuery(District.class);
		Root<DistrictImpl> order = criteria.from(DistrictImpl.class);
		criteria.select(order);
		TypedQuery<District> query = this.em.createQuery(criteria);
		return query.getResultList();
	}
	
	@Override
	public List<District> readAllDistrict(int limit, int offset) {
        TypedQuery<District> query = em.createNamedQuery("CBC_READ_ALL_DISTRICT", District.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        query.setHint(QueryHints.HINT_CACHE_REGION, "query.District");

        return query.getResultList();
    }
	
	@Override
    public List<District> readDistrictsByName(String districtName, int limit, int offset) {
        TypedQuery<District> query = em.createNamedQuery("CBC_READ_DISTRICT_BY_NAME", District.class);
        query.setParameter("districtName", districtName);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        query.setHint(QueryHints.HINT_CACHE_REGION, "query.District");
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        return query.getResultList();
    }

}
