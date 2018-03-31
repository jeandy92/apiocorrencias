/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webserviceocorrencia.ws.servico;

import com.webserviceocorrencia.ws.dao.OcorrenciaDAO;
import com.webserviceocorrencia.ws.entidades.MDOcorrencia;
import com.webserviceocorrencia.ws.entidades.MDUsuario;
import com.webserviceocorrencia.ws.repositorio.GeraTabelas;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.codehaus.jettison.json.JSONException;
import org.springframework.stereotype.Service;


/**
 *
 * @author Jeanderson
 */
@Service
public class OcorrenciaService {
    
    
	OcorrenciaDAO ocorrenciadao = new OcorrenciaDAO();
	EntityManager manager = new GeraTabelas().getEntityManager();
    
    	//Servicos de ocorrencia 
    
	public String cadastrarOcorrencia(MDOcorrencia ocorrencia) {

		// PEGAR O ID QUE VIER E PROCURAR UM USUAÁRIO COM ESSE ID

		// System.out.println("ID DO USUÁRIO DA OCORRENCIA
		// \n"+ocorrencia.getUsuario().getId());
		MDUsuario usu = ocorrencia.getUsuario();

		System.out.println("id do usuário " + usu.getBairro());

		if (ocorrenciadao.cadastrarOcorrencia(ocorrencia) == 1) {

			System.out.println("OCORRÊNCIA CADASTRADA COM SUCESSO !!");
			return " ";
		} else {
			if (ocorrenciadao.cadastrarOcorrencia(ocorrencia) == 0) {
				System.out.println("ERRO AO CADASTRAR OCORRENCIA");
				return "ERRO AO CADASTRAR OCORRENCIA";
			}
		}

		return "OCORRÊNCIA CADASTRADA COM SUCESSO !!";
	}
	
	public List<MDUsuario> buscarUsuarioOcorrencia(String cpf) throws JSONException {

		EntityManager manager = new GeraTabelas().getEntityManager();
		try {

			return manager.createNamedQuery("MDUsuario.buscaPorCpf", MDUsuario.class).setParameter("cpf", cpf)
					.getResultList();

		} catch (NoResultException nre) {
			System.out.println("Usuário não encontrado" + cpf);

		}
		return null;

	}
	
	public List<MDOcorrencia> listaOcorrencias(String bairro) throws JSONException{
	   
	String buscaOcorrencia = "select r from SAM_OCORRENCIA r where r.bairro = :bairro";
	
	
	return  manager .createQuery(buscaOcorrencia,MDOcorrencia.class)
			.setParameter("bairro", bairro)
			.getResultList();
	
	
	}
   
}
