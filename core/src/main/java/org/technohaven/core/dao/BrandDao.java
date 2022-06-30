package org.technohaven.core.dao;

import org.technohaven.core.entities.Brand;
import org.technohaven.core.entities.Showroom;

import javax.annotation.Nonnull;
import java.util.List;

public interface BrandDao {

    @Nonnull
    List<Brand> getBrands();

    @Nonnull
    Brand readBrandById(@Nonnull Long brandId);

}
