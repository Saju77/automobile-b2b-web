package org.technohaven.core.entities;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Parameter;

import javax.persistence.*;

@Entity
@Table(name = "COLOR")
@Inheritance(strategy = InheritanceType.JOINED)
@AdminPresentationClass(friendlyName = "ColorImpl_Color")
public class ColorImpl implements Color{

    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
//    @GeneratedValue(generator= "ColorId")
//    @GenericGenerator(
//            name="ColorId",
//            strategy="org.broadleafcommerce.common.persistence.IdOverrideTableGenerator",
//            parameters = {
//                    @Parameter(name="segment_value", value="ColorImpl"),
//                    @Parameter(name="entity_name", value="org.technohaven.core.entities.ColorImpl")
//            }
//    )
    @Column(name = "COLOR_ID", nullable = false)
    protected Long id;

    @Column(name = "NAME", nullable = false)
    @AdminPresentation(friendlyName = "ColorImpl_Color_Name", order = 1, prominent = true, gridOrder = 1)
    protected String name;

    @Column(name = "CODE", nullable = false)
    @AdminPresentation(friendlyName = "ColorImpl_Color_Code", order = 2, prominent = true, gridOrder = 2)
    protected int code;

    @Column(name = "SHORT_NAME", nullable = false)
    @AdminPresentation(friendlyName = "ColorImpl_Color_Short_Name", order = 3, prominent = true, gridOrder = 3)
    protected String shortName;

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

    @Override
    public String getShortName() {
        return shortName;
    }

    @Override
    public void setShortName(String shortName) {
        this.shortName = shortName;
    }
}
