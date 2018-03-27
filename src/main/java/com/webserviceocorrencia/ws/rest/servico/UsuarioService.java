/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.webserviceocorrencia.ws.rest.servico;

import com.google.gson.Gson;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.webserviceocorrencia.ws.dao.OcorrenciaDAO;
import com.webserviceocorrencia.ws.dao.UsuarioDAO;
import com.webserviceocorrencia.ws.entidades.MDUsuario;
import com.webserviceocorrencia.ws.rest.repositorio.GeraTabelas;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import org.apache.commons.codec.binary.Base64;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.springframework.stereotype.Service;

/**
 *
 * @author Jeanderson
 */

@Service 
public class UsuarioService {

        UsuarioDAO dao = new UsuarioDAO();
	EntityManager manager = new GeraTabelas().getEntityManager();
	
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

	public String deletarUsuario() {
            return "Deletar";
	}

	public String alterarUsuario() {
           return  "Alterar";
	}


	public String buscarUsuario(String nome) throws JSONException {

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


	public String logarUsuario( String pCpf,  String pSenha) {

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

        

    
}
