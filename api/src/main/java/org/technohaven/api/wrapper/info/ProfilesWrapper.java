package org.technohaven.api.wrapper.info;

import org.broadleafcommerce.common.rest.api.wrapper.APIWrapper;
import org.broadleafcommerce.common.rest.api.wrapper.BaseWrapper;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.technohaven.core.entities.Profile;

import javax.servlet.http.HttpServletRequest;
import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;
import java.util.List;

@Component("org.technohaven.api.wrapper.info.ProfilesWrapper")
@Scope("prototype")
public class ProfilesWrapper extends BaseWrapper implements APIWrapper<List<Profile>> {

    @XmlElement(name = "profile")
    protected List<ProfileWrapper> profiles = new ArrayList<ProfileWrapper>();

    public List<ProfileWrapper> getProfiles() {
        return profiles;
    }

    public void setProfiles(List<ProfileWrapper> profiles) {
        this.profiles = profiles;
    }

    @Override
    public void wrapDetails(List<Profile> models, HttpServletRequest request) {
        for (Profile profile : models) {
            ProfileWrapper wrapper = (ProfileWrapper) context.getBean(ProfileWrapper.class.getName());
            wrapper.wrapSummary(profile, request);
            profiles.add(wrapper);
        }
    }

    @Override
    public void wrapSummary(List<Profile> model, HttpServletRequest request) {

    }
}
