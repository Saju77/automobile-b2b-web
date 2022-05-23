package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.core.entities.Transmission;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Component("org.technohaven.api.wrapper.TransmissionWrapper")
@Scope("prototype")
@XmlRootElement(name = "transmission")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class TransmissionWrapper extends BaseWrapper implements APIWrapper<Transmission> {

    @XmlElement
    protected Long id;

    @XmlElement
    protected String name;

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

    @Override
    public void wrapDetails(Transmission transmission, HttpServletRequest request) {

    }

    @Override
    public void wrapSummary(Transmission transmission, HttpServletRequest request) {
        this.id = transmission.getId();
        this.name = transmission.getName();
    }
}
