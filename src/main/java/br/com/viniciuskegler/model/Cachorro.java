package br.com.viniciuskegler.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

@Entity
@Table(name = "cachorro")
@PrimaryKeyJoinColumn(name = "id_animal")
public class Cachorro extends Animal {
	private static final long serialVersionUID = 1L;

	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	private boolean treinado;
	
	public Cachorro() {
	}

	public Cachorro(Long id, String nome, Date nascimento, Double peso, String sexo, String observacao, boolean treinado) {
		super(id, nome, nascimento, peso, sexo, observacao);
		this.treinado = treinado;
	}

	public boolean isTreinado() {
		return treinado;
	}

	public void setTreinado(boolean treinado) {
		this.treinado = treinado;
	}
	
}
