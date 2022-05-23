package org.technohaven.api.endpoint.location;

import com.broadleafcommerce.rest.api.endpoint.BaseEndpoint;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.technohaven.api.services.CityService;
import org.technohaven.api.services.PortService;
import org.technohaven.api.wrapper.CitiesWrapper;
import org.technohaven.api.wrapper.PortsWrapper;
import org.technohaven.core.entities.City;
import org.technohaven.core.entities.Port;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/port",  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class PortEndPoint extends BaseEndpoint {

    @Resource(name = "blPortService")
    protected PortService portService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public PortsWrapper getAllPort(HttpServletRequest request) {
        List<Port> ports;
        ports = portService.getAllPort();

        PortsWrapper wrapper = (PortsWrapper) context.getBean(PortsWrapper.class.getName());
        wrapper.wrapDetails(ports, request);
        return wrapper;
    }

    @RequestMapping(value = "/findPorts", method = RequestMethod.GET)
    public PortsWrapper findPorts(HttpServletRequest request,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "limit", defaultValue = "20") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset) {
        List<Port> ports;

        if (name != null) {
            ports = portService.findPortsByName(name, limit, offset);
        } else {
            ports = portService.findPorts(limit, offset);
        }

        PortsWrapper wrapper = (PortsWrapper) context.getBean(PortsWrapper.class.getName());
        wrapper.wrapDetails(ports, request);
        return wrapper;
    }
}