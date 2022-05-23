package org.technohaven.api.endpoint.location;

import com.broadleafcommerce.rest.api.endpoint.BaseEndpoint;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.technohaven.api.services.CityService;
import org.technohaven.api.wrapper.CitiesWrapper;
import org.technohaven.core.entities.City;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/city",  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class CityEndPoint extends BaseEndpoint {

    @Resource(name = "blCityService")
    protected CityService cityService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public CitiesWrapper getAllCity(HttpServletRequest request) {
        List<City> cities;
        cities = cityService.getCities();

        CitiesWrapper wrapper = (CitiesWrapper) context.getBean(CitiesWrapper.class.getName());
        wrapper.wrapDetails(cities, request);
        return wrapper;
    }

    @RequestMapping(value = "/findCities", method = RequestMethod.GET)
    public CitiesWrapper findCities(HttpServletRequest request,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "limit", defaultValue = "20") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset) {
        List<City> cities;

        if (name != null) {
            cities = cityService.findCitiesByName(name, limit, offset);
        } else {
            cities = cityService.findAllCities(limit, offset);
        }

        CitiesWrapper wrapper = (CitiesWrapper) context.getBean(CitiesWrapper.class.getName());
        wrapper.wrapDetails(cities, request);
        return wrapper;
    }

}

