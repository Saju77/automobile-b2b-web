package org.technohaven.core.dao;

import org.technohaven.core.entities.Port;
import org.technohaven.core.entities.Transmission;

import javax.annotation.Nonnull;
import java.util.List;

public interface TransmissionDao {

    @Nonnull
    Transmission save(@Nonnull Transmission transmission);

    @Nonnull
    List<Transmission> readAllTransmission();

}
