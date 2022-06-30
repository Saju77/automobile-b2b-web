package org.technohaven.api.endpoint.info;

import com.broadleafcommerce.rest.api.endpoint.BaseEndpoint;
import com.broadleafcommerce.rest.api.exception.BroadleafWebServicesException;
import com.broadleafcommerce.rest.api.wrapper.CustomerPaymentWrapper;
import com.broadleafcommerce.rest.api.wrapper.OrderWrapper;
import org.apache.http.HttpStatus;
import org.broadleafcommerce.common.web.controller.annotation.FrameworkMapping;
import org.broadleafcommerce.core.order.domain.Order;
import org.broadleafcommerce.core.order.service.exception.RemoveFromCartException;
import org.broadleafcommerce.profile.core.domain.Customer;
import org.broadleafcommerce.profile.core.domain.CustomerPayment;
import org.broadleafcommerce.profile.web.core.CustomerState;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.technohaven.api.services.ShowroomService;
import org.technohaven.api.wrapper.ShowroomWrapper;
import org.technohaven.api.wrapper.ShowroomsWrapper;
import org.technohaven.core.entities.Showroom;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(value = "/showroominfo",  produces = { MediaType.APPLICATION_JSON_VALUE })
public class ShowroomEndPoint extends BaseEndpoint {

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

            showroom = showroomService.save(showroom);

            ShowroomWrapper response = (ShowroomWrapper) context.getBean(ShowroomWrapper.class.getName());
            response.wrapDetails(showroom, request);

            return response;
    }

    @RequestMapping(value = "/delete/{showroomId}", method = RequestMethod.DELETE)
    public ShowroomWrapper removeShowroom(HttpServletRequest request,
            @PathVariable("showroomId") Long showroomId){
        Showroom showroom = showroomId != null ? showroomService.findShowroomById(showroomId) : null;
        if (showroom == null) {
            throw new RuntimeException();
        }

        showroomService.removeShowroom(showroom);

        ShowroomWrapper response = (ShowroomWrapper) context.getBean(ShowroomWrapper.class.getName());
        response.wrapDetails(showroom, request);

        return response;
    }

    @RequestMapping(value = "/edit/{showroomId}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public ShowroomWrapper updateShowroom(HttpServletRequest request, @PathVariable("showroomId") Long showroomId, @RequestBody ShowroomWrapper wrapper) {
    	Showroom showroom = showroomId != null ? showroomService.findShowroomById(showroomId) : null;
        if (showroom == null) {
            throw new RuntimeException();
        }
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
        ShowroomWrapper wrapper = (ShowroomWrapper) context.getBean(ShowroomWrapper.class.getName());
        wrapper.wrapDetails(showroom, request);
        return wrapper;
    }

}
