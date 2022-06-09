package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIUnwrapper;
import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.api.services.ShowroomService;
import org.technohaven.api.services.WarehouseService;
import org.technohaven.core.entities.Showroom;
import org.technohaven.core.entities.Warehouse;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Component("org.technohaven.api.wrapper.WarehouseWrapper")
@Scope("prototype")
@XmlRootElement(name = "warehouse")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class WarehouseWrapper extends BaseWrapper implements APIWrapper<Warehouse>, APIUnwrapper<Warehouse> {

    @XmlElement
    protected Long id;

    @XmlElement
    protected String name;

    @XmlElement
    protected String phoneNumber;

    @XmlElement
    protected CityWrapper city;

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

    public CityWrapper getCity() {
        return city;
    }

    public void setCity(CityWrapper city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public void wrapDetails(Warehouse model, HttpServletRequest request) {
        this.id = model.getId();
        this.name = model.getName();
        this.phoneNumber = model.getPhoneNumber();
        if (model.getCity() != null) {
            CityWrapper cityWrapper = (CityWrapper) context.getBean(CityWrapper.class.getName());
            cityWrapper.wrapDetails(model.getCity(), request);
            this.city = cityWrapper;
        }
        this.address = model.getAddress();
    }

    @Override
    public void wrapSummary(Warehouse model, HttpServletRequest request) {
        wrapDetails(model, request);
    }

    @Override
    public Warehouse unwrap(HttpServletRequest request, ApplicationContext context) {
        WarehouseService warehouseService = (WarehouseService) context.getBean("blWarehouseService");
        Warehouse warehouse = warehouseService.createWarehouseFromId(this.id);
        warehouse.setName(this.name);
        warehouse.setPhoneNumber(this.phoneNumber);
        if (this.city != null) {
            warehouse.setCity(this.city.unwrap(request, context));
        }
        warehouse.setAddress(this.address);
        return warehouse;
    }
}
