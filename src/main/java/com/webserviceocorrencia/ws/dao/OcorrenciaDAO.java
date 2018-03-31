package com.webserviceocorrencia.ws.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.webserviceocorrencia.ws.entidades.MDOcorrencia;
import org.springframework.stereotype.Repository;


public class OcorrenciaDAO {

	public EntityManager getEnt() {
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("WebServiceOcorrencia");
		return factory.createEntityManager();
	}

	public Integer cadastrarOcorrencia(MDOcorrencia oc)   {
		
		EntityManager manager = getEnt();
		
		try 
		{
			manager.getTransaction().begin();
				
			manager.persist(oc);
	
			manager.getTransaction().commit();
	
			
			
		
		} finally {
			manager.close();
		}
	
		return 1;
	}
	
	
	
}
