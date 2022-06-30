package org.technohaven.core.entities;

import org.broadleafcommerce.common.presentation.AdminPresentation;
import org.broadleafcommerce.common.presentation.AdminPresentationClass;
import org.broadleafcommerce.common.presentation.AdminPresentationToOneLookup;

import javax.persistence.*;

@Entity
@Table(name = "BLC_MODEL")
@Inheritance(strategy = InheritanceType.JOINED)
@AdminPresentationClass(friendlyName = "ModelImpl_Model")
public class ModelImpl implements VehicleModel{

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "Model_Id", nullable = false)
   protected Long id;

   @Column(name = "Model_Name", nullable = false)
   @AdminPresentation(friendlyName = "Model_Name", order = 2, prominent = true, gridOrder = 2)
   protected String name;

   @Column(name = "Model_Short_Name", nullable = false)
   @AdminPresentation(friendlyName = "Model_Short_Name", order = 3, prominent = true, gridOrder = 3)
   protected String modelShortName;

   @Column(name = "Model_Code")
   @AdminPresentation(friendlyName = "Model_Code", order = 4, prominent = true, gridOrder = 4)
   protected String modelCode;

   @ManyToOne(targetEntity = BrandImpl.class, optional = false)
   @JoinColumn(name = "Brand_Id")
   @AdminPresentation(friendlyName = "Model_Brand", order = 1, prominent = true, gridOrder = 1)
   @AdminPresentationToOneLookup()
   protected Brand brand;

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
   public String getModelShortName() {
      return modelShortName;
   }

   @Override
   public void setModelShortName(String modelShortName) {
      this.modelShortName = modelShortName;
   }

   @Override
   public String getModelCode() {
      return modelCode;
   }

   @Override
   public void setModelCode(String modelCode) {
      this.modelCode = modelCode;
   }

   @Override
   public Brand getBrand() {
      return brand;
   }

   @Override
   public void setBrand(Brand brand) {
      this.brand = brand;
   }
}
