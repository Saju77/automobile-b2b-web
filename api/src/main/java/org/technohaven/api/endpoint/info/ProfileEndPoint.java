package org.technohaven.api.endpoint.info;

import com.broadleafcommerce.rest.api.endpoint.BaseEndpoint;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.technohaven.api.services.info.ProfileService;
import org.technohaven.api.wrapper.info.ProfileWrapper;
import org.technohaven.api.wrapper.info.ProfilesWrapper;
import org.technohaven.core.entities.Profile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/profile",  produces = { MediaType.APPLICATION_JSON_VALUE })
public class ProfileEndPoint extends BaseEndpoint {

    @Resource(name = "blProfileService")
    protected ProfileService profileService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ProfilesWrapper getAllProfile(HttpServletRequest request) {
        List<Profile> profiles;
        profiles = profileService.getProfiles();

        ProfilesWrapper wrapper = (ProfilesWrapper) context.getBean(ProfilesWrapper.class.getName());
        wrapper.wrapDetails(profiles, request);
        return wrapper;
    }

    @RequestMapping(value="", method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ProfileWrapper addProfile(HttpServletRequest request, @RequestBody ProfileWrapper wrapper) {
    	try {
    		Profile profile = wrapper.unwrap(request, context);
            
            profileService.saveProfile(profile);

            ProfileWrapper response = (ProfileWrapper) context.getBean(ProfileWrapper.class.getName());
            response.wrapDetails(profile, request);

            return response;
    	}
    	catch(Exception ex) {
    		System.out.println(ex.getMessage());
    		throw new RuntimeException();
    	}
        
    }

//    @ApiResponses({
//            @ApiResponse(code = org.apache.http.HttpStatus.SC_NOT_FOUND,
//                    message = BroadleafWebServicesException.CUSTOMER_NOT_FOUND,
//                    response = ErrorWrapper.class
//            )
//    })
//    @ApiImplicitParams(
//            @ApiImplicitParam(name = RestApiCustomerStateFilter.CUSTOMER_ID_ATTRIBUTE,
//                    paramType = "query",
//                    dataType = "long",
//                    required = true)
//    )
    @RequestMapping(value = "/edit/{profileId}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ProfileWrapper updateProfile(HttpServletRequest request, @PathVariable("profileId") Long profileId, @RequestBody ProfileWrapper wrapper) {
        Profile profile = profileId != null ? profileService.findProfileById(profileId) : null;
        if (profile == null) {
            throw new RuntimeException();
        }
        // we unwrap the update, by default this will read the customer from the database if id is specified on the request
        wrapper.setId(profileId);
        Profile update = wrapper.unwrap(request, context);

        profile = profileService.saveProfile(update);

        ProfileWrapper response = (ProfileWrapper) context.getBean(ProfileWrapper.class.getName());
        response.wrapDetails(profile, request);

        return response;
    }

    @RequestMapping(value = "/details/{id}", method = RequestMethod.GET)
    public ProfileWrapper findProfileById(HttpServletRequest request, @PathVariable("id") Long id) {
        Profile profile = profileService.findProfileById(id);
        ProfileWrapper wrapper;
        wrapper = (ProfileWrapper) context.getBean(ProfileWrapper.class.getName());
        wrapper.wrapDetails(profile, request);
        return wrapper;
    }

}
