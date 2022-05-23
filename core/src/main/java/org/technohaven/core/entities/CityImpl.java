package org.technohaven.core.entities;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.AdminPresentationToOneLookup;
import org.broadleafcommerce.common.presentation.RequiredOverride;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "CITY")
@Inheritance(strategy = InheritanceType.JOINED)
@AdminPresentationClass(friendlyName = "CityImpl_City")
public class CityImpl implements City{

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(generator= "CityId")
    @GenericGenerator(
            name="CityId",
            strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
            parameters = {
                    @Parameter(name="segment_value", value="CityImpl"),
                    @Parameter(name="entity_name", value="org.technohaven.core.entities.CityImpl")
            }
    )
    @Column(name = "CITY_ID", nullable = false)
    protected Long id;

    @ManyToOne(targetEntity = DistrictImpl.class, optional=false)
    @JoinColumn(name = "DISTRICT_ID")
    @AdminPresentation(friendlyName = "CityImpl_City_District", order = 1, prominent = true, gridOrder = 1)
    @AdminPresentationToOneLookup()
    protected District districtId;

    @Column(name = "CITY_UPAZILA_NAME", nullable = false)
    @AdminPresentation(friendlyName = "CityImpl_City_City_Upazila_Name", order = 2, prominent = true, gridOrder = 2)
    protected String name;

    @Column(name = "CITY_UPAZILA_CODE", nullable = false)
    @AdminPresentation(friendlyName = "CityImpl_City_City_Upazila_Code", order = 3, prominent = true, gridOrder = 3)
    protected int code;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public District getDistrictId() {
        return districtId;
    }

    public void setDistrictId(District districtId) {
        this.districtId = districtId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}
