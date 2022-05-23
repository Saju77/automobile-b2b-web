package org.technohaven.core.entities;

import org.apache.commons.lang3.BooleanUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.profile.core.domain.CustomerAdminPresentation;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "PROFILE")
@Inheritance(strategy = InheritanceType.JOINED)
@AdminPresentationClass(friendlyName = "ProfileImpl_Profile")
public class ProfileImpl implements Profile{

    private static final Long serialVersionUID = 1L;
    private static final Log LOG = LogFactory.getLog(ProfileImpl.class);

    @Id
    @GeneratedValue(generator= "ProfileId")
    @GenericGenerator(
            name="ProfileId",
            strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
            parameters = {
                    @Parameter(name="segment_value", value="ProfileImpl"),
                    @Parameter(name="entity_name", value="org.technohaven.core.entities.ProfileImpl")
            }
    )
    @Column(name = "PROFILE_ID", nullable = false)
    protected Long id;

    @Column(name = "OWNER_NAME", nullable = false)
    @AdminPresentation(friendlyName = "ProfileImpl_Profile_Owner_Name", order = 1, prominent = true, gridOrder = 1)
    protected String name;

    @Column(name = "DESIGNATION", nullable = false)
    @AdminPresentation(friendlyName = "ProfileImpl_Profile_Designation", order = 2, prominent = true, gridOrder = 2)
    protected String designation;

    @Column(name = "PHONE_NUMBER")
    @AdminPresentation(friendlyName = "ProfileImpl_Profile_Phone_Number", order = 3, prominent = true, gridOrder = 3)
    protected String phoneNumber;

    @Column(name = "BUSINESS_EMAIL", nullable = false)
    @AdminPresentation(friendlyName = "ProfileImpl_Profile_Business_Email", order = 4, prominent = true, gridOrder = 4)
    protected String businessEmail;

    @Column(name = "COMPANY_NAME", nullable = false)
    @AdminPresentation(friendlyName = "ProfileImpl_Profile_Company_Name", order = 5, prominent = true, gridOrder = 5)
    protected String companyName;

    @Column(name = "BIN_NUMBER")
    @AdminPresentation(friendlyName = "ProfileImpl_Profile_BIN_Number", order = 6, prominent = true, gridOrder = 6)
    protected String binNumber;

    @Column(name = "TRADE_LICENSE", nullable = false)
    @AdminPresentation(friendlyName = "ProfileImpl_Profile_Trade_License", order = 7, prominent = true, gridOrder = 7)
    protected String tradeLicense;

    @Column(name = "BARVIDA_MEMBER_ID")
    @AdminPresentation(friendlyName = "ProfileImpl_Profile_Barvida_Member_Id", order = 8, prominent = true, gridOrder = 8)
    protected String barvidaMemberId;

    @Column(name = "ADDRESS", nullable = false)
    @AdminPresentation(friendlyName = "ProfileImpl_Profile_Address", order = 9, prominent = true, gridOrder = 9)
    protected String address;

    @Column(name = "PHOTO_URL", nullable = false)
    @AdminPresentation(friendlyName = "ProfileImpl_Profile_Photo_Url", order = 10, prominent = true, gridOrder = 10)
    protected String photoUrl;

    @Column(name = "IS_REGISTERED")
    @AdminPresentation(friendlyName = "CustomerImpl_Customer_Registered",
            group = CustomerAdminPresentation.GroupName.QualificationOptions, order = CustomerAdminPresentation.FieldOrder.REGISTERED,
            prominent = true, gridOrder = 4000)
    protected Boolean registered = false;

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

    public String getDesignation() {
        return designation;
    }

    public void setDesignation(String designation) {
        this.designation = designation;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getBusinessEmail() {
        return businessEmail;
    }

    public void setBusinessEmail(String businessEmail) {
        this.businessEmail = businessEmail;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getBinNumber() {
        return binNumber;
    }

    public void setBinNumber(String binNumber) {
        this.binNumber = binNumber;
    }

    public String getTradeLicense() {
        return tradeLicense;
    }

    public void setTradeLicense(String tradeLicense) {
        this.tradeLicense = tradeLicense;
    }

    public String getBarvidaMemberId() {
        return barvidaMemberId;
    }

    public void setBarvidaMemberId(String barvidaMemberId) {
        this.barvidaMemberId = barvidaMemberId;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhotoUrl() {
        return photoUrl;
    }

    public void setPhotoUrl(String photoUrl) {
        this.photoUrl = photoUrl;
    }

    @Override
    public boolean isRegistered() {
        return BooleanUtils.toBoolean(registered);
    }

    @Override
    public void setRegistered(boolean registered) {
        this.registered = Boolean.valueOf(registered);
    }
}
