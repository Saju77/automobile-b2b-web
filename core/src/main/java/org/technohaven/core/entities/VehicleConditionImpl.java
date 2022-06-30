package org.technohaven.core.entities;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;

import javax.persistence.*;

@Entity
@Table(name = "BLC_VEHICLE_CONDITION_TYPE_CONFIG")
@Inheritance(strategy = InheritanceType.JOINED)
@AdminPresentationClass(friendlyName = "VehicleConditionImpl_Vehicle")
public class VehicleConditionImpl implements VehicleCondition{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "Vehicle_Condition_Id")
    protected long id;

    @Column(name = "Vehicle_Condition", nullable=false)
    @AdminPresentation(friendlyName = "Vehicle_Condition", order = 1, prominent = true, gridOrder = 1)
    protected String vehicleCondition;

    @Override
    public long getId() {
        return id;
    }
    @Override
    public void setId(long id) {
        this.id = id;
    }
    @Override
    public String getVehicleCondition() {
        return vehicleCondition;
    }
    @Override
    public void setVehicleCondition(String vehicleCondition) {
        this.vehicleCondition = vehicleCondition;
    }
}
