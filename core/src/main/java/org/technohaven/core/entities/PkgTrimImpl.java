package org.technohaven.core.entities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.AdminPresentationToOneLookup;

import javax.persistence.*;

@Entity
@Table(name = "PKG_TRIM")
@Inheritance(strategy = InheritanceType.JOINED)
@AdminPresentationClass(friendlyName = "PkgTrimImpl_PkgTrim")
public class PkgTrimImpl implements PkgTrim{

    private static final Long serialVersionUID = 1L;
    private static final Log LOG = LogFactory.getLog(PkgTrimImpl.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator= "PkgTrimId")
//    @GenericGenerator(
//            name="PkgTrimId",
//            strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
//            parameters = {
//                    @Parameter(name="segment_value", value="PkgTrimImpl"),
//                    @Parameter(name="entity_name", value="org.technohaven.core.entities.PkgTrimImpl")
//            }
//    )
    @Column(name = "PKG_TRIM_ID", nullable = false)
    protected Long id;

    @ManyToOne(targetEntity = ModelImpl.class, optional=false)
    @JoinColumn(name = "MODEL_ID")
    @AdminPresentation(friendlyName = "PkgTrimImpl_PkgTrim_Model", order = 1, prominent = true, gridOrder = 1)
    @AdminPresentationToOneLookup()
    protected VehicleModel vehicleModel;

    @Column(name = "PKG_TRIM_NAME", nullable = false)
    @AdminPresentation(friendlyName = "PkgTrimImpl_PkgTrim_Name", order = 2, prominent = true, gridOrder = 2)
    protected String name;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
