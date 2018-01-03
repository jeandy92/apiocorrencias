package com.webserviceocorrencia.ws.rest.repositorio;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.EntityManager;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.webserviceocorrencia.jpa.dao.UsuarioDAO;
import com.webserviceocorrencia.jpa.entidades.MDUsuario;
import com.webserviceocorrencia.ws.rest.modelo.Usuario;

public class PopularUsuarios {

	public static void main(String[] args) throws Exception {
		
		UsuarioDAO dao =  new UsuarioDAO();
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		
		
	MDUsuario cad  = new MDUsuario ();
	
			cad.setNome("XUXA");
			cad.setSenha("1234");
			cad.setCpf("1121298710");
			cad.setTelefone("41622246");
			cad.setDataDeNascimento("22/03/1995");
			cad.setCep("06433210");
			cad.setRua("Rua Joaquim de Abreu");
			cad.setNumero("56");
			cad.setComplemento("54");
			cad.setComplemento("casa1"); 
			cad.setBairro("Jardim Silveira");
			cad.setCidade("Barueri");
			cad.setUf("SP");
			cad.setEmail("jeanderson.almeida@hotmail.com"); 
			cad.setSenha("1234");
			cad.setConfirmarSenha("1234");
			
		try {
			
			if (dao.cadastraUsuario(cad) == 1) 
			{
				System.out.println("USUARIO CADASTRADO COM SUCESSO !!");
			} 
			else
			{
				System.out.println("JÁ EXISTE ESTE CPF !!");
			}
			
			}

			
		   catch (MySQLIntegrityConstraintViolationException e) 

			{
			   System.out.println(cad.getCpf() + "CPF JÁ CADASTRADO ");
			}
		 
			catch (org.hibernate.exception.ConstraintViolationException  e) 
			
			{
			   System.out.println(cad.getCpf() + "CPF JÁ CADASTRADO ");
			}

		

	}
}