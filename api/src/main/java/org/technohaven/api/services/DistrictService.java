package org.technohaven.api.services;

import org.technohaven.core.entities.District;
import org.technohaven.core.entities.Showroom;

import java.util.List;

public interface DistrictService {

    List<District> getDistricts();
    
    List<District> findAllDistricts(int limit, int offset);
    
    /**
     * Retrieve a list of {@code District} instances based on the search criteria
     *
     * @param districtName the name of the district to search by
     * @param limit the maximum number of results to return
     * @param offset the starting point of the records to return
     * @return a list of district instances that match the search criteria
     */
    List<District> findDistrictsByName(String districtName, int limit, int offset);

    District createDistrictFromId(Long districtId);

    public District findDistrictById(Long districtId);

    public Long findNextDistrictId();
	
}
