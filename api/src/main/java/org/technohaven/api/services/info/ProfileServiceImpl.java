package org.technohaven.api.services.info;

import org.broadleafcommerce.common.id.service.IdGenerationService;
import org.springframework.stereotype.Service;
import org.technohaven.core.dao.info.ProfileDao;
import org.technohaven.core.entities.Profile;

import javax.annotation.Resource;

@Service("blProfileService")
public class ProfileServiceImpl implements ProfileService{

    @Resource(name="blProfileDao")
    protected ProfileDao profileDao;

    @Resource(name = "blIdGenerationService")
    protected IdGenerationService idGenerationService;

    @Override
    public Profile save(Profile profile) {
    	try {
    		return profileDao.save(profile);
    	}catch(Exception ex) {
    		System.out.println(ex.getMessage());
    		throw new RuntimeException();
    	}
        
    }

    @Override
    public Profile createProfileFromId(Long profileId) {
        Profile profile = profileId != null ? findProfileById(profileId) : null;
        if (profile == null) {
            profile = profileDao.create();
            if (profileId != null) {
                profile.setId(profileId);
            } else {
                profile.setId(findNextProfileId());
            }
        }
        return profile;
    }

    @Override
    public Long findNextProfileId() {
        return idGenerationService.findNextId(Profile.class.getCanonicalName());
    }

    @Override
    public Profile findProfileById(Long profileId) {
        return profileDao.readProfileById(profileId);
    }
}
