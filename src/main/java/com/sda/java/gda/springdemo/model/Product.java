package com.sda.java.gda.springdemo.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products")
public class Product extends BaseEntity {

  @NotBlank
  @Size(max = 90)
  @Column(unique = true, nullable = false)
  private String name;


  @Min(0)
  @Max(10000)
  @Column(nullable = false)
  private Double price;


}
