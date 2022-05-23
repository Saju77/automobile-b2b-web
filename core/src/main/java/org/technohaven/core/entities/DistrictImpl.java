package org.technohaven.core.entities;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "DISTRICT")
@Inheritance(strategy = InheritanceType.JOINED)
@AdminPresentationClass(friendlyName = "DistrictImpl_District")
public class DistrictImpl implements District{

    private static final Long serialVersionUID = 1L;
    private static final Log LOG = LogFactory.getLog(DistrictImpl.class);

    @Id
    @GeneratedValue(generator= "DistrictId")
    @GenericGenerator(
            name="DistrictId",
            strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
            parameters = {
                    @Parameter(name="segment_value", value="DistrictImpl"),
                    @Parameter(name="entity_name", value="org.technohaven.core.entities.DistrictImpl")
            }
    )
    @Column(name = "DISTRICT_ID", nullable = false)
    protected Long id;

    @Column(name = "NAME", nullable = false)
    @AdminPresentation(friendlyName = "DistrictImpl_District_Name", order = 1, prominent = true, gridOrder = 1)
    protected String name;

    @Column(name = "CODE", nullable = false)
    @AdminPresentation(friendlyName = "DistrictImpl_District_Code", order = 2, prominent = true, gridOrder = 2)
    protected int code;

    @Override
    public Long getId() {
        return id;
    }

    @Override
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

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public void setCode(int code) {
        this.code = code;
    }
}
