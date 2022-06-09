package org.technohaven.api.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.id.service.IdGenerationService;
import org.springframework.stereotype.Service;
import org.technohaven.core.dao.CityDao;
import org.technohaven.core.dao.DistrictDao;
import org.technohaven.core.entities.City;
import org.technohaven.core.entities.District;
import org.technohaven.core.entities.Showroom;

import javax.annotation.Resource;
import java.util.List;

@Service("blCityService")
public class CityServiceImpl implements CityService{

    protected static final Log LOG = LogFactory.getLog(CityServiceImpl.class);

    @Resource(name="blCityDao")
    protected CityDao cityDao;

    @Resource(name = "blIdGenerationService")
    protected IdGenerationService idGenerationService;

    @Override
    public City save(City city) {
        if (city.getId() == null) {
            city.setId(findNextCityId());
        }
        return cityDao.save(city);
    }

    @Override
    public List<City> getCities() {
        return cityDao.getCities();
    }

    @Override
    public List<City> findAllCities(int limit, int offset) {
        return cityDao.readAllCity(limit, offset);
    }

    @Override
    public List<City> findCitiesByName(String cityName, int limit, int offset) {
        return cityDao.readCitiesByName(cityName, limit, offset);
    }

    @Override
    public City createCityFromId(Long cityId) {
        City city = cityId != null ? findCityById(cityId) : null;
        if (city == null) {
            city = cityDao.create();
            if (cityId != null) {
                city.setId(cityId);
            } else {
                city.setId(findNextCityId());
            }
        }
        return city;
    }

    @Override
    public City findCityById(Long cityId) {
        return cityDao.readCityById(cityId);
    }

    @Override
    public Long findNextCityId() {
        return idGenerationService.findNextId(City.class.getCanonicalName());
    }

}
