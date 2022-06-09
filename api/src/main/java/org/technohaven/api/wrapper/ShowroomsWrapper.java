package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.core.entities.Showroom;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@Component("org.technohaven.api.wrapper.ShowroomsWrapper")
@Scope("prototype")
public class ShowroomsWrapper extends BaseWrapper implements APIWrapper<List<Showroom>> {

    @XmlElement(name = "showroom")
    protected List<ShowroomWrapper> showrooms = new ArrayList<ShowroomWrapper>();

    public List<ShowroomWrapper> getShowrooms() {
        return showrooms;
    }

    public void setShowrooms(List<ShowroomWrapper> showrooms) {
        this.showrooms = showrooms;
    }

    @Override
    public void wrapDetails(List<Showroom> models, HttpServletRequest request) {
        for (Showroom showroom : models) {
            ShowroomWrapper wrapper = (ShowroomWrapper) context.getBean(ShowroomWrapper.class.getName());
            wrapper.wrapSummary(showroom, request);
            showrooms.add(wrapper);
        }
    }

    @Override
    public void wrapSummary(List<Showroom> model, HttpServletRequest request) {

    }
}
