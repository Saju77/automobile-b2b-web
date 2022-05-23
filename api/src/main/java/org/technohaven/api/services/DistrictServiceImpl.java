package org.technohaven.api.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.technohaven.api.wrapper.DistrictWrapper;
import org.technohaven.core.dao.DistrictDao;
import org.technohaven.core.entities.District;

import javax.annotation.Resource;
import java.util.List;

@Service("blDistrictService")
public class DistrictServiceImpl implements DistrictService {

	protected static final Log LOG = LogFactory.getLog(DistrictServiceImpl.class);

	@Resource(name="blDistrictDao")
	protected DistrictDao districtDao;

    @Override
    public List<District> getDistricts() {
        return districtDao.getDistricts();
    }

	@Override
	public List<District> findAllDistricts(int limit, int offset) {
		return districtDao.readAllDistrict(limit, offset);
	}
	
	@Override
    public List<District> findDistrictsByName(String districtName, int limit, int offset) {
        return districtDao.readDistrictsByName(districtName, limit, offset);
    }
	
}
