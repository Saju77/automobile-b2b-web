package org.technohaven.api.services.info;

import org.technohaven.core.entities.Profile;
import org.technohaven.core.entities.Showroom;

import java.util.List;

public interface ProfileService {

    Profile saveProfile(Profile profile);

    /**
     * Returns a {@link Profile} by first looking in the database, otherwise creating a new non-persisted {@link Profile}
     *
     * @param profileId the id of the profile to lookup
     */
    Profile createProfileFromId(Long profileId);

    List<Profile> getProfiles();

    Long findNextProfileId();

    Profile findProfileById(Long profileId);

}
