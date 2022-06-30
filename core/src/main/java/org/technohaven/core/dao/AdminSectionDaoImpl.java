package org.technohaven.core.dao;

import org.broadleafcommerce.common.persistence.EntityConfiguration;
import org.broadleafcommerce.openadmin.server.security.domain.AdminSection;
import org.broadleafcommerce.openadmin.server.security.domain.AdminSectionImpl;
import org.springframework.stereotype.Repository;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("blAdminSectionDao")
public class AdminSectionDaoImpl implements AdminSectionDao{

    @PersistenceContext(unitName="blPU")
    protected EntityManager em;

    @Resource(name="blEntityConfiguration")
    protected EntityConfiguration entityConfiguration;

    @Nonnull
    @Override
    public AdminSection save(@Nonnull AdminSection adminSection) {
        return em.merge(adminSection);
    }

    @Nonnull
    @Override
    public List<AdminSection> getAdminSections() {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<AdminSection> criteria = builder.createQuery(AdminSection.class);
        Root<AdminSectionImpl> order = criteria.from(AdminSectionImpl.class);
        criteria.select(order);
        TypedQuery<AdminSection> query = this.em.createQuery(criteria);
        return query.getResultList();
    }

    @Nonnull
    @Override
    public AdminSection readAdminSectionById(@Nonnull Long adminSectionId) {
        return em.find(AdminSectionImpl.class, adminSectionId);
    }

    @Override
    public AdminSection create() {
        String adm = AdminSection.class.getName();
        AdminSection adminSection =  (AdminSection) entityConfiguration.createEntityInstance(adm);
        return adminSection;
    }
}
