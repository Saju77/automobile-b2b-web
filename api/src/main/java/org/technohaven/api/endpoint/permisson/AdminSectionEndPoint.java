package org.technohaven.api.endpoint.permisson;

import com.broadleafcommerce.rest.api.endpoint.BaseEndpoint;
import org.broadleafcommerce.openadmin.server.security.domain.AdminSection;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.technohaven.api.services.AdminSectionService;
import org.technohaven.api.wrapper.*;
import org.technohaven.core.dao.AdminSecPermJDBCTemplate;
import org.technohaven.core.entities.AdminSecPerm;
import org.technohaven.core.entities.Showroom;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/adminSection",  produces = { MediaType.APPLICATION_JSON_VALUE })
public class AdminSectionEndPoint extends BaseEndpoint {

    @Resource(name = "blAdminSectionService")
    protected AdminSectionService adminSectionService;

    @Resource(name = "blAdminSecPermJDBCTemplate")
    protected AdminSecPermJDBCTemplate adminSecPermJDBCTemplate;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public AdminSectionsWrapper getAllAdminSections(HttpServletRequest request) {
        List<AdminSection> adminSections;
        adminSections = adminSectionService.getAdminSections();

        AdminSectionsWrapper wrapper = (AdminSectionsWrapper) context.getBean(AdminSectionsWrapper.class.getName());
        wrapper.wrapDetails(adminSections, request);
        return wrapper;
    }

    @RequestMapping(value = "/getAllAdminSecPerms", method = RequestMethod.GET)
    public AdminSecPermsWrapper getAllAdminSecPerms(HttpServletRequest request) {
        List<AdminSecPerm> adminSecPerms = adminSecPermJDBCTemplate.listAdminSecPerms();

        AdminSecPermsWrapper wrapper = (AdminSecPermsWrapper) context.getBean(AdminSecPermsWrapper.class.getName());
        wrapper.wrapDetails(adminSecPerms, request);
        return wrapper;
    }

    @RequestMapping(value = "/{adminSectionId}", method = RequestMethod.GET)
    public AdminSectionWrapper findAdminSectionById(HttpServletRequest request, @PathVariable("adminSectionId") Long adminSectionId) {
        AdminSection adminSection = adminSectionService.findAdminSectionById(adminSectionId);
        AdminSectionWrapper wrapper = (AdminSectionWrapper) context.getBean(AdminSectionWrapper.class.getName());
        wrapper.wrapDetails(adminSection, request);
        return wrapper;
    }

    @RequestMapping(value = "/findAdminSecPerm/{permissionId}", method = RequestMethod.GET)
    public AdminSecPermWrapper findAdminSectionById(HttpServletRequest request, @PathVariable("permissionId") int permissionId) {
        AdminSecPerm adminSecPerm = adminSecPermJDBCTemplate.findAdminSecPermById(permissionId).orElseThrow(() -> new RuntimeException("Admin Permission ID not found"));
        AdminSecPermWrapper wrapper = (AdminSecPermWrapper) context.getBean(AdminSecPermWrapper.class.getName());
        wrapper.wrapDetails(adminSecPerm, request);
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

    @RequestMapping(value = "/createAdminSecPerm", method = RequestMethod.POST, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public AdminSecPermWrapper createAdminSecPerm(HttpServletRequest request, @RequestBody AdminSecPermWrapper wrapper) {
        try {
            adminSecPermJDBCTemplate.create(wrapper.getAdminSecId(), wrapper.getAdminPermId());
            AdminSecPerm adminSecPerm = new AdminSecPerm();
            adminSecPerm.setAdminSecId(wrapper.getAdminSecId());
            adminSecPerm.setAdminPermId(wrapper.getAdminPermId());

            AdminSecPermWrapper response = (AdminSecPermWrapper) context.getBean(AdminSecPermWrapper.class.getName());
            response.wrapDetails(adminSecPerm, request);
            return response;
        }
        catch (Exception e){
            System.out.println("Error is : "+e.getMessage());
            return null;
        }
    }

    @RequestMapping(value = "/edit/{adminSectionId}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public AdminSectionWrapper updateAdminSection(HttpServletRequest request, @PathVariable("adminSectionId") Long adminSectionId, @RequestBody AdminSectionWrapper wrapper) {
        AdminSection adminSection = adminSectionId != null ? adminSectionService.findAdminSectionById(adminSectionId) : null;
        if (adminSection == null) {
            throw new RuntimeException();
        }
        wrapper.setId(adminSectionId);
        AdminSection update = wrapper.unwrap(request, context);

        adminSection = adminSectionService.save(update);

        AdminSectionWrapper response = (AdminSectionWrapper) context.getBean(AdminSectionWrapper.class.getName());
        response.wrapDetails(adminSection, request);

        return response;
    }

    @RequestMapping(value = "/deleteAdminSecPerm/{permissionId}", method = RequestMethod.DELETE)
    public void deleteAdminSecPerm(HttpServletRequest request, @PathVariable("permissionId") int permissionId) {
        try {
            int success = adminSecPermJDBCTemplate.delete(permissionId);
            if(success == 1){
                System.out.println("Deleted the Record admin_permission_id = " + permissionId);
            }else{
                System.out.println("Not Deleted the Record admin_permission_id = " + permissionId);
            }
        }
        catch (Exception e){
            System.out.println("Error is : "+e.getMessage());
        }
    }

}
