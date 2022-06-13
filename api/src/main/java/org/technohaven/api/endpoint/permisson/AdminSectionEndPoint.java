package org.technohaven.api.endpoint.permisson;

import com.broadleafcommerce.rest.api.endpoint.BaseEndpoint;
import org.broadleafcommerce.openadmin.server.security.domain.AdminSection;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.technohaven.api.services.AdminSectionService;
import org.technohaven.api.services.ModelService;
import org.technohaven.api.wrapper.*;
import org.technohaven.core.entities.City;
import org.technohaven.core.entities.VehicleModel;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/adminSection",  produces = { MediaType.APPLICATION_JSON_VALUE })
public class AdminSectionEndPoint extends BaseEndpoint {

    @Resource(name = "blAdminSectionService")
    protected AdminSectionService adminSectionService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public AdminSectionsWrapper getAllAdminSections(HttpServletRequest request) {
        List<AdminSection> adminSections;
        adminSections = adminSectionService.getAdminSections();

        AdminSectionsWrapper wrapper = (AdminSectionsWrapper) context.getBean(AdminSectionsWrapper.class.getName());
        wrapper.wrapDetails(adminSections, request);
        return wrapper;
    }

    @RequestMapping(value = "/{adminSectionId}", method = RequestMethod.GET)
    public AdminSectionWrapper findAdminSectionById(HttpServletRequest request, @PathVariable("adminSectionId") Long adminSectionId) {
        AdminSection adminSection = adminSectionService.findAdminSectionById(adminSectionId);
        AdminSectionWrapper wrapper = (AdminSectionWrapper) context.getBean(AdminSectionWrapper.class.getName());
        wrapper.wrapDetails(adminSection, request);
        return wrapper;
    }

    @RequestMapping(value="", method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    public AdminSectionWrapper addAdminSection(HttpServletRequest request, @RequestBody AdminSectionWrapper wrapper) {

        AdminSection adminSection = wrapper.unwrap(request, context);

        adminSection = adminSectionService.save(adminSection);

        AdminSectionWrapper response = (AdminSectionWrapper) context.getBean(AdminSectionWrapper.class.getName());
        response.wrapDetails(adminSection, request);

        return response;
    }

}
