package org.technohaven.core.entities;

import java.io.Serializable;

public interface AuctionGrade extends Serializable {

    public Long getId();

    public void setId(Long id);

    public String getGrade();

    public void setGrade(String grade);

}
