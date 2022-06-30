package org.technohaven.core.entities;

import java.io.Serializable;

public interface Showroom extends Serializable {

    public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);

    public String getPhoneNumber();

    public void setPhoneNumber(String phoneNumber);

    public City getCity();

    public void setCity(City city);

    public String getAddress();

    public void setAddress(String address);

}
