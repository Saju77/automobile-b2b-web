package org.technohaven.api.wrapper.info;

import org.broadleafcommerce.common.rest.api.wrapper.APIUnwrapper;
import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.api.services.info.ShowroomService;
import org.technohaven.core.entities.City;
import org.technohaven.core.entities.District;
import org.technohaven.core.entities.Profile;
import org.technohaven.core.entities.Showroom;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Component("org.technohaven.api.wrapper.info.ShowroomWrapper")
@Scope("prototype")
@XmlRootElement(name = "showroom")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ShowroomWrapper extends BaseWrapper implements APIWrapper<Showroom>, APIUnwrapper<Showroom> {

    @XmlElement
    protected Long id;

    @XmlElement
    protected String name;

    @XmlElement
    protected String phoneNumber;

    @XmlElement
    protected District district;

    @XmlElement
    protected City city;

    @XmlElement
    protected String address;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void wrapDetails(Showroom showroom, HttpServletRequest request) {
    	this.id = showroom.getId();
        this.name = showroom.getName();
        this.phoneNumber = showroom.getPhoneNumber();
        this.district = showroom.getDistrict();
        this.city = showroom.getCity();
        this.address = showroom.getAddress();
    }

    @Override
    public void wrapSummary(Showroom showroom, HttpServletRequest request) {
        this.id = showroom.getId();
        this.name = showroom.getName();
        this.phoneNumber = showroom.getPhoneNumber();
        this.district = showroom.getDistrict();
        this.city = showroom.getCity();
        this.address = showroom.getAddress();
    }

    @Override
    public Showroom unwrap(HttpServletRequest request, ApplicationContext context) {
        ShowroomService showroomService = (ShowroomService) context.getBean("blShowroomService");
        Showroom showroom = showroomService.createShowroomFromId(this.id);
        showroom.setName(this.name);
        showroom.setPhoneNumber(this.phoneNumber);
        showroom.setDistrict(this.district);
        showroom.setCity(this.city);
        showroom.setAddress(this.address);
        return showroom;
    }
}
