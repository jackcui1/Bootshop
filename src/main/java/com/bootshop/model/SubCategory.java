package com.bootshop.model;


import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

/**
 * @author Guowei Cui
 * @date 7/19/2018 10:43 PM
 */
@Entity
@Data
@Table(name = "secondcategory")
public class SubCategory implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

}
