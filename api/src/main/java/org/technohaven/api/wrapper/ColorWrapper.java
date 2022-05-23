package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.core.entities.Color;
import org.technohaven.core.entities.Port;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Component("org.technohaven.api.wrapper.ColorWrapper")
@Scope("prototype")
@XmlRootElement(name = "color")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ColorWrapper extends BaseWrapper implements APIWrapper<Color> {

    @XmlElement
    protected Long id;

    @XmlElement
    protected String name;

    @XmlElement
    protected int code;

    @XmlElement
    protected String shortName;

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

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    @Override
    public void wrapDetails(Color color, HttpServletRequest request) {

    }

    @Override
    public void wrapSummary(Color color, HttpServletRequest request) {
        this.id = color.getId();
        this.name = color.getName();
        this.code = color.getCode();
        this.shortName = color.getShortName();
    }
}
