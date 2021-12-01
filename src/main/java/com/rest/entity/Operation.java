package com.rest.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Operation implements Serializable{
	public static final int VIREMENT = 0;
	public static final int VERSEMENT = 1;
	public static final int RETRAIT = 2;
	@Id
	@GeneratedValue
	private Long numeroOperation;
	private Date dateOperation;
	private double montant;
	private int type;
	@ManyToOne
	@JoinColumn(name="codecompte")
	private Compte compte;
	public Operation(Date dateOperation, double montant, int type, Compte compte) {
		super();
		this.dateOperation = dateOperation;
		this.montant = montant;
		this.type = type;
		this.compte = compte;
	}
	
}
