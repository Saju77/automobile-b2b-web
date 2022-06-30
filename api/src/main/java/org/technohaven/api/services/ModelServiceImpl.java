package org.technohaven.api.services;

import org.springframework.stereotype.Service;
import org.technohaven.core.dao.ModelDao;
import org.technohaven.core.entities.VehicleModel;

import javax.annotation.Resource;
import java.util.List;

@Service("blModelService")
public class ModelServiceImpl implements ModelService{

    @Resource(name="blModelDao")
    protected ModelDao modelDao;

    @Override
    public List<VehicleModel> getVehicleModels() {
        return modelDao.getVehicleModels();
    }

    @Override
    public VehicleModel findVehicleModelById(Long modelId) {
        return modelDao.readVehicleModelById(modelId);
    }
}
