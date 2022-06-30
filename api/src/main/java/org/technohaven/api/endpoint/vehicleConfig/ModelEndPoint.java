package org.technohaven.api.endpoint.vehicleConfig;

import com.broadleafcommerce.rest.api.endpoint.BaseEndpoint;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.technohaven.api.services.BrandService;
import org.technohaven.api.services.ModelService;
import org.technohaven.api.wrapper.BrandWrapper;
import org.technohaven.api.wrapper.BrandsWrapper;
import org.technohaven.api.wrapper.ModelWrapper;
import org.technohaven.api.wrapper.ModelsWrapper;
import org.technohaven.core.entities.Brand;
import org.technohaven.core.entities.VehicleModel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/modelinformation",  produces = { MediaType.APPLICATION_JSON_VALUE })
public class ModelEndPoint extends BaseEndpoint {

    @Resource(name = "blModelService")
    protected ModelService modelService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelsWrapper getAllVModel(HttpServletRequest request) {
        List<VehicleModel> vModels;
        vModels = modelService.getVehicleModels();

        ModelsWrapper wrapper = (ModelsWrapper) context.getBean(ModelsWrapper.class.getName());
        wrapper.wrapDetails(vModels, request);
        return wrapper;
    }

    @RequestMapping(value = "/{modelId}", method = RequestMethod.GET)
    public ModelWrapper findModelById(HttpServletRequest request, @PathVariable("modelId") Long modelId) {
        VehicleModel vModel = modelService.findVehicleModelById(modelId);
        ModelWrapper wrapper = (ModelWrapper) context.getBean(ModelWrapper.class.getName());
        wrapper.wrapDetails(vModel, request);
        return wrapper;
    }
}
