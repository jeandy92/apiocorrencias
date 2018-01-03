package com.webserviceocorrencia.ws.rest.client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.json.JsonObject;

import com.google.gson.Gson;
import com.google.gson.stream.JsonReader;

public class TestaUrlAndroid {

	
	public static void main ( String [] args ) throws IOException{
		
	//cadastraUsuario("http://pokeapi.co/api/v2/pokemon/1/");
		cadastraUsuario("http://localhost:62001/RestWO/services/WebserviceOcorrencia/buscarUsuario/XUXA/");
	
	}
	
	public static void cadastraUsuario(String url) throws IOException {
		
		

			URL http =  /*new URL("https://api.github.com/");*/ new URL("http://192.168.53.109:62001/RestWO/services/WebserviceOcorrencia/buscarUsuario/XUXA/");

			HttpURLConnection urlConnection = (HttpURLConnection) http.openConnection();
			urlConnection.setRequestMethod("GET");
			//urlConnection.setRequestProperty("User-Agent", "my-rest-app-v0.1");
			//urlConnection.setRequestProperty("Accept", "application/vnd.github.v3+json");
			//urlConnection.setRequestProperty("Contact-Me", "hathibelagal@example.com");
			urlConnection.connect();
			//urlConnection.get

			
			
			switch (urlConnection.getResponseCode()) {
			
			case 200:
				
				InputStream responseBody = urlConnection.getInputStream();
				InputStreamReader responseBodyReader = new InputStreamReader(responseBody, "UTF-8");
				JsonReader jsonReader = new JsonReader(responseBodyReader);
				
				
				System.out.println("Resposta: "+jsonReader.toString());
				System.out.println("Ok");
				  jsonReader.beginObject(); // Start processing the JSON object
                  while (jsonReader.hasNext()) { // Loop through all keys
                      String key = jsonReader.nextName(); // Fetch the next key
                      if (key.equals("organization_url")) { // Check if desired key
                          // Fetch the value as a String
                          String value = jsonReader.nextString();

                          System.out.println(value);
                          // ...

                          break; // Break out of the loop
                      } else {
                          jsonReader.skipValue(); // Skip values of other keys
                      }
                  }		
			
				jsonReader.close();
				urlConnection.disconnect();
				break;

			case 404:
			
				System.out.println("SERVIDOR FORA  OU INACESSIVEL");
				System.out.println(urlConnection.getURL());
				
				
			default:
				break;
			}
		
		}
	}


/* InputStream inputStream = urlConnection.getInputStream();
if (inputStream == null) {
    return null;
}
Gson gson  = new Gson();
				
				
				JsonObject json =gson.fromJson(jsonReader,JsonObject.class);
				
				int id = json.getInt("id");
				String nome  =  json.getString("nome");
				
				System.out.println("ID JSON:" + id );
				System.out.println("String JSON :" + nome );
				
BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
String linha;
StringBuffer buffer = new StringBuffer();
while ((linha = reader.readLine()) != null) {
buffer.append(linha + "\n");
}

if (buffer.length() == 0) {
return null;
}

if (urlConnection != null) {
urlConnection.disconnect();
}

if (reader != null) {

try {
    reader.close();
} catch (final IOException e) {
    System.out.println("Erro Erro fechando o stream");
}

}*/
