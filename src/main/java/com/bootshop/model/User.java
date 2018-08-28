package com.bootshop.model;

import java.io.Serializable;
import java.util.Date;
import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;
import org.hibernate.validator.constraints.NotEmpty;

@Entity
@Data
@Table(name = "user")
public class User implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "userid")
	private int userid;

	//@NotEmpty(message = "*Please provide your name")
	private String username;

	@Column(name = "password")
	//@Length(min = 5, message = "*Your password must have at least 5 characters")
	@NotEmpty(message = "*Please provide your password")
	private String password;

	private int enabled;

	@Column(name = "create_at", nullable = false, updatable = false)
	private Date createAt;

	@Column(name = "loggedin_at", nullable = true)
	private Date loggedInAt;

	private String loginIp;

	@OneToOne
	@JoinColumn(name = "roleid")
	private Role role;
	

	

	

}
