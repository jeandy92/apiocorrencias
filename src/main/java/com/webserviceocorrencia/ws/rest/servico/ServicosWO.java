/*package com.webserviceocorrencia.ws.rest.servico;

import javax.ws.rs.core.MediaType;

import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.hibernate.Criteria;
import org.hibernate.criterion.Example;

import java.util.ArrayList;

import java.io.*;

import java.util.List;
import com.google.gson.Gson;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import org.apache.commons.codec.binary.Base64;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import org.springframework.stereotype.Service;

import com.webserviceocorrencia.jpa.dao.OcorrenciaDAO;
import com.webserviceocorrencia.jpa.dao.UsuarioDAO;
import com.webserviceocorrencia.jpa.entidades.MDOcorrencia;
import com.webserviceocorrencia.jpa.entidades.MDUsuario;
import com.webserviceocorrencia.ws.rest.repositorio.GeraTabelas;

@Service
public class ServicosWO {

	UsuarioDAO dao = new UsuarioDAO();
	OcorrenciaDAO ocorrenciadao = new OcorrenciaDAO();
	EntityManager manager = new GeraTabelas().getEntityManager();
	
/*	//Servicos de Usuarios
	@Path("/cadastrarImagemPerfil")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })

	public String armazenarImagemPerfil(MDUsuario usuario) throws IOException {

		EntityManager manager = new GeraTabelas().getEntityManager();

		System.out.println("STRING VINDA DO ANDROID " + usuario.getFt_perfil());

		String imagemNova = usuario.getFt_perfil();

		usuario = manager.createNamedQuery("MDUsuario.buscaPorCpf", MDUsuario.class)
				.setParameter("cpf", usuario.getCpf()).getSingleResult();

		usuario.setFt_perfil(imagemNova);

		System.out.println("ID USUARIO " + usuario.getId());

		try {
			if (dao.alterarUsuario(usuario) == 1) {

				System.out.println("USUARIO ALTERADO  COM SUCESSO !!");

				usuario = manager.createNamedQuery("MDUsuario.buscaPorCpf", MDUsuario.class)
						.setParameter("cpf", usuario.getCpf()).getSingleResult();

				// Converting a Base64 String into Image byte array
				// String stringencoder =
				// Base64.encodeBase64String(usuario.getFt_perfil().getBytes("UTF-8"));

				byte[] imageByteArray = Base64.decodeBase64(usuario.getFt_perfil());

				// Cria um novo diretório para cada usuário e armazena sua
				// imagem de perfil
				File diretorio = new File("C:/Users/Jeanderson/Desktop/FTP-TESTE/" + usuario.getNome());
				diretorio.mkdir();

				// Write a image byte array into file system
				FileOutputStream imageOutFile = new FileOutputStream(diretorio + "/" + usuario.getNome() + ".png");

				imageOutFile.write(imageByteArray);

				imageOutFile.flush();
				imageOutFile.close();

				System.out.println("Imagem salva com suceso");

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

	@Path("/cadastrarUsuario")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })

	public String cadastrarUsuario(MDUsuario usu) {

		System.out.println(usu.getDataDeNascimento());

		try {
			if (dao.cadastraUsuario(usu) == 1) {

				System.out.println("USUARIO CADASTRADO COM SUCESSO !!");
				return "USUARIO CADASTRADO COM SUCESSO !!";
			} else {
				if (dao.cadastraUsuario(usu) == 0) {
					System.out.println("CPF JÁ CADASTRADO");
					return "CPF JÁ CADASTRADO";
				}
			}

		} catch (MySQLIntegrityConstraintViolationException e) {

			System.out.println(usu.getCpf() + "CPF JÁ CADASTRADO ");
		}

		return "USUARIO CADASTRADO COM SUCESSO !!";
	}

	public void deletarUsuario(MDUsuario usu) {

	}

	public void alterarUsuario(MDUsuario usu) {

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
					.setParameter("nome", nome).getResultList();

			JSONArray json = new JSONArray(usuarios);
			// result= new Gson().toJson( new
			// JSONObject().put("usuarios",usuarios));
			result = new Gson().toJson(json);

			System.out.println(" usus = Nome vindo da requisição ----- " + nome);

		} catch (NoResultException nre) {
			System.out.println("Usuário não encontrado" + nome);
			result = "USUÁRIO NÃO EXISTE EM NOSSA BASE DE DADOS";
		}

		return result;
	}

	@Path("/logarUsuario/{ppCpf}-{ppSenha}")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.TEXT_PLAIN })
	public String logarUsuario(@PathParam("ppCpf") String pCpf, @PathParam("ppSenha") String pSenha) {

		EntityManager manager = new GeraTabelas().getEntityManager();
		System.out.println("Logou\n\n");
		System.out.println("CPF ====" + pCpf + "SENHA:--- " + pSenha);

		try {

			MDUsuario usuario = (MDUsuario) manager.createNamedQuery("MDUsuario.logarUsuario", MDUsuario.class)
					.setParameter("vpCpf", pCpf).getSingleResult();

			if (usuario.getSenha().equals(pSenha))

			{
				System.out.println("SENHA CORRETA!:" + usuario.getSenha());
				return new Gson().toJson(usuario);
			}

			else

			{
				System.out.println("SENHA INCORRETA!:  " + usuario.getSenha() + "   DIFERENTE:  " + pSenha);
				return "SENHA INCORRETA!";
			}

		} catch (NoResultException nre) {
			System.out.println("Usuário não encontrado" + pCpf);
			return "USUÁRIO NÃO CADASTRADO NA BASE DE DADOS";
		}

	}
*/
	
/*	//Servicos de ocorrencia 
	@Path("/cadastrarOcorrencia")
	@POST
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
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

	@Path("/buscarUsuarioOcorrencia/{cpf}")
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	public List<MDUsuario> buscarUsuarioOcorrencia(@PathParam("cpf") String cpf) throws JSONException {

		EntityManager manager = new GeraTabelas().getEntityManager();
		try {

			return manager.createNamedQuery("MDUsuario.buscaPorCpf", MDUsuario.class).setParameter("cpf", cpf)
					.getResultList();

		} catch (NoResultException nre) {
			System.out.println("Usuário não encontrado" + cpf);

		}
		return null;

	}
	
	@Path("/buscarOcorBairro/{bairro}")
	@GET
	@Consumes({ MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_JSON })
	
	public List<MDOcorrencia> listaOcorrencias(@PathParam("bairro") String bairro) throws JSONException{
	   
	String buscaOcorrencia = "select r from SAM_OCORRENCIA r where r.bairro = :bairro";
	
	
	return  manager
			.createQuery(buscaOcorrencia,MDOcorrencia.class)
			.setParameter("bairro", bairro)
			.getResultList();
	
	
	}
}
*/