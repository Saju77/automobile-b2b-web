package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.core.entities.City;
import org.technohaven.core.entities.District;
import org.technohaven.core.entities.Port;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Component("org.technohaven.api.wrapper.PortWrapper")
@Scope("prototype")
@XmlRootElement(name = "port")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class PortWrapper extends BaseWrapper implements APIWrapper<Port> {

    @XmlElement
    protected Long id;

    @XmlElement
    protected String name;

    @XmlElement
    protected int code;

    @XmlElement
    protected City city;

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

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    @Override
    public void wrapDetails(Port port, HttpServletRequest request) {

    }

    @Override
    public void wrapSummary(Port port, HttpServletRequest request) {
        this.id = port.getId();
        this.name = port.getName();
        this.code = port.getCode();
        this.city = port.getCityId();
    }
}
