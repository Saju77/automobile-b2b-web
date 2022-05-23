package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.core.entities.City;
import org.technohaven.core.entities.District;
import org.technohaven.core.entities.Port;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@Component("org.technohaven.api.wrapper.PortsWrapper")
@Scope("prototype")
public class PortsWrapper extends BaseWrapper implements APIWrapper<List<Port>> {

    @XmlElement(name = "port")
    protected List<PortWrapper> ports = new ArrayList<PortWrapper>();

    public List<PortWrapper> getPorts() {
        return ports;
    }

    public void setPorts(List<PortWrapper> ports) {
        this.ports = ports;
    }

    @Override
    public void wrapDetails(List<Port> models, HttpServletRequest request) {

        for (Port port : models) {
            PortWrapper wrapper = (PortWrapper) context.getBean(PortWrapper.class.getName());
            wrapper.wrapSummary(port, request);
            ports.add(wrapper);
        }
    }

    @Override
    public void wrapSummary(List<Port> model, HttpServletRequest request) {

    }
}
