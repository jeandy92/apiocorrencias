package com.webserviceocorrencia.ws.repositorio;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.persistence.EntityManager;

import com.webserviceocorrencia.ws.dao.OcorrenciaDAO;
import com.webserviceocorrencia.ws.entidades.MDOcorrencia;
import com.webserviceocorrencia.ws.entidades.MDUsuario;

public class PopularOcorrencia {
	
	public static void main (String [] args){
		
		//EntityManager manager = new GeraTabelas().getEntityManager();
		
		DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
		OcorrenciaDAO dao  =  new OcorrenciaDAO();
		
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
		
				
		MDOcorrencia ocorrencia  = new MDOcorrencia();
		
		ocorrencia.setTipo("Assalto");
		ocorrencia.setData(("20/11/2017"));
		ocorrencia.setDescricao("Assalto a m�o armada");
		ocorrencia.setRua("Rua dos Hornet");
		ocorrencia.setBairro("Jardim ALcantara");
		ocorrencia.setCidade("Jandira");
		ocorrencia.setReferencia("Sem referencia");
		ocorrencia.setUf("SP");
		ocorrencia.setUsuario(cad);
		ocorrencia.setAnonimo("N");	
		 
		
		if(dao.cadastrarOcorrencia(ocorrencia)== 1){
		
			System.out.println("OCORRENCIA CADASTRADA COM SUCESSO " + "\n NUMERO DA OCORRENCIA:" +ocorrencia.getId());
		}
		
		
		
		
		/*
	    manager.getTransaction().begin();
		
	    // persistindo as contas
	    manager.persist(ocorrencia);
			
		manager.getTransaction().commit();

		manager.close();*/
		
	}

}
