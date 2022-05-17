package org.technohaven.admin.controllers;

import org.broadleafcommerce.openadmin.web.controller.AdminAbstractController;
import org.broadleafcommerce.openadmin.web.controller.entity.AdminBasicEntityController;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/" + CityController.SECTION_KEY)
@Secured("ROLE_PERMISSION_OTHER_DEFAULT")
public class CityController extends AdminBasicEntityController {

    protected static final String SECTION_KEY = "city";
    
    @Override
    protected String getSectionKey(Map<String, String> pathVars) {
        //allow external links to work for ToOne items
        if (super.getSectionKey(pathVars) != null) {
            return super.getSectionKey(pathVars);
        }
        return SECTION_KEY;
    }

//    @RequestMapping(value = "", method = RequestMethod.GET)
//    public String test(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
//
//        // This is expected by the modules/emptyContainer template, this is a custom template that gets included into the body
//        model.addAttribute("customView", "views/city");
//
//        // ensure navigation gets set up correctly
//        setModelAttributes(model, SECTION_KEY);
//
//        // gets the scaffolding set up to display the template from the customView attribute above
//        return "modules/emptyContainer";
//    }
//
//    @RequestMapping(value = "/cusCity", method = RequestMethod.GET)
//    public String customCity(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
//        // This is expected by the modules/emptyContainer template, this is a custom template that gets included into the body
//        model.addAttribute("customView", "views/city");
//
//        // ensure navigation gets set up correctly
//        setModelAttributes(model, SECTION_KEY);
//
//        // gets the scaffolding set up to display the template from the customView attribute above
//        return "modules/emptyContainer";
//    }
//
//    @RequestMapping(value = "/cusDistrict", method = RequestMethod.GET)
//    public String customDistrict(HttpServletRequest request, HttpServletResponse response, Model model) throws Exception {
//        // This is expected by the modules/emptyContainer template, this is a custom template that gets included into the body
//        model.addAttribute("customView", "views/district");
//
//        // ensure navigation gets set up correctly
//        setModelAttributes(model, SECTION_KEY);
//
//        // gets the scaffolding set up to display the template from the customView attribute above
//        return "modules/emptyContainer";
//    }

}
