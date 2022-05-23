package org.technohaven.api.endpoint.productCon;

import com.broadleafcommerce.rest.api.endpoint.BaseEndpoint;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.technohaven.api.services.FuelTypeService;
import org.technohaven.api.services.TransmissionService;
import org.technohaven.api.wrapper.FuelTypesWrapper;
import org.technohaven.api.wrapper.TransmissionsWrapper;
import org.technohaven.core.entities.FuelType;
import org.technohaven.core.entities.Transmission;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/fuelType",  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class FuelTypeEndPoint extends BaseEndpoint {

    @Resource(name = "blFuelTypeService")
    protected FuelTypeService fuelTypeService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public FuelTypesWrapper getAllFuelType(HttpServletRequest request) {
        List<FuelType> fuelTypes;
        fuelTypes = fuelTypeService.getAllFuelType();

        FuelTypesWrapper wrapper = (FuelTypesWrapper) context.getBean(FuelTypesWrapper.class.getName());
        wrapper.wrapDetails(fuelTypes, request);
        return wrapper;
    }

}
