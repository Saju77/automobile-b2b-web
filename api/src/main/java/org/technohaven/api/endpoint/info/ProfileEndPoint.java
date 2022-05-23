package org.technohaven.api.endpoint.info;

import com.broadleafcommerce.rest.api.endpoint.BaseEndpoint;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.technohaven.api.services.info.ProfileService;
import org.technohaven.api.wrapper.info.ProfileWrapper;
import org.technohaven.core.entities.Profile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/profile",  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class ProfileEndPoint extends BaseEndpoint {

    @Resource(name = "blProfileService")
    protected ProfileService profileService;

    @RequestMapping(value="", method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
    public ProfileWrapper addProfile(HttpServletRequest request, @RequestBody ProfileWrapper wrapper) {
    	try {
    		Profile profile = wrapper.unwrap(request, context);
            
            profileService.save(profile);

            ProfileWrapper response = (ProfileWrapper) context.getBean(ProfileWrapper.class.getName());
            response.wrapDetails(profile, request);

            return response;
    	}
    	catch(Exception ex) {
    		System.out.println(ex.getMessage());
    		throw new RuntimeException();
    	}
        
    }

//    @FrameworkMapping(method = RequestMethod.POST,
//            consumes = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
//    public CustomerWrapper addCustomer(HttpServletRequest request, @RequestBody CustomerWrapper wrapper) {
//        Customer customer = wrapper.unwrap(request, context);
//        if (StringUtils.isEmpty(customer.getUsername())) {
//            // if no username was specified, we will use the email address instead
//            String userName = (StringUtils.isNotBlank(customer.getEmailAddress()) ? customer.getEmailAddress() : "");
//            customer.setUsername(userName);
//        }
//        customer = customerService.saveCustomer(customer);
//
//        CustomerWrapper response = (CustomerWrapper) context.getBean(CustomerWrapper.class.getName());
//        response.wrapDetails(customer, request);
//
//        return response;
//    }
    
//    @RequestMapping(value = "details", method = RequestMethod.GET)
//    public ProfileWrapper findProfile(HttpServletRequest request) {
//        Profile profile = profileService.findProfile;
//        ProfileWrapper wrapper;
//        wrapper = (ProfileWrapper) context.getBean(ProfileWrapper.class.getName());
//        wrapper.wrapDetails(profile, request);
//        return wrapper;
//    }

    @RequestMapping(value = "details/{id}", method = RequestMethod.GET)
    public ProfileWrapper findProfileById(HttpServletRequest request, @PathVariable("id") Long id) {
        Profile profile = profileService.findProfileById(id);
        ProfileWrapper wrapper;
        wrapper = (ProfileWrapper) context.getBean(ProfileWrapper.class.getName());
        wrapper.wrapDetails(profile, request);
        return wrapper;
    }

}
