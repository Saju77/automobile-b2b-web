package org.technohaven.core.dao.info;

import org.technohaven.core.entities.Profile;

import javax.annotation.Nonnull;

public interface ProfileDao {

    public Profile save(Profile profile);

    public Profile create();

    @Nonnull
    public Profile readProfileById(@Nonnull Long profileId);

}
