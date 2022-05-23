package org.technohaven.core.entities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.AdminPresentationToOneLookup;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "SHOWROOM")
@Inheritance(strategy = InheritanceType.JOINED)
@AdminPresentationClass(friendlyName = "ShowroomImpl_Showroom")
public class ShowroomImpl implements Showroom{

    private static final Long serialVersionUID = 1L;
    private static final Log LOG = LogFactory.getLog(ShowroomImpl.class);

    @Id
    @GeneratedValue(generator= "ShowroomId")
    @GenericGenerator(
            name="ShowroomId",
            strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
            parameters = {
                    @Parameter(name="segment_value", value="ShowroomImpl"),
                    @Parameter(name="entity_name", value="org.technohaven.core.entities.ShowroomImpl")
            }
    )
    @Column(name = "SHOWROOM_ID", nullable = false)
    protected Long id;

    @Column(name = "SHOWROOM_NAME", nullable = false)
    @AdminPresentation(friendlyName = "ShowroomImpl_Showroom_Name", order = 1, prominent = true, gridOrder = 1)
    protected String name;

    @Column(name = "PHONE_NUMBER", nullable = false)
    @AdminPresentation(friendlyName = "ShowroomImpl_Showroom_Phone_Number", order = 2, prominent = true, gridOrder = 2)
    protected String phoneNumber;

    @ManyToOne(targetEntity = DistrictImpl.class, optional=false)
    @JoinColumn(name = "DISTRICT_ID")
    @AdminPresentation(friendlyName = "ShowroomImpl_Showroom_District", order = 3, prominent = true, gridOrder = 3)
    @AdminPresentationToOneLookup()
    protected District district;

    @ManyToOne(targetEntity = CityImpl.class, optional=false)
    @JoinColumn(name = "CITY_ID")
    @AdminPresentation(friendlyName = "ShowroomImpl_Showroom_City", order = 4, prominent = true, gridOrder = 4)
    @AdminPresentationToOneLookup()
    protected City city;

    @Column(name = "ADDRESS", nullable = false)
    @AdminPresentation(friendlyName = "ShowroomImpl_Showroom_Address", order = 5, prominent = true, gridOrder = 5)
    protected String address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public District getDistrict() {
        return district;
    }

    @Override
    public void setDistrict(District district) {
        this.district = district;
    }

    public City getCity() {
        return city;
    }

    public void setCity(City city) {
        this.city = city;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}


