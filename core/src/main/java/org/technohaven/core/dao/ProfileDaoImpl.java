package org.technohaven.core.dao;

import org.broadleafcommerce.common.persistence.EntityConfiguration;
import org.springframework.stereotype.Repository;
import org.technohaven.core.entities.Profile;
import org.technohaven.core.entities.ProfileImpl;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository("blProfileDao")
public class ProfileDaoImpl implements ProfileDao{

    @PersistenceContext(unitName="blPU")
    protected EntityManager em;

    @Resource(name="blEntityConfiguration")
    protected EntityConfiguration entityConfiguration;

    @Override
    public Profile save(Profile profile) {
        return em.merge(profile);
    }

    @Override
    public Profile create() {
        String prof = Profile.class.getName();
        Profile profile =  (Profile) entityConfiguration.createEntityInstance(prof);
        return profile;
    }

    @Nonnull
    @Override
    public List<Profile> getProfiles() {
        CriteriaBuilder builder = this.em.getCriteriaBuilder();
        CriteriaQuery<Profile> criteria = builder.createQuery(Profile.class);
        Root<ProfileImpl> order = criteria.from(ProfileImpl.class);
        criteria.select(order);
        TypedQuery<Profile> query = this.em.createQuery(criteria);
        return query.getResultList();
    }

    @Nonnull
    @Override
    public Profile readProfileById(@Nonnull Long profileId) {
        return em.find(ProfileImpl.class, profileId);
    }
}
