package org.technohaven.core.dao.info;

import org.broadleafcommerce.common.persistence.EntityConfiguration;
import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;
import org.technohaven.core.entities.*;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("blShowroomDao")
public class ShowroomDaoImpl implements ShowroomDao{

    @PersistenceContext(unitName="blPU")
    protected EntityManager em;

    @Resource(name="blEntityConfiguration")
    protected EntityConfiguration entityConfiguration;

    @Override
    public Showroom save(Showroom showroom) {
        return em.merge(showroom);
    }

    @Nonnull
    @Override
    public Showroom readShowroomById(@Nonnull Long ShowroomId) {
        return em.find(ShowroomImpl.class, ShowroomId);
    }

    @Nonnull
    @Override
    public List<Showroom> getShowrooms() {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Showroom> criteria = builder.createQuery(Showroom.class);
        Root<ShowroomImpl> order = criteria.from(ShowroomImpl.class);
        criteria.select(order);
        TypedQuery<Showroom> query = this.em.createQuery(criteria);
        return query.getResultList();
    }

    @Override
    public Showroom create() {
        String Showr = Showroom.class.getName();
        Showroom showroom =  (Showroom) entityConfiguration.createEntityInstance(Showr);
        return showroom;
    }
}
