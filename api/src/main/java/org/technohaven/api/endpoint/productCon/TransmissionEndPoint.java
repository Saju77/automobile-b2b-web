package org.technohaven.api.endpoint.productCon;

import com.broadleafcommerce.rest.api.endpoint.BaseEndpoint;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.technohaven.api.services.PortService;
import org.technohaven.api.services.TransmissionService;
import org.technohaven.api.wrapper.PortsWrapper;
import org.technohaven.api.wrapper.TransmissionsWrapper;
import org.technohaven.core.entities.Port;
import org.technohaven.core.entities.Transmission;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/transmission",  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class TransmissionEndPoint extends BaseEndpoint {

    @Resource(name = "blTransmissionService")
    protected TransmissionService transmissionService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public TransmissionsWrapper getAllTransmission(HttpServletRequest request) {
        List<Transmission> transmissions;
        transmissions = transmissionService.getAllTransmission();

        TransmissionsWrapper wrapper = (TransmissionsWrapper) context.getBean(TransmissionsWrapper.class.getName());
        wrapper.wrapDetails(transmissions, request);
        return wrapper;
    }

}
