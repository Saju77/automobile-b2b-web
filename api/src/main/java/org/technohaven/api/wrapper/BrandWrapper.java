package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIUnwrapper;
import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.core.entities.Brand;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Component("org.technohaven.api.wrapper.BrandWrapper")
@Scope("prototype")
@XmlRootElement(name = "showroom")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class BrandWrapper extends BaseWrapper implements APIWrapper<Brand>, APIUnwrapper<Brand> {

    @XmlElement
    protected Long id;

    @XmlElement
    protected String name;

    @XmlElement
    protected String brandShortName;

    @XmlElement
    protected String brandCode;

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

    public String getBrandShortName() {
        return brandShortName;
    }

    public void setBrandShortName(String brandShortName) {
        this.brandShortName = brandShortName;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    @Override
    public Brand unwrap(HttpServletRequest request, ApplicationContext context) {
        return null;
    }

    @Override
    public void wrapDetails(Brand brand, HttpServletRequest request) {
        this.id= brand.getId();
        this.name=brand.getName();
        this.brandShortName=brand.getBrandShortName();
        this.brandCode=brand.getBrandCode();
    }

    @Override
    public void wrapSummary(Brand brand, HttpServletRequest request) {
        wrapDetails(brand, request);
    }
}
