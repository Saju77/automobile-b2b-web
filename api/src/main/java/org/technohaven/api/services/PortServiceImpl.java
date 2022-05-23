package org.technohaven.api.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Service;
import org.technohaven.core.dao.CityDao;
import org.technohaven.core.dao.PortDao;
import org.technohaven.core.entities.City;
import org.technohaven.core.entities.Port;

import javax.annotation.Resource;
import java.util.List;

@Service("blPortService")
public class PortServiceImpl implements PortService{

    protected static final Log LOG = LogFactory.getLog(PortServiceImpl.class);

    @Resource(name="blPortDao")
    protected PortDao portDao;

    @Override
    public List<Port> getAllPort() {
        return portDao.readAllPort();
    }

    @Override
    public List<Port> findPorts(int limit, int offset) {
        return portDao.readPorts(limit, offset);
    }

    @Override
    public List<Port> findPortsByName(String portName, int limit, int offset) {
        return portDao.readPortsByName(portName, limit, offset);
    }
}
