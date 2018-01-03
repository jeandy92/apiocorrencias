package com.webserviceocorrencia.ws.rest.modelo;

public class Usuario {
	
		//DADOS PARA TESTE DE CONEXAO
        private boolean userValido;	
	
	
		//DADOS PESSOAIS
		private String nome;
		private String CPF;
		private String telefone;
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
		
		
		
	//Get e sets	
		
		public String getNome() {
			return nome;
		}
		public boolean isUserValido() {
			return userValido;
		}
		public void setUserValido(boolean userValido) {
			this.userValido = userValido;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getCPF() {
			return CPF;
		}
		public void setCPF(String cPF) {
			CPF = cPF;
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
