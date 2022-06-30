package org.technohaven.core.entities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.AdminPresentationToOneLookup;

import javax.persistence.*;

@Entity
@Table(name = "VEHICLE")
@Inheritance(strategy = InheritanceType.JOINED)
@AdminPresentationClass(friendlyName = "VehicleImpl_Vehicle")
public class VehicleImpl implements Vehicle{

    private static final Long serialVersionUID = 1L;
    private static final Log LOG = LogFactory.getLog(VehicleImpl.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator= "VehicleId")
//    @GenericGenerator(
//            name="VehicleId",
//            strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
//            parameters = {
//                    @Parameter(name="segment_value", value="VehicleImpl"),
//                    @Parameter(name="entity_name", value="org.technohaven.core.entities.VehicleImpl")
//            }
//    )
    @Column(name = "VEHICLE_ID", nullable = false)
    protected Long id;

    @ManyToOne(targetEntity = ModelImpl.class, optional=false)
    @JoinColumn(name = "MODEL_ID")
    @AdminPresentation(friendlyName = "VehicleImpl_Vehicle_Model", order = 1, prominent = true, gridOrder = 1)
    @AdminPresentationToOneLookup()
    protected VehicleModel vehicleModel;

    @Column(name = "VEHICLE_EDITION", nullable = false)
    @AdminPresentation(friendlyName = "VehicleImpl_Vehicle_Edition", order = 2, prominent = true, gridOrder = 2)
    protected String edition;

    @Column(name = "VEHICLE_EDITION_SHORT_NAME")
    @AdminPresentation(friendlyName = "VehicleImpl_Vehicle_Edition_Short_Name", order = 3, prominent = true, gridOrder = 3)
    protected String shortName;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public VehicleModel getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(VehicleModel vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
