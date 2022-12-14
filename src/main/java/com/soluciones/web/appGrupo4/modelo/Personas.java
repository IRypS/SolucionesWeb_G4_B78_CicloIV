package com.soluciones.web.appGrupo4.modelo;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;


@Entity
@Table(name = "persona")
public class Personas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idPerson;
	
	private String nombre;
	
	private String lastname;
	
	@DateTimeFormat(iso = ISO.DATE)
	private LocalDate birthday;
	
	private String countryId;
	
	
	
	public Personas() {
		// TODO Auto-generated constructor stub
	}


	public Personas(int idPerson, String nombre, String lastname, LocalDate birthday, String countryId) {
		super();
		this.idPerson = idPerson;
		this.nombre = nombre;
		this.lastname = lastname;
		this.birthday = birthday;
		this.countryId = countryId;
	}


	public int getIdPerson() {
		return idPerson;
	}

	public void setIdPerson(int idPerson) {
		this.idPerson = idPerson;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public LocalDate getBirthday() {
		return birthday;
	}

	public void setBirthday(LocalDate birthday) {
		this.birthday = birthday;
	}

	public String getCountryId() {
		return countryId;
	}

	public void setCountryId(String countryId) {
		this.countryId = countryId;
	}
	
}
