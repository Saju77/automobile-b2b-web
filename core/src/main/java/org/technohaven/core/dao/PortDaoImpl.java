package org.technohaven.core.dao;

import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;
import org.technohaven.core.entities.Port;
import org.technohaven.core.entities.PortImpl;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;
import java.util.List;

@Repository("blPortDao")
public class PortDaoImpl implements PortDao{

    @PersistenceContext(unitName="blPU")
    protected EntityManager em;

    @Nonnull
    @Override
    @Transactional
    public Port save(@Nonnull Port port) {
        return em.merge(port);
    }

    @Nonnull
    @Override
    public List<Port> readAllPort() {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Port> criteria = builder.createQuery(Port.class);
        Root<PortImpl> root = criteria.from(PortImpl.class);
        criteria.select(root);
        TypedQuery<Port> query = this.em.createQuery(criteria);
        return query.getResultList();
    }

    @Nonnull
    @Override
    public List<Port> readPorts(@Nonnull int limit, @Nonnull int offset) {
        TypedQuery<Port> query = em.createNamedQuery("CBC_READ_ALL_PORT", Port.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        query.setHint(QueryHints.HINT_CACHE_REGION, "query.Port");

        return query.getResultList();
    }

    @Nonnull
    @Override
    public List<Port> readPortsByName(@Nonnull String portName, int limit, int offset) {
        TypedQuery<Port> query = em.createNamedQuery("CBC_READ_PORT_BY_NAME", Port.class);
        query.setParameter("portName", portName);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        query.setHint(QueryHints.HINT_CACHE_REGION, "query.Port");
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        return query.getResultList();
    }

}
