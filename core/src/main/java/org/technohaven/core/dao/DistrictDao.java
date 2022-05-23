package org.technohaven.core.dao;

import javax.annotation.Nonnull;

import org.technohaven.core.entities.District;

import java.util.List;

public interface DistrictDao {

	 @Nonnull
	 District save(@Nonnull District district);

	 @Nonnull
	 List<District> getDistricts();
	 
	 /**
	 * Retrieve a subset of all categories
	 *
	 * @param limit the maximum number of results, defaults to 20
	 * @param offset the starting point in the record set, defaults to 0
	 * @return
	 */
	 @Nonnull
	 public List<District> readAllDistrict(@Nonnull int limit, @Nonnull int offset);
	 
	 @Nonnull
	 public List<District> readDistrictsByName(@Nonnull String districtName, int limit, int offset);
	
}
