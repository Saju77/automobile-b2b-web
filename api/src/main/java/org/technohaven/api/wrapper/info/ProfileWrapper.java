package org.technohaven.api.wrapper.info;

import org.broadleafcommerce.common.rest.api.wrapper.APIUnwrapper;
import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.api.services.info.ProfileService;
import org.technohaven.core.entities.Profile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@Component("org.technohaven.api.wrapper.info.ProfileWrapper")
@Scope("prototype")
@XmlRootElement(name = "profile")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class ProfileWrapper extends BaseWrapper implements APIWrapper<Profile>, APIUnwrapper<Profile> {

    @XmlElement
    protected Long id;

    @XmlElement
    protected String name;

    @XmlElement
    protected String designation;

    @XmlElement
    protected String phoneNumber;

    @XmlElement
    protected String businessEmail;

    @XmlElement
    protected String companyName;

    @XmlElement
    protected String binNumber;

    @XmlElement
    protected String tradeLicense;

    @XmlElement
    protected String barvidaMemberId;

    @XmlElement
    protected String address;

    @XmlElement
    protected String photoUrl;

    @Override
    public void wrapDetails(Profile profile, HttpServletRequest request) {
        this.id = profile.getId();
        this.name = profile.getName();
        this.designation = profile.getDesignation();
        this.phoneNumber = profile.getPhoneNumber();
        this.businessEmail = profile.getBusinessEmail();
        this.companyName = profile.getCompanyName();
        this.binNumber = profile.getBinNumber();
        this.tradeLicense = profile.getTradeLicense();
        this.barvidaMemberId = profile.getBarvidaMemberId();
        this.address = profile.getAddress();
        this.photoUrl = profile.getPhotoUrl();
    }

    @Override
    public void wrapSummary(Profile profile, HttpServletRequest request) {
        wrapDetails(profile, request);
    }

    @Override
    public Profile unwrap(HttpServletRequest request, ApplicationContext context) {
        ProfileService profileService = (ProfileService) context.getBean("blProfileService");
        Profile profile = profileService.createProfileFromId(this.id);
        profile.setName(this.name);
        profile.setDesignation(this.designation);
        profile.setPhoneNumber(this.phoneNumber);
        profile.setBusinessEmail(this.businessEmail);
        profile.setCompanyName(this.companyName);
        profile.setBinNumber(this.binNumber);
        profile.setTradeLicense(this.tradeLicense);
        profile.setBarvidaMemberId(this.barvidaMemberId);
        profile.setAddress(this.address);
        profile.setPhotoUrl(this.photoUrl);

        return profile;
    }

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

}
