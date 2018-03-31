package com.webserviceocorrencia.ws.repositorio;


import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class GeraTabelas {

	private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("WebServiceOcorrencia");
	
		public EntityManager getEntityManager() {
			return emf.createEntityManager();
		}

		
		
		
	}

