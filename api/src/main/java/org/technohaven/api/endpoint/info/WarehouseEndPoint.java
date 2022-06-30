package org.technohaven.api.endpoint.info;

import com.broadleafcommerce.rest.api.endpoint.BaseEndpoint;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.technohaven.api.services.WarehouseService;
import org.technohaven.api.wrapper.WarehouseWrapper;
import org.technohaven.api.wrapper.WarehousesWrapper;
import org.technohaven.core.entities.Warehouse;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping(value = "/warehouseinfo",  produces = { MediaType.APPLICATION_JSON_VALUE })
public class WarehouseEndPoint extends BaseEndpoint {

    @Resource(name = "blWarehouseService")
    protected WarehouseService warehouseService;

    @RequestMapping(value = "", method = RequestMethod.GET)
    public WarehousesWrapper getAllWarehouse(HttpServletRequest request) {
        List<Warehouse> warehouses;
        warehouses = warehouseService.getWarehouses();

        WarehousesWrapper wrapper = (WarehousesWrapper) context.getBean(WarehousesWrapper.class.getName());
        wrapper.wrapDetails(warehouses, request);
        return wrapper;
    }

    @RequestMapping(value="", method = RequestMethod.POST,
            consumes = { MediaType.APPLICATION_JSON_VALUE })
    public WarehouseWrapper addWarehouse(HttpServletRequest request, @RequestBody WarehouseWrapper wrapper) {

        Warehouse warehouse = wrapper.unwrap(request, context);

        warehouse = warehouseService.save(warehouse);

        WarehouseWrapper response = (WarehouseWrapper) context.getBean(WarehouseWrapper.class.getName());
        response.wrapDetails(warehouse, request);

        return response;
    }

    @RequestMapping(value = "/delete/{warehouseId}", method = RequestMethod.DELETE)
    public WarehouseWrapper removeWarehouse(HttpServletRequest request,
            @PathVariable("warehouseId") Long warehouseId){
        Warehouse warehouse = warehouseId != null ? warehouseService.findWarehouseById(warehouseId) : null;
        if (warehouse == null) {
            throw new RuntimeException();
        }

        warehouseService.removeWarehouse(warehouse);

        WarehouseWrapper response = (WarehouseWrapper) context.getBean(WarehouseWrapper.class.getName());
        response.wrapDetails(warehouse, request);

        return response;
    }

    @RequestMapping(value = "/edit/{warehouseId}", method = RequestMethod.PUT, consumes = { MediaType.APPLICATION_JSON_VALUE })
    public WarehouseWrapper updateWarehouse(HttpServletRequest request, @PathVariable("warehouseId") Long warehouseId, @RequestBody WarehouseWrapper wrapper) {
        Warehouse warehouse = warehouseId != null ? warehouseService.findWarehouseById(warehouseId) : null;
        if (warehouse == null) {
            throw new RuntimeException();
        }
        wrapper.setId(warehouseId);
        Warehouse update = wrapper.unwrap(request, context);

        warehouse = warehouseService.save(update);

        WarehouseWrapper response = (WarehouseWrapper) context.getBean(WarehouseWrapper.class.getName());
        response.wrapDetails(warehouse, request);

        return response;
    }

    @RequestMapping(value = "details/{warehouseId}", method = RequestMethod.GET)
    public WarehouseWrapper findWarehouseById(HttpServletRequest request, @PathVariable("warehouseId") Long warehouseId) {
        Warehouse warehouse = warehouseService.findWarehouseById(warehouseId);
        WarehouseWrapper wrapper = (WarehouseWrapper) context.getBean(WarehouseWrapper.class.getName());
        wrapper.wrapDetails(warehouse, request);
        return wrapper;
    }

}
