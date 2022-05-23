package org.technohaven.api.endpoint.productCon;

import com.broadleafcommerce.rest.api.endpoint.BaseEndpoint;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.technohaven.api.services.ColorService;
import org.technohaven.api.services.PortService;
import org.technohaven.api.wrapper.ColorsWrapper;
import org.technohaven.api.wrapper.PortsWrapper;
import org.technohaven.core.entities.Color;
import org.technohaven.core.entities.Port;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/color",  produces = { MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE })
public class ColorEndPoint extends BaseEndpoint {

    @Resource(name = "blColorService")
    protected ColorService colorService;


    @RequestMapping(value = "", method = RequestMethod.GET)
    public ColorsWrapper getAllColor(HttpServletRequest request) {
        List<Color> colors;
        colors = colorService.getAllColor();

        ColorsWrapper wrapper = (ColorsWrapper) context.getBean(ColorsWrapper.class.getName());
        wrapper.wrapDetails(colors, request);
        return wrapper;
    }

    @RequestMapping(value = "/findColors", method = RequestMethod.GET)
    public ColorsWrapper findColors(HttpServletRequest request,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "limit", defaultValue = "20") int limit,
            @RequestParam(value = "offset", defaultValue = "0") int offset) {
        List<Color> colors;

        if (name != null) {
            colors = colorService.findColorsByName(name, limit, offset);
        } else {
            colors = colorService.findColors(limit, offset);
        }

        ColorsWrapper wrapper = (ColorsWrapper) context.getBean(ColorsWrapper.class.getName());
        wrapper.wrapDetails(colors, request);
        return wrapper;
    }

}
