package org.technohaven.api.services;

import org.springframework.stereotype.Service;
import org.technohaven.core.dao.BrandDao;
import org.technohaven.core.entities.Brand;

import javax.annotation.Resource;
import java.util.List;

@Service("blBrandService")
public class BrandServiceImpl implements BrandService{

    @Resource(name="blBrandDao")
    protected BrandDao brandDao;


    @Override
    public List<Brand> getBrands() {
        return brandDao.getBrands();
    }

    @Override
    public Brand findBrandById(Long brandId) {
        return brandDao.readBrandById(brandId);
    }
}
