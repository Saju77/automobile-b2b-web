package org.technohaven.core.entities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;

import javax.persistence.*;

@Entity
@Table(name = "TEST_ENTITY")
@Inheritance(strategy = InheritanceType.JOINED)
@AdminPresentationClass(friendlyName = "TestEntityImpl_TestEntity")
public class TestEntityImpl implements TestEntity{

    private static final Long serialVersionUID = 1L;
    private static final Log LOG = LogFactory.getLog(TestEntityImpl.class);

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TEST_ENTITY_ID", nullable = false)
    protected Long id;

    @Column(name = "TEST_ENTITY_NAME", nullable = false)
    @AdminPresentation(friendlyName = "TestEntityImpl_TestEntity_Name", order = 1, prominent = true, gridOrder = 1)
    protected String name;

    public Long getId() {
        return id;
    }

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
}
