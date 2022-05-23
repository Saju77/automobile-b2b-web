package org.technohaven.core.dao;

import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;
import org.technohaven.core.entities.Port;
import org.technohaven.core.entities.PortImpl;
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

@Repository("blTransmissionDao")
public class TransmissionDaoImpl implements TransmissionDao{

    @PersistenceContext(unitName="blPU")
    protected EntityManager em;

    @Nonnull
    @Override
    @Transactional
    public Transmission save(@Nonnull Transmission transmission) {
        return em.merge(transmission);
    }

    @Nonnull
    @Override
    public List<Transmission> readAllTransmission() {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Transmission> criteria = builder.createQuery(Transmission.class);
        Root<TransmissionImpl> root = criteria.from(TransmissionImpl.class);
        criteria.select(root);
        TypedQuery<Transmission> query = this.em.createQuery(criteria);
        return query.getResultList();
    }

}
