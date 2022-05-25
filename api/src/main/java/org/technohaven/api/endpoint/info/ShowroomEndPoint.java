package org.technohaven.api.endpoint.info;

import com.broadleafcommerce.rest.api.endpoint.BaseEndpoint;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.technohaven.api.services.info.ShowroomService;
import org.technohaven.api.wrapper.info.ProfileWrapper;
import org.technohaven.api.wrapper.info.ShowroomWrapper;
import org.technohaven.api.wrapper.info.ShowroomsWrapper;
import org.technohaven.core.entities.Profile;
import org.technohaven.core.entities.Showroom;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/showroominfo",  produces = { MediaType.APPLICATION_JSON_VALUE })
public class ShowroomEndPoint  extends BaseEndpoint {

    @Resource(name = "blShowroomService")
    protected ShowroomService showroomService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ShowroomsWrapper getAllShowroom(HttpServletRequest request) {
        List<Showroom> showrooms;
        showrooms = showroomService.getShowrooms();

        ShowroomsWrapper wrapper = (ShowroomsWrapper) context.getBean(ShowroomsWrapper.class.getName());
        wrapper.wrapDetails(showrooms, request);
        return wrapper;
    }

    @RequestMapping(value="", method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ShowroomWrapper addShowroom(HttpServletRequest request, @RequestBody ShowroomWrapper wrapper) {

            Showroom showroom = wrapper.unwrap(request, context);

            showroomService.save(showroom);

            ShowroomWrapper response = (ShowroomWrapper) context.getBean(ShowroomWrapper.class.getName());
            response.wrapDetails(showroom, request);

            return response;
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
    @RequestMapping(value = "/edit/{showroomId}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ShowroomWrapper updateShowroom(HttpServletRequest request, @PathVariable("showroomId") Long showroomId, @RequestBody ShowroomWrapper wrapper) {
        Showroom showroom = showroomId != null ? showroomService.findShowroomById(showroomId) : null;
        if (showroom == null) {
            throw new RuntimeException();
        }
        // we unwrap the update, by default this will read the customer from the database if id is specified on the request
        wrapper.setId(showroomId);
        Showroom update = wrapper.unwrap(request, context);

        showroom = showroomService.save(update);

        ShowroomWrapper response = (ShowroomWrapper) context.getBean(ShowroomWrapper.class.getName());
        response.wrapDetails(showroom, request);

        return response;
    }
    
    @RequestMapping(value = "details/{id}", method = RequestMethod.GET)
    public ShowroomWrapper findShowroomById(HttpServletRequest request, @PathVariable("id") Long id) {
        Showroom showroom = showroomService.findShowroomById(id);
        ShowroomWrapper wrapper;
        wrapper = (ShowroomWrapper) context.getBean(ShowroomWrapper.class.getName());
        wrapper.wrapDetails(showroom, request);
        return wrapper;
    }

}
