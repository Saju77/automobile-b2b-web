package org.technohaven.api.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.technohaven.core.dao.TransmissionDao;
import org.technohaven.core.entities.Transmission;

import javax.annotation.Resource;
import java.util.List;

@Service("blTransmissionService")
public class TransmissionServiceImpl implements TransmissionService{

    protected static final Log LOG = LogFactory.getLog(TransmissionServiceImpl.class);

    @Resource(name="blTransmissionDao")
    protected TransmissionDao transmissionDao;

    @Override
    public List<Transmission> getAllTransmission() {
        return transmissionDao.readAllTransmission();
    }

}
