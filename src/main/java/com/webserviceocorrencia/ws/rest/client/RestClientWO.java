package com.webserviceocorrencia.ws.rest.client;

import com.webserviceocorrencia.ws.entidades.MDUsuario;
import com.webserviceocorrencia.ws.rest.modelo.Usuario;

import javax.ws.rs.core.Response;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;
import com.sun.jersey.api.json.JSONConfiguration;

public class RestClientWO {
	
	
	public static void main (String[] args){
		
	
     
	/*	String urlRestService = "http://localhost:62001/RestWO/services/WebserviceOcorrencia/buscarUsuario";
		System.out.println("########### Servi�o Rest Invovcado:+ ["+urlRestService+"]");
		
		
		Client  client  = Client.create();
		
		WebResource webResource = client
		  .resource(urlRestService);

			ClientResponse response = webResource.accept("application/json")
	                   .get(ClientResponse.class);

			if (response.getStatus() != 200) {
			   throw new RuntimeException("Failed : HTTP error code : "
				+ response.getStatus());
			}

			String output = response.getEntity(String.class);

			System.out.println("Output from Server .... \n");
			System.out.println(output);
		
		
		System.out.println("########### Response:[Usuario:");
		//System.out.println("########### Response:[Usuario:"+usu.isUserValido()+"]");
		

		
	
	}
	public void inserirUsuario(){*/
		
		String urlRestService = "http://localhost:8080/RestWO/services/WebserviceOcorrencia/cadastrarUsuario";
		System.out.println("########### Servi�o Rest Invovcado:+ ["+urlRestService+"]");
		
		MDUsuario usu  =  new MDUsuario();
		
		usu.setNome("Vinicius");
		usu.setSenha("jeana");
		usu.setCpf("43131386843");
		
		
		
		
		
		ClientConfig clientConfig = new DefaultClientConfig();
		clientConfig.getFeatures().put(JSONConfiguration.FEATURE_POJO_MAPPING,Boolean.TRUE);
		
		Client client = Client.create(clientConfig);
		WebResource webResource = client.resource(urlRestService);
		
		ClientResponse response = webResource.type("application/json").post(ClientResponse.class,usu);
		usu = response.getEntity(MDUsuario.class);
		
		System.out.println("########### Response:[Usuario:"+usu.getNome()+"]");
		//System.out.println("########### Response:[Usuario:"+usu.isUserValido()+"]");
		
		
	}}
	
	
	
	


