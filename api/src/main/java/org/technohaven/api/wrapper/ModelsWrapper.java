package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.core.entities.Brand;
import org.technohaven.core.entities.VehicleModel;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@Component("org.technohaven.api.wrapper.ModelsWrapper")
@Scope("prototype")
public class ModelsWrapper extends BaseWrapper implements APIWrapper<List<VehicleModel>> {

    @XmlElement(name = "model")
    protected List<ModelWrapper> vModels = new ArrayList<ModelWrapper>();

    public List<ModelWrapper> getvModels() {
        return vModels;
    }

    public void setvModels(List<ModelWrapper> vModels) {
        this.vModels = vModels;
    }

    @Override
    public void wrapDetails(List<VehicleModel> models, HttpServletRequest request) {
        for (VehicleModel vModel : models) {
            ModelWrapper wrapper = (ModelWrapper) context.getBean(ModelWrapper.class.getName());
            wrapper.wrapSummary(vModel, request);
            vModels.add(wrapper);
        }
    }

    @Override
    public void wrapSummary(List<VehicleModel> model, HttpServletRequest request) {

    }
}
