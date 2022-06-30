package org.technohaven.core.entities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.AdminPresentationToOneLookup;

import javax.persistence.*;

@Entity
@Table(name = "WAREHOUSE")
@Inheritance(strategy = InheritanceType.JOINED)
@AdminPresentationClass(friendlyName = "WarehouseImpl_Warehouse")
public class WarehouseImpl implements Warehouse{

    private static final Long serialVersionUID = 1L;
    private static final Log LOG = LogFactory.getLog(WarehouseImpl.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator= "WarehouseId")
//    @GenericGenerator(
//            name="WarehouseId",
//            strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
//            parameters = {
//                    @Parameter(name="segment_value", value="WarehouseImpl"),
//                    @Parameter(name="entity_name", value="org.technohaven.core.entities.WarehouseImpl")
//            }
//    )
    @Column(name = "WAREHOUSE_ID", nullable = false)
    protected Long id;

    @Column(name = "WAREHOUSE_NAME", nullable = false)
    @AdminPresentation(friendlyName = "WarehouseImpl_Warehouse_Name", order = 1, prominent = true, gridOrder = 1)
    protected String name;

    @Column(name = "PHONE_NUMBER", nullable = false)
    @AdminPresentation(friendlyName = "WarehouseImpl_Warehouse_Phone_Number", order = 2, prominent = true, gridOrder = 2)
    protected String phoneNumber;

    @ManyToOne(targetEntity = CityImpl.class, optional=false)
    @JoinColumn(name = "CITY_ID")
    @AdminPresentation(friendlyName = "WarehouseImpl_Warehouse_City", order = 3, prominent = true, gridOrder = 3)
    @AdminPresentationToOneLookup()
    protected City city;

    @Column(name = "ADDRESS", nullable = false)
    @AdminPresentation(friendlyName = "WarehouseImpl_Warehouse_Address", order = 4, prominent = true, gridOrder = 4)
    protected String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
