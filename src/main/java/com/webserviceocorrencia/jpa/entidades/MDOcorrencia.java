package com.webserviceocorrencia.jpa.entidades;



import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
	
@Entity
(name = "SAM_OCORRENCIA")



public class MDOcorrencia {

	    @Id
		@GeneratedValue
		private Integer id;
	    
	    
	    
	    private String rua;
	    private String bairro;
	    private String cidade;
	    private String uf;
	    private String descricao;
	    
	    
	    private String data;	    
	    private String tipo;
	    private String anonimo;
	    
	    @OneToOne(cascade =CascadeType.MERGE)
	    private MDUsuario usuario;
	    
	    private String referencia;

		public Integer getId() {
			return id;
		}

		public void setId(Integer id) {
			this.id = id;
		}

		public String getRua() {
			return rua;
		}

		public void setRua(String rua) {
			this.rua = rua;
		}

		public String getBairro() {
			return bairro;
		}

		public void setBairro(String bairro) {
			this.bairro = bairro;
		}

		public String getCidade() {
			return cidade;
		}

		public void setCidade(String cidade) {
			this.cidade = cidade;
		}

		public String getUf() {
			return uf;
		}

		public void setUf(String uf) {
			this.uf = uf;
		}

		public String getDescricao() {
			return descricao;
		}

		public void setDescricao(String descricao) {
			this.descricao = descricao;
		}

		public String getData() {
			return data;
		}

		public void setData(String date) {
			this.data = date;
		}

		public String getTipo() {
			return tipo;
		}

		public void setTipo(String tipo) {
			this.tipo = tipo;
		}

		public String getAnonimo() {
			return anonimo;
		}

		public void setAnonimo(String anonimo) {
			this.anonimo = anonimo;
		}

		public MDUsuario getUsuario() {
			return usuario;
		}

		public void setUsuario(MDUsuario usuario) {
			this.usuario = usuario;
		}

		public String getReferencia() {
			return referencia;
		}

		public void setReferencia(String referencia) {
			this.referencia = referencia;
		}
	    
      

	    
	    
	    
}
		
	    