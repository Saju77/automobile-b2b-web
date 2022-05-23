package org.technohaven.api.services.info;

import org.technohaven.core.entities.Profile;

public interface ProfileService {

    Profile save(Profile profile);

    /**
     * Returns a {@link Profile} by first looking in the database, otherwise creating a new non-persisted {@link Profile}
     *
     * @param profileId the id of the profile to lookup
     */
    public Profile createProfileFromId(Long profileId);

    public Long findNextProfileId();

    Profile findProfileById(Long profileId);

}
