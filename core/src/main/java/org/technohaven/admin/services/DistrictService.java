package org.technohaven.admin.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.technohaven.admin.entities.District;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Service
public class DistrictService {

    @PersistenceContext(unitName = "blPU")
    private EntityManager em;

    @Transactional
    public District save(District district) {
        return em.merge(district);
    }
    
    public String getString(String value) {
    	System.out.println("The value is "+value);
        return value;
    }

}
