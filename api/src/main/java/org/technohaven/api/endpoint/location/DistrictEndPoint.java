package org.technohaven.api.endpoint.location;

import com.broadleafcommerce.rest.api.endpoint.BaseEndpoint;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.technohaven.api.services.DistrictService;
import org.technohaven.api.wrapper.DistrictWrapper;
import org.technohaven.api.wrapper.DistrictsWrapper;
import org.technohaven.core.entities.District;

import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/district",  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class DistrictEndPoint extends BaseEndpoint {

    @Resource(name = "blDistrictService")
    protected DistrictService districtService;
        
    
  @RequestMapping(value = "", method = RequestMethod.GET)
  public DistrictsWrapper getAllDistrict(HttpServletRequest request) {
      List<District> districts;
      districts = districtService.getDistricts();
      
      DistrictsWrapper wrapper = (DistrictsWrapper) context.getBean(DistrictsWrapper.class.getName());
      wrapper.wrapDetails(districts, request);
      return wrapper;
  }
  
  @RequestMapping(value = "/findDistricts", method = RequestMethod.GET)
  public DistrictsWrapper findDistricts(HttpServletRequest request,
		  @RequestParam(value = "name", required = false) String name,
          @RequestParam(value = "limit", defaultValue = "20") int limit,
          @RequestParam(value = "offset", defaultValue = "0") int offset) {    	
      List<District> districts;      
      
      if (name != null) {
    	  districts = districtService.findDistrictsByName(name, limit, offset);
      } else {
    	  districts = districtService.findAllDistricts(limit, offset);
      }
      
      DistrictsWrapper wrapper = (DistrictsWrapper) context.getBean(DistrictsWrapper.class.getName());
      wrapper.wrapDetails(districts, request);
      return wrapper;
  }	
     
}
