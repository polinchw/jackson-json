package com.bpe.jackson;

import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;

import com.bpe.model.User;

public class JavaToJSON {

	/**
	 * This will POST a User in JSON format to a URL.
	 * @param args
	 */
	public static void main(String[] args) {
		 
		User user = new User();
		user.setName("Tom");
		user.setAge(30);
		
	    ObjectMapper mapper = new ObjectMapper();	 
		try {	 
			// POST a User in JSON format to a URL
			URL url = new URL("https://connector.host/api/jwas/someendpoint");			
			mapper.writeValue(url.openConnection().getOutputStream(), user); 
				 
		} catch (Exception e) {	 
			e.printStackTrace();
		}
	}
}
