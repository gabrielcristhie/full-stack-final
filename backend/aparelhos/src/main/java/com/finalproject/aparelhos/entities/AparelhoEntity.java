package com.finalproject.aparelhos.entities;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "aparelhos")
public class AparelhoEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	
	@NotNull
  	@Column
	private String nomeAparelho;
	@Column
	private long numeroDeSerie;
	@Column
	private String modelo;
	@Column
	private String marca;
	@Column
	private Date dataAquisicao;
	@Column
	private String posse;
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getNomeAparelho() {
		return nomeAparelho;
	}
	public void setNomeAparelho(String nomeAparelho) {
		this.nomeAparelho = nomeAparelho;
	}
	public long getNumeroDeSerie() {
		return numeroDeSerie;
	}
	public void setNumeroDeSerie(long numeroDeSerie) {
		this.numeroDeSerie = numeroDeSerie;
	}
	public String getModelo() {
		return modelo;
	}
	public void setModelo(String modelo) {
		this.modelo = modelo;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public Date getDataAquisicao() {
		return dataAquisicao;
	}
	public void setDataAquisicao(Date dataAquisicao) {
		this.dataAquisicao = dataAquisicao;
	}
	public String getPosse() {
		return posse;
	}
	public void setPosse(String posse) {
		this.posse = posse;
	}
	
	
}
