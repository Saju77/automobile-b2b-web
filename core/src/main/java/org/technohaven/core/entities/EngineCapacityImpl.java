package org.technohaven.core.entities;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.AdminPresentationToOneLookup;

import javax.persistence.*;

@Entity
@Table(name = "BLC_ENGINE_CAPACITY")
@Inheritance(strategy = InheritanceType.JOINED)
@AdminPresentationClass(friendlyName = "EngineCapacityImpl_EngineCapacity")
public class EngineCapacityImpl implements EngineCapacity{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Engine_Capacity_Id", nullable = false)
    protected long id;

    @ManyToOne(targetEntity = ModelImpl.class, optional = false)
    @JoinColumn(name = "Model_Id")
    @AdminPresentation(friendlyName = "Engine_Capacity_Model", order = 1, prominent = true, gridOrder = 1)
    @AdminPresentationToOneLookup
    protected VehicleModel vehicleModel;

    @Column(name = "Engine_Capacity", nullable = false)
    @AdminPresentation(friendlyName = "Engine_Capacity", order = 2, prominent = true, gridOrder = 2)
    protected String engineCapacity;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getEngineCapacity() {
        return engineCapacity;
    }

    public void setEngineCapacity(String engineCapacity) {
        this.engineCapacity = engineCapacity;
    }
}
