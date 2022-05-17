package org.technohaven.api.wrapper;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;

import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.core.entities.District;

@Component("org.technohaven.api.wrapper.DistrictsWrapper")
@Scope("prototype")
public class DistrictsWrapper extends BaseWrapper implements APIWrapper<List<District>> {
	
	@XmlElement(name = "district")
    protected List<DistrictWrapper> districts = new ArrayList<DistrictWrapper>();

	public List<DistrictWrapper> getDistricts() {
		return districts;
	}

	public void setDistricts(List<DistrictWrapper> districts) {
		this.districts = districts;
	}

	@Override
	public void wrapDetails(List<District> models, HttpServletRequest request) {
		
		for (District district : models) {
			DistrictWrapper wrapper = (DistrictWrapper) context.getBean(DistrictWrapper.class.getName());
            wrapper.wrapSummary(district, request);
            districts.add(wrapper);
        }
		
	}

	@Override
	public void wrapSummary(List<District> model, HttpServletRequest request) {
		// TODO Auto-generated method stub
		
	}
	
	

}
