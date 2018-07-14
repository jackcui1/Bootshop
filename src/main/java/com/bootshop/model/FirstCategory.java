package com.bootshop.model;


import javax.persistence.*;
import java.io.Serializable;

/**
 * @author Guowei Cui
 * @date 7/13/2018 7:44 PM
 */
@Entity
@Table(name = "firstcategory")
public class FirstCategory implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;

    private String name;

    private String description;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
