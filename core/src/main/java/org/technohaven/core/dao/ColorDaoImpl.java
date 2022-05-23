package org.technohaven.core.dao;

import org.hibernate.jpa.QueryHints;
import org.springframework.stereotype.Repository;
import org.technohaven.core.entities.Color;
import org.technohaven.core.entities.ColorImpl;
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

@Repository("blColorDao")
public class ColorDaoImpl implements ColorDao{

    @PersistenceContext(unitName="blPU")
    protected EntityManager em;

    @Nonnull
    @Override
    @Transactional
    public Color save(@Nonnull Color color) {
        return em.merge(color);
    }

    @Nonnull
    @Override
    public List<Color> readAllColor() {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Color> criteria = builder.createQuery(Color.class);
        Root<ColorImpl> root = criteria.from(ColorImpl.class);
        criteria.select(root);
        TypedQuery<Color> query = this.em.createQuery(criteria);
        return query.getResultList();
    }

    @Nonnull
    @Override
    public List<Color> readColors(@Nonnull int limit, @Nonnull int offset) {
        TypedQuery<Color> query = em.createNamedQuery("CBC_READ_ALL_COLOR", Color.class);
        query.setFirstResult(offset);
        query.setMaxResults(limit);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        query.setHint(QueryHints.HINT_CACHE_REGION, "query.Color");

        return query.getResultList();
    }

    @Nonnull
    @Override
    public List<Color> readColorsByName(@Nonnull String colorName, int limit, int offset) {
        TypedQuery<Color> query = em.createNamedQuery("CBC_READ_COLOR_BY_NAME", Color.class);
        query.setParameter("colorName", colorName);
        query.setHint(QueryHints.HINT_CACHEABLE, true);
        query.setHint(QueryHints.HINT_CACHE_REGION, "query.Color");
        query.setFirstResult(offset);
        query.setMaxResults(limit);

        return query.getResultList();
    }


}
