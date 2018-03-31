package com.webserviceocorrencia.ws.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import com.google.gson.Gson;
import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.webserviceocorrencia.ws.entidades.MDUsuario;
import org.springframework.stereotype.Repository;

@Repository
public class UsuarioDAO {
	
	public EntityManager getEnt() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("WebServiceOcorrencia");
		return factory.createEntityManager();
	}

	public Integer cadastraUsuario(MDUsuario usu) throws MySQLIntegrityConstraintViolationException, org.hibernate.exception.ConstraintViolationException {
		
		EntityManager manager = getEnt();
		
				
		if(verificaCPF(usu.getCpf()))
		
		{
		
		try 
		{
			manager.getTransaction().begin();
			
			manager.persist(usu);

			manager.getTransaction().commit();

		
			
			
		} finally {
			manager.close();
		}
	
		return 1; 
		}//if
		
		else
		{
			System.out.println("ELSE E ENVIA 0  Verificando o Cpf informado");
		return 0;	
		}
			
	}
	
	public boolean verificaCPF(String cpf){

		
		EntityManager manager = getEnt();
		
		
		try {
			MDUsuario usuario = (MDUsuario) manager.createNamedQuery("MDUsuario.verificarCPF", MDUsuario.class)
					.setParameter("vpCpf", cpf).getSingleResult();

			if (usuario.getCpf() != null)

			{
				System.out.println(" IF  Verificando o Cpf informado");
				manager.close();
				return false;
				

			}

			else

			   {manager.close();
				return true;
			}
		} catch (NoResultException e) {
			manager.close();
			return true;
		}
}

	public String logaUsuario(String pCpf,String pSenha){

		EntityManager manager = getEnt();

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

		    } 
		    
		catch (NoResultException nre) 
			{
					System.out.println("Usu�rio n�o encontrado" + pCpf);
					return null;
			}

	}

    public Integer alterarUsuario (MDUsuario usu){
    	

		EntityManager manager = getEnt();
		MDUsuario usuario = manager.merge(usu);
		
		try 
		{
			manager.getTransaction().begin();
			
			manager.merge(usuario);

			manager.getTransaction().commit();

		
			
			
		} finally {
			manager.close();
		}
	
	
    	
	  return 1;
	  
	  
	  
  }
}



