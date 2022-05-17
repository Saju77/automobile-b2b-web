package org.technohaven.api.services;

import org.technohaven.core.entities.City;
import org.technohaven.core.entities.District;

import java.util.List;

public interface CityService {

    List<City> getCities();

    List<City> findAllCities(int limit, int offset);

    /**
     * Retrieve a list of {@code City} instances based on the search criteria
     *
     * @param cityName the name of the city to search by
     * @param limit the maximum number of results to return
     * @param offset the starting point of the records to return
     * @return a list of city instances that match the search criteria
     */
    List<City> findCitiesByName(String cityName, int limit, int offset);

}
