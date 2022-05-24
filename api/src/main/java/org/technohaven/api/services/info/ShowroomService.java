package org.technohaven.api.services.info;

import org.technohaven.core.entities.District;
import org.technohaven.core.entities.Profile;
import org.technohaven.core.entities.Showroom;

import java.util.List;

public interface ShowroomService {

    Showroom save(Showroom showroom);

    List<Showroom> getShowrooms();

    Showroom findShowroomById(Long showroomId);

    /**
     * Returns a {@link Showroom} by first looking in the database, otherwise creating a new non-persisted {@link Showroom}
     *
     * @param showroomId the id of the profile to lookup
     */
    Showroom createShowroomFromId(Long showroomId);

    Long findNextShowroomId();

}
