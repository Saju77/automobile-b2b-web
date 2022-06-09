package org.technohaven.api.wrapper;

import com.broadleafcommerce.rest.api.wrapper.CountryWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.APIUnwrapper;
import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.api.services.CityService;
import org.technohaven.core.entities.City;
import org.technohaven.core.entities.District;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *  This is a JAXB wrapper for a Broadleaf District.  There may be several reasons to extend this class.
 *  First, you may want to extend Broadleaf's DistrictImpl and expose those extensions via a RESTful
 *  service.  You may also want to suppress properties that are being serialized.  To expose new properties, or
 *  suppress properties that have been exposed, do the following:<br/>
 *  <br/>
 *  1. Extend this class   <br/>
 *  2. Override the <code>wrap</code> method. <br/>
 *  3. Within the wrap method, either override all properties that you want to set, or call <code>super.wrap(District)</code>   <br/>
 *  4. Set additional property values that you have added.  <br/>
 *  5. Set super properties to null if you do not want them serialized. (e.g. <code>super.name = null;</code>  <br/>
 */

@Component("org.technohaven.api.wrapper.CityWrapper")
@Scope("prototype")
@XmlRootElement(name = "city")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CityWrapper extends BaseWrapper implements APIWrapper<City>, APIUnwrapper<City> {

	@XmlElement
    protected Long id;

	@XmlElement
    protected DistrictWrapper district;

	@XmlElement
    protected String name;

	@XmlElement
    protected int code;
	
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public DistrictWrapper getDistrict() {
		return district;
	}

	public void setDistrict(DistrictWrapper district) {
		this.district = district;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	@Override
	public void wrapDetails(City city, HttpServletRequest request) {
		this.id = city.getId();
		if (city.getDistrict() != null) {
			DistrictWrapper districtWrapper = (DistrictWrapper) context.getBean(DistrictWrapper.class.getName());
			districtWrapper.wrapDetails(city.getDistrict(), request);
			this.district = districtWrapper;
		}
		this.name = city.getName();
		this.code = city.getCode();
	}

	@Override
	public void wrapSummary(City city, HttpServletRequest request) {
		wrapDetails(city, request);
	}

	@Override
	public City unwrap(HttpServletRequest request, ApplicationContext context) {
		CityService cityService = (CityService) context.getBean("blCityService");
		City city = cityService.createCityFromId(this.id);
		if (this.district != null) {
			city.setDistrict(this.district.unwrap(request, context));
		}
		city.setName(this.name);
		city.setCode(this.code);
		return city;
	}
}
