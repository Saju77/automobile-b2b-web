package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.core.entities.Port;
import org.technohaven.core.entities.Transmission;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@Component("org.technohaven.api.wrapper.TransmissionsWrapper")
@Scope("prototype")
public class TransmissionsWrapper extends BaseWrapper implements APIWrapper<List<Transmission>> {

    @XmlElement(name = "transmission")
    protected List<TransmissionWrapper> transmissions = new ArrayList<TransmissionWrapper>();

    public List<TransmissionWrapper> getTransmissions() {
        return transmissions;
    }

    public void setTransmissions(List<TransmissionWrapper> transmissions) {
        this.transmissions = transmissions;
    }

    @Override
    public void wrapDetails(List<Transmission> models, HttpServletRequest request) {
        for (Transmission transmission : models) {
            TransmissionWrapper wrapper = (TransmissionWrapper) context.getBean(TransmissionWrapper.class.getName());
            wrapper.wrapSummary(transmission, request);
            transmissions.add(wrapper);
        }
    }

    @Override
    public void wrapSummary(List<Transmission> models, HttpServletRequest request) {

    }
}
