package org.technohaven.core.dao;

import org.technohaven.core.entities.City;
import org.technohaven.core.entities.District;
import org.technohaven.core.entities.Showroom;

import javax.annotation.Nonnull;
import java.util.List;

public interface CityDao {

    @Nonnull
    City save(@Nonnull City city);

    @Nonnull
    List<City> getCities();

    /**
     * Retrieve a subset of all Cities
     *
     * @param limit the maximum number of results, defaults to 20
     * @param offset the starting point in the record set, defaults to 0
     * @return
     */
    @Nonnull
    public List<City> readAllCity(@Nonnull int limit, @Nonnull int offset);

    @Nonnull
    public List<City> readCitiesByName(@Nonnull String cityName, int limit, int offset);

    @Nonnull
    public City readCityById(@Nonnull Long cityId);

    public City create();

}
