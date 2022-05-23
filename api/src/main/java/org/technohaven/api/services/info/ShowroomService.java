package org.technohaven.api.services.info;

import org.technohaven.core.entities.District;
import org.technohaven.core.entities.Profile;
import org.technohaven.core.entities.Showroom;

import java.util.List;

public interface ShowroomService {

    List<Showroom> getShowrooms();

    Showroom findShowroomById(Long showroomId);

}
