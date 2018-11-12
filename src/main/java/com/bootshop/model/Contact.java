package com.bootshop.model;


import lombok.Data;
import javax.persistence.*;
import java.io.Serializable;




@Entity
@Data
@Table(name = "contact")
public class Contact implements Serializable{

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "fistname", nullable = false)
    private String firstname;

    @Column(name = "lastname", nullable = false)
    private String lastname;

    @Column(name = "email", nullable = false)
    private String email;

    private String message;

    @ManyToOne
    @JoinColumn(name = "articleid")
    private Article article;
}
