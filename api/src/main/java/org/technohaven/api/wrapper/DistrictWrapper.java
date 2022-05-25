package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIUnwrapper;
import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.api.services.CityService;
import org.technohaven.api.services.DistrictService;
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

@Component("org.technohaven.api.wrapper.DistrictWrapper")
@Scope("prototype")
@XmlRootElement(name = "district")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class DistrictWrapper extends BaseWrapper implements APIWrapper<District>, APIUnwrapper<District> {

	@XmlElement
    protected Long id;

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
	public void wrapDetails(District model, HttpServletRequest request) {

	}

	@Override
	public void wrapSummary(District district, HttpServletRequest request) {
		this.id = district.getId();
        this.name = district.getName();
        this.code = district.getCode();
	}

	@Override
	public District unwrap(HttpServletRequest request, ApplicationContext context) {
		DistrictService districtService = (DistrictService) context.getBean("blDistrictService");
		District district = districtService.createDistrictFromId(this.id);
		district.setName(this.name);
		district.setCode(this.code);
		return district;
	}
}
