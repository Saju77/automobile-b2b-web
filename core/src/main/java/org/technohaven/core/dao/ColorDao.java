package org.technohaven.core.dao;

import org.technohaven.core.entities.Color;
import org.technohaven.core.entities.Port;

import javax.annotation.Nonnull;
import java.util.List;

public interface ColorDao {

    @Nonnull
    Color save(@Nonnull Color color);

    @Nonnull
    List<Color> readAllColor();

    /**
     * Retrieve a subset of all Color
     *
     * @param limit the maximum number of results, defaults to 20
     * @param offset the starting point in the record set, defaults to 0
     * @return
     */
    @Nonnull
    public List<Color> readColors(@Nonnull int limit, @Nonnull int offset);

    @Nonnull
    public List<Color> readColorsByName(@Nonnull String colorName, int limit, int offset);

}
