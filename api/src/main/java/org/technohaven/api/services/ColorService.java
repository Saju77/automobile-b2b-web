package org.technohaven.api.services;

import org.technohaven.core.entities.Color;
import org.technohaven.core.entities.Port;

import java.util.List;

public interface ColorService {

    List<Color> getAllColor();

    List<Color> findColors(int limit, int offset);

    /**
     * Retrieve a list of {@code Color} instances based on the search criteria
     *
     * @param colorName the name of the Port to search by
     * @param limit the maximum number of results to return
     * @param offset the starting point of the records to return
     * @return a list of Color instances that match the search criteria
     */
    List<Color> findColorsByName(String colorName, int limit, int offset);

}
