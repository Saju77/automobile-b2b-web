package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.core.entities.Brand;
import org.technohaven.core.entities.Showroom;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@Component("org.technohaven.api.wrapper.BrandsWrapper")
@Scope("prototype")
public class BrandsWrapper extends BaseWrapper implements APIWrapper<List<Brand>> {

    @XmlElement(name = "brand")
    protected List<BrandWrapper> brands = new ArrayList<BrandWrapper>();

    public List<BrandWrapper> getBrands() {
        return brands;
    }

    public void setBrands(List<BrandWrapper> brands) {
        this.brands = brands;
    }

    @Override
    public void wrapDetails(List<Brand> models, HttpServletRequest request) {
        for (Brand brand : models) {
            BrandWrapper wrapper = (BrandWrapper) context.getBean(BrandWrapper.class.getName());
            wrapper.wrapSummary(brand, request);
            brands.add(wrapper);
        }
    }

    @Override
    public void wrapSummary(List<Brand> model, HttpServletRequest request) {

    }
}
