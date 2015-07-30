package com.bpe.jackson;

import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;

import com.bpe.model.User;

public class JSONToJava {

	/**
	 * This shows how to read from a Restful web service by using an HTTP GET.
	 * @param args
	 */
	public static void main(String[] args) {

		ObjectMapper mapper = new ObjectMapper();

		try {
			//Read the user by doing an http get.
			User user = mapper.readValue(new URL("https://connector.host/api/jwas/manifest.json"), User.class);
			System.out.println("User's name: "+user.getName());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
