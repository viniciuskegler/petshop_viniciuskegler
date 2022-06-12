package br.com.viniciuskegler.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "comportamento")
public class Comportamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(nullable = false)
	private String tipo;
	
	@Column(nullable = false)
	private String descricao;
	
	@Column(columnDefinition = "BOOLEAN DEFAULT false")
	private boolean ativo;
		
	public Comportamento() {
	}
	
	public Comportamento(Long id, String tipo, String descricao, boolean ativo) {
		super();
		this.id = id;
		this.tipo = tipo;
		this.descricao = descricao;
		this.ativo = ativo;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public boolean isAtivo() {
		return ativo;
	}

	public void setFalso(boolean ativo) {
		this.ativo = ativo;
	}

	@Override
	public String toString() {
		return "Comportamento [id=" + id + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Comportamento other = (Comportamento) obj;
		return Objects.equals(id, other.id);
	}
	
}
