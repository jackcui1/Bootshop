package com.bootshop.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Data
@Table(name = "product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int productid;

    @NotEmpty(message = "Product Name must not be Null.")
    private String productname;

    private String brand;
    private String model;
    private String sku;

    @Min(value = 0, message = "Product price must not be  less then zero.")
    private double price;

    private String dimensions;

    @Column(name = "unitinstock")
    private int unitInStock;

    private int availability;
    private String description;

    private String imagename;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JsonIgnore
    private List<CartItem> cartItemList;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "firstcategoryid")
    private Category category;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "secondcategoryid")
	private SubCategory subCategory;


    @Transient
    private String absolutImagename;

}
