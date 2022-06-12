package br.com.viniciuskegler.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "gato")
@PrimaryKeyJoinColumn(name = "id_animal")
public class Gato extends Animal {
	private static final long serialVersionUID = 1L;
	
	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	private boolean FIV;
	
	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	private boolean FELV;
	
	public Gato() {
	}

	public Gato(Long id, String nome, Date nascimento, Double peso, String sexo, String observacao, boolean FIV, boolean FELV) {
		super(id, nome, nascimento, peso, sexo, observacao);
		this.FIV = FIV;
		this.FELV = FELV;
	}

	public boolean isFIV() {
		return FIV;
	}

	public void setFIV(boolean fIV) {
		FIV = fIV;
	}

	public boolean isFELV() {
		return FELV;
	}

	public void setFELV(boolean fELV) {
		FELV = fELV;
	}
	
	
	
}
