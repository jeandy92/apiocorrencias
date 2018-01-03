package com.webserviceocorrencia.jpa.entidades;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.UniqueConstraint;



@Entity(name = "SAM_USUARIO")
@Table(name = "SAM_USUARIO", uniqueConstraints=
@UniqueConstraint(columnNames={"cpf"}))
       
@NamedQueries({

@NamedQuery(name="MDUsuario.buscaPorNome", 
query="select u from SAM_USUARIO u where u.nome = :nome	"),

@NamedQuery(name="MDUsuario.logarUsuario", 
query="select u from SAM_USUARIO u where u.cpf =:vpCpf"),

@NamedQuery(name="MDUsuario.verificarCPF", 
query="select u from SAM_USUARIO u where u.cpf =:vpCpf"),

@NamedQuery(name="MDUsuario.buscaPorCpf", 
query="select u from SAM_USUARIO u where u.cpf =:cpf"),

@NamedQuery(name="MDUsuario.buscaTodos", 
query="select u from SAM_USUARIO u "), 

})



public class MDUsuario {

	@Id
	@GeneratedValue
	private Long id;
		
	private String cpf;
			
	       //DADOS PESSOAIS
			private String nome;
			private String telefone;
			private String ft_perfil;
			
		
			public String getFt_perfil() {
				return ft_perfil;
			}

			public void setFt_perfil(String ft_perfil) {
				this.ft_perfil = ft_perfil;
			}

			private String dataDeNascimento;

			//DADOS DE ENDERECOS 
			private String cep;
			private String rua;
			private String numero;
			private String complemento;
			private String bairro;
			private String cidade;
			private String uf;
			
			//DADOS DE ACESSO
			private String email;
			private String senha;
			private String confirmarSenha;
			


	public String getCpf() {
		return cpf;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

	public void setCpf(String cPF) {
		cpf = cPF;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getDataDeNascimento() {
		return dataDeNascimento;
	}

	public void setDataDeNascimento(String dataDeNascimento) {
		this.dataDeNascimento = dataDeNascimento;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getRua() {
		return rua;
	}

	public void setRua(String rua) {
		this.rua = rua;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getConfirmarSenha() {
		return confirmarSenha;
	}

	public void setConfirmarSenha(String confirmarSenha) {
		this.confirmarSenha = confirmarSenha;
	}

}
