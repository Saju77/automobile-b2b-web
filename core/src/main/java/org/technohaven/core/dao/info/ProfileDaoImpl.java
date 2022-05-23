package org.technohaven.core.dao.info;

import org.broadleafcommerce.common.persistence.EntityConfiguration;
import org.springframework.stereotype.Repository;
import org.technohaven.core.entities.Profile;
import org.technohaven.core.entities.ProfileImpl;

import javax.annotation.Nonnull;
import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
        Profile profile =  (Profile) entityConfiguration.createEntityInstance(Profile.class.getName());
        return profile;
    }

    @Nonnull
    @Override
    public Profile readProfileById(@Nonnull Long profileId) {
        return em.find(ProfileImpl.class, profileId);
    }
}
