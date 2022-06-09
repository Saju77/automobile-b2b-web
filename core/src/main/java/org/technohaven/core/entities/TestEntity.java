package org.technohaven.core.entities;

import java.io.Serializable;

public interface TestEntity extends Serializable {

    public Long getId();

    public void setId(Long id);

    public String getName();

    public void setName(String name);

}