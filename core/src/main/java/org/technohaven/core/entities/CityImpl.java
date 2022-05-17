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
@AdminPresentationClass(friendlyName = "City")
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
    @AdminPresentation(friendlyName = "DISTRICT", order = 1, prominent = true, gridOrder = 1)
    @AdminPresentationToOneLookup()
    protected District districtId;

    @Column(name = "CITY_UPAZILA_NAME", nullable = false)
    @AdminPresentation(friendlyName = "City/Upazila Name", order = 2, prominent = true, gridOrder = 2)
    protected String cityUpazilaName;

    @Column(name = "CITY_UPAZILA_CODE", nullable = false)
    @AdminPresentation(friendlyName = "City/Upazila Code", order = 3, prominent = true, gridOrder = 3)
    protected String cityUpazilaCode;

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

    @Override
    public String getCityUpazilaName() {
        return cityUpazilaName;
    }

    @Override
    public void setCityUpazilaName(String cityUpazilaName) {
        this.cityUpazilaName = cityUpazilaName;
    }

    @Override
    public String getCityUpazilaCode() {
        return cityUpazilaCode;
    }

    @Override
    public void setCityUpazilaCode(String cityUpazilaCode) {
        this.cityUpazilaCode = cityUpazilaCode;
    }
    
}
