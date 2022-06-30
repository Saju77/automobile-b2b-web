package org.technohaven.api.services;

import org.broadleafcommerce.common.id.service.IdGenerationService;
import org.broadleafcommerce.common.util.TransactionUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.technohaven.core.dao.ProfileDao;
import org.technohaven.core.entities.Profile;

import javax.annotation.Resource;
import java.util.List;

@Service("blProfileService")
public class ProfileServiceImpl implements ProfileService{

    @Resource(name="blProfileDao")
    protected ProfileDao profileDao;

    @Resource(name = "blIdGenerationService")
    protected IdGenerationService idGenerationService;

    @Override
    @Transactional(TransactionUtils.DEFAULT_TRANSACTION_MANAGER)
    public Profile saveProfile(Profile profile) {
    	try {
            if (profile.getId() == null) {
                profile.setId(findNextProfileId());
            }
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
    public List<Profile> getProfiles() {
        return profileDao.getProfiles();
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
