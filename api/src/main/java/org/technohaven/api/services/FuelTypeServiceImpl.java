package org.technohaven.api.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.technohaven.core.dao.FuelTypeDao;
import org.technohaven.core.entities.FuelType;

import javax.annotation.Resource;
import java.util.List;

@Service("blFuelTypeService")
public class FuelTypeServiceImpl implements FuelTypeService{

    protected static final Log LOG = LogFactory.getLog(FuelTypeServiceImpl.class);

    @Resource(name="blFuelTypeDao")
    protected FuelTypeDao fuelTypeDao;

    @Override
    public List<FuelType> getAllFuelType() {
        return fuelTypeDao.readAllFuelType();
    }
}
