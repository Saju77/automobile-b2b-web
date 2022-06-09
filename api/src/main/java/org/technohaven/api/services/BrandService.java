package org.technohaven.api.services;

import org.technohaven.core.entities.Brand;
import org.technohaven.core.entities.Showroom;

import java.util.List;

public interface BrandService {

    List<Brand> getBrands();

    Brand findBrandById(Long brandId);

}
