package com.kynsof.scheduled.infrastructure.dao;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "profile")
public class Profile {
    @Id
    private UUID id;

    @Size(max = 50)
    private String name;

    @Size(max = 50)
    private String lastname;

    private Date birthdayDate;

    private String CI;

//	private String pais;
//
//	private String provincia;
//
//	private String ciudad;
//
//	@JsonIgnore
//	@ManyToOne
//	@JoinColumn(name = "province_id")
//	private Location province;
//
//	@JsonIgnore
//	@ManyToOne
//	@JoinColumn(name = "city_id")
//	private Location city;
//
	@Size(max = 40)
	private String telephone;
//
	private String gender;
//
//	@ManyToOne
//	@JoinColumn(name = "language_id")
//	private Language language;
//
//	@Enumerated(EnumType.STRING)
//	@Column(length = 20)
//	private ECredentialType credentialType;
//
    @JsonIgnore
	@OneToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "user_id")
	private User user;


}