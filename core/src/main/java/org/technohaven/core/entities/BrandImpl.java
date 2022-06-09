package org.technohaven.core.entities;


import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;

import javax.persistence.*;

@Entity
@Table(name = "BLC_BRAND")
@Inheritance(strategy = InheritanceType.JOINED)
@AdminPresentationClass(friendlyName = "BrandImpl_Brand")
public class BrandImpl implements Brand{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Brand_Id", nullable = false)
    protected Long id;

    @Column(name = "Brand_Name", nullable = false)
    @AdminPresentation(friendlyName = "BrandImpl_Brand_Name", order = 1, prominent = true, gridOrder = 1)
    protected String name;

    @Column(name = "Brand_Short_Name", nullable = false)
    @AdminPresentation(friendlyName = "BrandImpl_Brand_Short_Name", order = 2, prominent = true, gridOrder = 2)
    protected String brandShortName;

    @Column(name = "Brand_Code")
    @AdminPresentation(friendlyName = "BrandImpl_Brand_Code", order = 3, prominent = true, gridOrder = 3)
    protected String brandCode;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long brandId) {
        this.id = brandId;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String brandName) {
        this.name = brandName;
    }

    @Override
    public String getBrandShortName() {
        return brandShortName;
    }

    @Override
    public void setBrandShortName(String brandShortName) {
        this.brandShortName = brandShortName;
    }

    @Override
    public String getBrandCode() {
        return brandCode;
    }

    @Override
    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }
}
