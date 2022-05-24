package org.technohaven.core.dao.info;

import org.technohaven.core.entities.Profile;
import org.technohaven.core.entities.Showroom;

import javax.annotation.Nonnull;
import java.util.List;

public interface ProfileDao {

    public Profile save(Profile profile);

    public Profile create();

    @Nonnull
    List<Profile> getProfiles();

    @Nonnull
    public Profile readProfileById(@Nonnull Long profileId);

}
