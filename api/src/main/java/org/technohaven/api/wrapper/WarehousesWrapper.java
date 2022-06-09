package org.technohaven.api.wrapper;

import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.core.entities.Showroom;
import org.technohaven.core.entities.Warehouse;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@Component("org.technohaven.api.wrapper.WarehousesWrapper")
@Scope("prototype")
public class WarehousesWrapper extends BaseWrapper implements APIWrapper<List<Warehouse>> {

    @XmlElement(name = "warehouse")
    protected List<WarehouseWrapper> warehouses = new ArrayList<WarehouseWrapper>();

    public List<WarehouseWrapper> getWarehouses() {
        return warehouses;
    }

    public void setWarehouses(List<WarehouseWrapper> warehouses) {
        this.warehouses = warehouses;
    }

    @Override
    public void wrapDetails(List<Warehouse> models, HttpServletRequest request) {
        for (Warehouse warehouse : models) {
            WarehouseWrapper wrapper = (WarehouseWrapper) context.getBean(WarehouseWrapper.class.getName());
            wrapper.wrapSummary(warehouse, request);
            warehouses.add(wrapper);
        }
    }

    @Override
    public void wrapSummary(List<Warehouse> model, HttpServletRequest request) {

    }
}
