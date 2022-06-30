package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIUnwrapper;
import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.core.entities.VehicleModel;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Component("org.technohaven.api.wrapper.ModelWrapper")
@Scope("prototype")
@XmlRootElement(name = "model")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ModelWrapper extends BaseWrapper implements APIWrapper<VehicleModel>, APIUnwrapper<VehicleModel> {

    @XmlElement
    protected Long id;

    @XmlElement
    protected String name;

    @XmlElement
    protected String modelShortName;

    @XmlElement
    protected String modelCode;

    @XmlElement
    protected BrandWrapper brand;

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

    public String getModelShortName() {
        return modelShortName;
    }

    public void setModelShortName(String modelShortName) {
        this.modelShortName = modelShortName;
    }

    public String getModelCode() {
        return modelCode;
    }

    public void setModelCode(String modelCode) {
        this.modelCode = modelCode;
    }

    public BrandWrapper getBrand() {
        return brand;
    }

    public void setBrand(BrandWrapper brand) {
        this.brand = brand;
    }

    @Override
    public VehicleModel unwrap(HttpServletRequest request, ApplicationContext context) {
        return null;
    }

    @Override
    public void wrapDetails(VehicleModel model, HttpServletRequest request) {
        this.id=model.getId();
        this.name=model.getName();
        this.modelShortName=model.getModelShortName();
        this.modelCode=model.getModelCode();
        if (model.getBrand() != null){
            BrandWrapper brandWrapper = (BrandWrapper) context.getBean(BrandWrapper.class.getName());
            brandWrapper.wrapDetails(model.getBrand(), request);
            this.brand=brandWrapper;
        }

    }

    @Override
    public void wrapSummary(VehicleModel model, HttpServletRequest request) {
        wrapDetails(model, request);
    }
}
