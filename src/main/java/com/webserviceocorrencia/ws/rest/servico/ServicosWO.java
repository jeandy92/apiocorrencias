package com.webserviceocorrencia.ws.rest.servico;

import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;

import java.util.ArrayList;

import java.util.List;
import com.google.gson.Gson;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import com.webserviceocorrencia.jpa.dao.OcorrenciaDAO;
import com.webserviceocorrencia.jpa.dao.UsuarioDAO;
import com.webserviceocorrencia.jpa.entidades.MDOcorrencia;
import com.webserviceocorrencia.jpa.entidades.MDUsuario;
import com.webserviceocorrencia.ws.rest.repositorio.GeraTabelas;

@Path("/WebserviceOcorrencia")
public class ServicosWO {
	
	
	 UsuarioDAO  dao =  new UsuarioDAO();
	 OcorrenciaDAO ocorrenciadao  =  new OcorrenciaDAO();
	
	@Path("/cadastrarUsuario")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })

	public String cadastrarUsuario(MDUsuario usu) {


		 System.out.println(usu.getDataDeNascimento());
		
		 try {
			if(dao.cadastraUsuario(usu)==1){
				 
			    System.out.println("USUARIO CADASTRADO COM SUCESSO !!");
			    return "USUARIO CADASTRADO COM SUCESSO !!";
			 } 
			else {
				if(dao.cadastraUsuario(usu)==0)
				{ 
					System.out.println("CPF JÁ CADASTRADO");
				 return "CPF JÁ CADASTRADO";
				}
			}
			
		} catch (MySQLIntegrityConstraintViolationException e) {
			
			  System.out.println(usu.getCpf()+"CPF JÁ CADASTRADO ");
		}
         
		return "USUARIO CADASTRADO COM SUCESSO !!";
	}
  	
	public void deletarUsuario(MDUsuario usu){
		
	}
	
	public void alterarUsuario(MDUsuario usu){
		
	}

	@Path("/buscarUsuario/{nome}")
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String buscarUsuario(@PathParam("nome") String nome) throws JSONException {

		String result;
		EntityManager manager = new GeraTabelas().getEntityManager();
try {		
	        List<MDUsuario> usuarios = new ArrayList<MDUsuario>();

			
				usuarios = manager.createNamedQuery("MDUsuario.buscaPorNome", MDUsuario.class)
						.setParameter("nome", "Jeanderson").getResultList();
			
	        JSONArray json   =  new JSONArray("[a]");
	        //result=  new Gson().toJson( new JSONObject().put("usuarios",usuarios));
	        result  = new Gson().toJson(json =  new JSONArray(usuarios));
	    
	        System.out.println(" usus = Nome vindo da requisição ----- "+nome);
	        
	        
			
		} catch (NoResultException nre) {
			System.out.println("Usuário não encontrado"+nome);
			 result =  "USUÁRIO NÃO EXISTE EM NOSSA BASE DE DADOS";
		}

	return result;
	}
	
	
	@Path("/logarUsuario/{ppCpf}-{ppSenha}")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON ,MediaType.TEXT_PLAIN})
	public String logarUsuario(@PathParam("ppCpf") String pCpf, @PathParam("ppSenha") String pSenha) {

		EntityManager manager = new GeraTabelas().getEntityManager();
		System.out.println("Logou\n\n");
		System.out.println("CPF ====" + pCpf + "SENHA:--- " + pSenha);

		try {

			MDUsuario usuario = (MDUsuario) manager.createNamedQuery("MDUsuario.logarUsuario", MDUsuario.class)
					.setParameter("vpCpf", pCpf).getSingleResult();
           
			if(usuario.getSenha().equals(pSenha))
			
			{
				System.out.println("SENHA CORRETA!:"+usuario.getSenha());
				return new Gson().toJson(usuario);
			}
			
			else 
			
			{
				System.out.println("SENHA INCORRETA!:  "+usuario.getSenha()+"   DIFERENTE:  "+pSenha);
				return "SENHA INCORRETA!";	
			}
			
		} catch (NoResultException nre) {
			System.out.println("Usuário não encontrado" + pCpf);
			return "USUÁRIO NÃO CADASTRADO NA BASE DE DADOS";
		}

	}

	@Path("/cadastrarOcorrencia")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String cadastrarOcorrencia(MDOcorrencia ocorrencia){
	
		//PEGAR O ID QUE VIER E PROCURAR UM USUAÁRIO COM ESSE ID 
		
		//System.out.println("ID DO USUÁRIO DA OCORRENCIA \n"+ocorrencia.getUsuario().getId());
		MDUsuario usu = ocorrencia.getUsuario();
		
		System.out.println("id do usuário "+usu.getBairro());
		
		
		
			if(ocorrenciadao.cadastrarOcorrencia(ocorrencia)==1){
				 
			    System.out.println("OCORRÊNCIA CADASTRADA COM SUCESSO !!");
			    return " ";
			 } 
			else {
				if(ocorrenciadao.cadastrarOcorrencia(ocorrencia)==0)
				{ 
					System.out.println("ERRO AO CADASTRAR OCORRENCIA");
				 return "ERRO AO CADASTRAR OCORRENCIA";
				}
			}
			
		
		return "OCORRÊNCIA CADASTRADA COM SUCESSO !!";
	}
		
	
	@Path("/buscarUsuarioOcorrencia/{cpf}")
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String buscarUsuarioOcorrencia(@PathParam("cpf") String cpf) throws JSONException {

		MDUsuario usuario;
		String result;
		EntityManager manager = new GeraTabelas().getEntityManager();
		try {		
	        

	        usuario = manager.createNamedQuery("MDUsuario.buscaPorCpf", MDUsuario.class)
	        		.setParameter("cpf", cpf).getSingleResult();
	       
	      
	        result  = new Gson().toJson(usuario,MDUsuario.class);
	    
	        System.out.println("Nome vindo da requisição ----- "+cpf);

			
		} catch (NoResultException nre) {
			System.out.println("Usuário não encontrado"+cpf);
			 result =  "USUÁRIO NÃO EXISTE EM NOSSA BASE DE DADOS";
		}

	return result;
	}
	
	
	@Path("/cadastrarImagemPerfil")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public String armazenarImagemPerfil(MDUsuario usuario){
	
		EntityManager manager = new GeraTabelas().getEntityManager();
		
		System.out.println("STRING VINDA DO ANDROID " + usuario.getFt_perfil());
		
		String imagemNova  =  usuario.getFt_perfil();
		
		usuario = manager.createNamedQuery("MDUsuario.buscaPorCpf", MDUsuario.class)
        		.setParameter("cpf", usuario.getCpf()).getSingleResult();
		
		usuario.setFt_perfil(imagemNova);
		
		System.out.println("ID USUARIO "+usuario.getId());
		
		 try {
				if(dao.alterarUsuario(usuario)==1){
					 
				    System.out.println("USUARIO ALTERADO  COM SUCESSO !!");
				    return "USUARIO ALTERADO  COM SUCESSO !!";
				 } 
				else {
				
					System.out.println("ERRO AO ALTERAR USUARIO");
				 return "ERRO AO ALTERAR USUARIO";
				
			}
				} catch (NoResultException nre) {
					System.out.println("Usuário não encontrado");
					 
				}
			
		
		return "USUARIO CADASTRADO COM SUCESSO !!";
	
		
	}	
}



	



