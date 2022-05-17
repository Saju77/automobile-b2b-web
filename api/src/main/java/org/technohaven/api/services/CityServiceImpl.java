package org.technohaven.api.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.technohaven.core.dao.CityDao;
import org.technohaven.core.dao.DistrictDao;
import org.technohaven.core.entities.City;
import org.technohaven.core.entities.District;

import javax.annotation.Resource;
import java.util.List;

@Service("blCityService")
public class CityServiceImpl implements CityService{

    protected static final Log LOG = LogFactory.getLog(DistrictServiceImpl.class);

    @Resource(name="blCityDao")
    protected CityDao cityDao;

    @Override
    public List<City> getCities() {
        return cityDao.getCities();
    }

    @Override
    public List<City> findAllCities(int limit, int offset) {
        return cityDao.readAllCities(limit, offset);
    }

    @Override
    public List<City> findCitiesByName(String cityName, int limit, int offset) {
        return cityDao.readCitiesByName(cityName, limit, offset);
    }

}
