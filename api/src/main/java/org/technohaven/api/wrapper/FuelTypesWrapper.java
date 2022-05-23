package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.core.entities.FuelType;
import org.technohaven.core.entities.Transmission;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@Component("org.technohaven.api.wrapper.FuelTypesWrapper")
@Scope("prototype")
public class FuelTypesWrapper extends BaseWrapper implements APIWrapper<List<FuelType>> {

    @XmlElement(name = "fuelType")
    protected List<FuelTypeWrapper> fuelTypes = new ArrayList<FuelTypeWrapper>();

    public List<FuelTypeWrapper> getFuelTypes() {
        return fuelTypes;
    }

    public void setFuelTypes(List<FuelTypeWrapper> fuelTypes) {
        this.fuelTypes = fuelTypes;
    }

    @Override
    public void wrapDetails(List<FuelType> models, HttpServletRequest request) {
        for (FuelType fuelType : models) {
            FuelTypeWrapper wrapper = (FuelTypeWrapper) context.getBean(FuelTypeWrapper.class.getName());
            wrapper.wrapSummary(fuelType, request);
            fuelTypes.add(wrapper);
        }
    }

    @Override
    public void wrapSummary(List<FuelType> models, HttpServletRequest request) {

    }
}
