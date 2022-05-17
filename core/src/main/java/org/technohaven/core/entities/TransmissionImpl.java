package org.technohaven.core.entities;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "TRANSMISSION")
@AdminPresentationClass(friendlyName = "Transmission")
public class TransmissionImpl implements Transmission{

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator= "TransmissionId")
    @GenericGenerator(
            name="TransmissionId",
            strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
            parameters = {
                    @Parameter(name="segment_value", value="TransmissionImpl"),
                    @Parameter(name="entity_name", value="org.technohaven.core.entities.TransmissionImpl")
            }
    )
    @Column(name = "TRANSMISSION_ID", nullable = false)
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
