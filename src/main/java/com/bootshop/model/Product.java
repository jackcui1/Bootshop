package com.bootshop.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

@Entity
@Table(name="product")
@Data
public class Product implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message="Product Name must not be Null.")
	private String name;
	
	@ManyToOne
	@JoinColumn(name="firstcategoryid")
	private FirstCategory firstCategory;
	
	@ManyToOne
	@JoinColumn(name="secondcategoryid")
	private SecondCategory secondCategory;
	
	private String brand;
	private String model;
	private String sku;
	
	@Min(value=0,message="Product price must not be  less then zero.")
	private double price;
	
	private String dimensions;
	
	@Column(name="unitinstock")
	private int unitInStock;
	
	private int availability;
	private String description;
	
	private String imagename;
	
	@OneToMany(mappedBy="product",cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JsonIgnore
	private List<CartItem> cartItemList;
	
	@Transient
	private String absolutImagename;



}
