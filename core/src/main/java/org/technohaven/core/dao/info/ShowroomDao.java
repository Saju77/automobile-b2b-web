package org.technohaven.core.dao.info;

import org.technohaven.core.entities.District;
import org.technohaven.core.entities.Profile;
import org.technohaven.core.entities.Showroom;

import javax.annotation.Nonnull;
import java.util.List;

public interface ShowroomDao {

    public Showroom save(Showroom showroom);

    @Nonnull
    public Showroom readShowroomById(@Nonnull Long ShowroomId);

    @Nonnull
    List<Showroom> getShowrooms();

    public Showroom create();

}
