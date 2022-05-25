package org.technohaven.api.services;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.id.service.IdGenerationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.technohaven.api.wrapper.DistrictWrapper;
import org.technohaven.core.dao.DistrictDao;
import org.technohaven.core.entities.District;
import org.technohaven.core.entities.Showroom;

import javax.annotation.Resource;
import java.util.List;

@Service("blDistrictService")
public class DistrictServiceImpl implements DistrictService {

	protected static final Log LOG = LogFactory.getLog(DistrictServiceImpl.class);

	@Resource(name="blDistrictDao")
	protected DistrictDao districtDao;

	@Resource(name = "blIdGenerationService")
	protected IdGenerationService idGenerationService;

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

	@Override
	public District createDistrictFromId(Long districtId) {
		District district = districtId != null ? findDistrictById(districtId) : null;
		if (district == null) {
			district = districtDao.create();
			if (districtId != null) {
				district.setId(districtId);
			} else {
				district.setId(findNextDistrictId());
			}
		}
		return district;
	}

	@Override
	public District findDistrictById(Long districtId) {
		return districtDao.readDistrictById(districtId);
	}

	@Override
	public Long findNextDistrictId() {
		return idGenerationService.findNextId(District.class.getCanonicalName());
	}

}
