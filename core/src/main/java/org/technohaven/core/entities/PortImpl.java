package org.technohaven.core.entities;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.AdminPresentationToOneLookup;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "PORT")
@Inheritance(strategy = InheritanceType.JOINED)
@AdminPresentationClass(friendlyName = "PortImpl_Port")
public class PortImpl implements Port{

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @GeneratedValue(generator= "PortId")
//    @GenericGenerator(
//            name="PortId",
//            strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
//            parameters = {
//                    @Parameter(name="segment_value", value="PortImpl"),
//                    @Parameter(name="entity_name", value="org.technohaven.core.entities.PortImpl")
//            }
//    )
    @Column(name = "PORT_ID", nullable = false)
    protected Long id;

    @Column(name = "NAME", nullable = false)
    @AdminPresentation(friendlyName = "PortImpl_Port_Name", order = 1, prominent = true, gridOrder = 1)
    protected String name;

    @Column(name = "CODE", nullable = false)
    @AdminPresentation(friendlyName = "Code", order = 2, prominent = true, gridOrder = 2)
    protected int code;

    @ManyToOne(targetEntity = CityImpl.class, optional=false)
    @JoinColumn(name = "CITY_ID")
    @AdminPresentation(friendlyName = "PortImpl_Port_City", order = 3, prominent = true, gridOrder = 3)
    @AdminPresentationToOneLookup()
    protected City cityId;

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
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }

    public City getCityId() {
        return cityId;
    }

    public void setCityId(City cityId) {
        this.cityId = cityId;
    }
}
