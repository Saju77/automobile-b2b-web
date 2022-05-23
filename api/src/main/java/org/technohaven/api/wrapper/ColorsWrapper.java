package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.core.entities.Color;
import org.technohaven.core.entities.Port;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@Component("org.technohaven.api.wrapper.ColorsWrapper")
@Scope("prototype")
public class ColorsWrapper extends BaseWrapper implements APIWrapper<List<Color>> {

    @XmlElement(name = "color")
    protected List<ColorWrapper> colors = new ArrayList<ColorWrapper>();

    public List<ColorWrapper> getColors() {
        return colors;
    }

    public void setColors(List<ColorWrapper> colors) {
        this.colors = colors;
    }


    @Override
    public void wrapDetails(List<Color> models, HttpServletRequest request) {
        for (Color color : models) {
            ColorWrapper wrapper = (ColorWrapper) context.getBean(ColorWrapper.class.getName());
            wrapper.wrapSummary(color, request);
            colors.add(wrapper);
        }
    }

    @Override
    public void wrapSummary(List<Color> models, HttpServletRequest request) {

    }
}
