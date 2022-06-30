package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.core.entities.City;
import org.technohaven.core.entities.District;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@Component("org.technohaven.api.wrapper.CitiesWrapper")
@Scope("prototype")
public class CitiesWrapper extends BaseWrapper implements APIWrapper<List<City>> {
	
	@XmlElement(name = "city")
    protected List<CityWrapper> cities = new ArrayList<CityWrapper>();

	public List<CityWrapper> getCities() {
		return cities;
	}

	public void setCities(List<CityWrapper> cities) {
		this.cities = cities;
	}


	@Override
	public void wrapDetails(List<City> models, HttpServletRequest request) {

		for (City city : models) {
			CityWrapper cityWrapper = (CityWrapper) context.getBean(CityWrapper.class.getName());
			cityWrapper.wrapSummary(city, request);
			cities.add(cityWrapper);
		}
	}

	@Override
	public void wrapSummary(List<City> model, HttpServletRequest request) {
		wrapDetails(model, request);
	}
}
