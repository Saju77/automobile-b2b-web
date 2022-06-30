package org.technohaven.core.dao;

import org.broadleafcommerce.openadmin.server.security.domain.AdminSection;
import org.broadleafcommerce.openadmin.server.security.domain.AdminSectionImpl;
import org.springframework.stereotype.Repository;
import org.technohaven.core.entities.AdminSecPerm;

import javax.annotation.Nonnull;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("blAdminSecPermDao")
public class AdminSecPermDaoImpl implements AdminSecPermDao{

    @PersistenceContext(unitName="blPU")
    protected EntityManager em;

    public int numberOfSubscriptions() {
        // >> "subscriptions" has to be the exact name of your table
        // if does not work, consider trying SUBSCRIPTIONS or Subscriptions
        String sql = "SELECT count(*) FROM blc_admin_sec_perm_xref";
        Query query = em.createNativeQuery(sql);

        // getSingleResult() instead :)
        return ((Number) query.getSingleResult()).intValue();
    }

    @Nonnull
    @Override
    public List<AdminSecPerm> getAdminSecPerms() {
        String sql = "SELECT * FROM blc_admin_sec_perm_xref";
        Query query = em.createNativeQuery(sql);

        for (int i = 0; i < 5; i++) {
            System.out.println("query.getResultList() "+i+" -> "+query.getResultList().get(i));
        }

        List<AdminSecPerm> adminSecPerms = query.getResultList();

        return adminSecPerms;
    }

}
