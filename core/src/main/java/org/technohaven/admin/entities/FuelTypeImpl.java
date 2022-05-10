package org.technohaven.admin.entities;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "FUEL_TYPE")
@AdminPresentationClass(friendlyName = "Fuel Type")
public class FuelTypeImpl implements FuelType{

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator= "FuelTypeId")
    @GenericGenerator(
            name="FuelTypeId",
            strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
            parameters = {
                    @Parameter(name="segment_value", value="FuelTypeImpl"),
                    @Parameter(name="entity_name", value="org.technohaven.admin.entities.FuelTypeImpl")
            }
    )
    @Column(name = "FUEL_TYPE_ID", nullable = false)
    protected Long id;

    @Column(name = "NAME", nullable = false)
    @AdminPresentation(friendlyName = "Name", order = 1, prominent = true, gridOrder = 1)
    protected String name;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

}
