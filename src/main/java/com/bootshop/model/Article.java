package com.bootshop.model;


import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;

/**
 * this is  Entity of article  for publishing
 * @author Guowei Cui
 * @date 8/12/2018 11:27 PM
 */

@Entity
@Data
@Table(name = "article")
public class Article {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "title", nullable = false)
    @Length(min = 5, message = "*Your title must have at least 5 characters")
    @NotEmpty(message = "*Please provide title")
    private String title;

    @Column(name = "body", columnDefinition = "TEXT")
    private String body;


    @Column(name = "create_at", nullable = false, updatable = false)
    private Date createAt;

    @Column(name = "update_at", nullable = true, updatable = false)
    private Date updateAt;

    @ManyToOne
    @JoinColumn(name = "userid", referencedColumnName = "userid", nullable = false)
    @NotNull
    private User user;

}
