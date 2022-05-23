package org.technohaven.api.endpoint.info;

import com.broadleafcommerce.rest.api.endpoint.BaseEndpoint;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.technohaven.api.services.DistrictService;
import org.technohaven.api.services.info.ShowroomService;
import org.technohaven.api.wrapper.DistrictsWrapper;
import org.technohaven.api.wrapper.info.ProfileWrapper;
import org.technohaven.api.wrapper.info.ShowroomWrapper;
import org.technohaven.api.wrapper.info.ShowroomsWrapper;
import org.technohaven.core.entities.District;
import org.technohaven.core.entities.Profile;
import org.technohaven.core.entities.Showroom;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/showroominfo",  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
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
    
    @RequestMapping(value = "details/{id}", method = RequestMethod.GET)
    public ShowroomWrapper findShowroomById(HttpServletRequest request, @PathVariable("id") Long id) {
        Showroom showroom = showroomService.findShowroomById(id);
        ShowroomWrapper wrapper;
        wrapper = (ShowroomWrapper) context.getBean(ShowroomWrapper.class.getName());
        wrapper.wrapDetails(showroom, request);
        return wrapper;
    }

}
