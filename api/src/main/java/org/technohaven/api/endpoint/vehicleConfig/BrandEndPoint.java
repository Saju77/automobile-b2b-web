package org.technohaven.api.endpoint.vehicleConfig;

import com.broadleafcommerce.rest.api.endpoint.BaseEndpoint;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.technohaven.api.services.BrandService;
import org.technohaven.api.services.ShowroomService;
import org.technohaven.api.wrapper.BrandWrapper;
import org.technohaven.api.wrapper.BrandsWrapper;
import org.technohaven.api.wrapper.ShowroomWrapper;
import org.technohaven.api.wrapper.ShowroomsWrapper;
import org.technohaven.core.entities.Brand;
import org.technohaven.core.entities.Showroom;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/brandinformation",  produces = { MediaType.APPLICATION_JSON_VALUE })
public class BrandEndPoint extends BaseEndpoint {

    @Resource(name = "blBrandService")
    protected BrandService brandService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public BrandsWrapper getAllBrand(HttpServletRequest request) {
        List<Brand> brands;
        brands = brandService.getBrands();

        BrandsWrapper wrapper = (BrandsWrapper) context.getBean(BrandsWrapper.class.getName());
        wrapper.wrapDetails(brands, request);
        return wrapper;
    }

    @RequestMapping(value = "/{brandId}", method = RequestMethod.GET)
    public BrandWrapper findBrandById(HttpServletRequest request, @PathVariable("brandId") Long brandId) {
        Brand brand = brandService.findBrandById(brandId);
        BrandWrapper wrapper = (BrandWrapper) context.getBean(BrandWrapper.class.getName());
        wrapper.wrapDetails(brand, request);
        return wrapper;
    }

}
