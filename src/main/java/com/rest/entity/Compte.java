package com.rest.entity;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compte {
	@Id
	@GeneratedValue
	private long code;
	private double solde;
	private Date dateCreation;
	@OneToMany(fetch=FetchType.EAGER,mappedBy="compte")
	@JsonIgnore
	private Collection<Operation> operations;
	
}

	
	

