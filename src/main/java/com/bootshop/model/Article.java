package com.bootshop.model;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;

/**
 * this is  Entity of article  for publishing
 * @author Guowei Cui
 * @date 8/12/2018 11:27 PM
 */

@Entity
@Data
@Table(name = "article")
public class Article implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    @Length(min = 5, message = "*Your title must have at least 5 characters")
    @NotEmpty(message = "*Please provide title")
    private String title;

    private String desc;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;

    private int type;

    private String url;

    private String imagename;


    @Column(name = "create_at", nullable = false, updatable = false)
    private Date createAt;

    @Column(name = "update_at", nullable = true, updatable = false)
    private Date updateAt;


}
