package org.technohaven.core.entities;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "PORT")
@AdminPresentationClass(friendlyName = "Port")
public class PortImpl implements Port{

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator= "PortId")
    @GenericGenerator(
            name="PortId",
            strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
            parameters = {
                    @Parameter(name="segment_value", value="PortImpl"),
                    @Parameter(name="entity_name", value="org.technohaven.core.entities.PortImpl")
            }
    )
    @Column(name = "PORT_ID", nullable = false)
    protected Long id;

    @Column(name = "NAME", nullable = false)
    @AdminPresentation(friendlyName = "Name", order = 1, prominent = true, gridOrder = 1)
    protected String name;

    @Column(name = "CODE", nullable = false)
    @AdminPresentation(friendlyName = "Code", order = 2, prominent = true, gridOrder = 2)
    protected String code;

    @Column(name = "DISTRICT", nullable = false)
    @AdminPresentation(friendlyName = "District", order = 3, prominent = true, gridOrder = 3)
    protected String district;

    @Column(name = "CITY", nullable = false)
    @AdminPresentation(friendlyName = "City", order = 4, prominent = true, gridOrder = 4)
    protected String city;

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

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public void setCode(String code) {
        this.code = code;
    }

    @Override
    public String getDistrict() {
        return district;
    }

    @Override
    public void setDistrict(String district) {
        this.district = district;
    }

    @Override
    public String getCity() {
        return city;
    }

    @Override
    public void setCity(String city) {
        this.city = city;
    }
}
