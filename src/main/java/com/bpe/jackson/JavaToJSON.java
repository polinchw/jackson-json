package com.bpe.jackson;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Logger;

import javax.xml.bind.DatatypeConverter;

import org.codehaus.jackson.map.ObjectMapper;

import com.bpe.model.User;

@SuppressWarnings("restriction")
public class JavaToJSON {
	
	private final static Logger logger = Logger.getLogger(JavaToJSON.class.getName());

	/**
	 * This will POST a User in JSON format to a URL.
	 * 
	 * @param args
	 */
	public static void main(String[] args) throws Exception {

		User user = new User();
		user.setName("Tom");
		user.setAge(30);

		ObjectMapper mapper = new ObjectMapper();
		URL obj = new URL("http://someurl");
		HttpURLConnection con = (HttpURLConnection) obj.openConnection();

		con.setRequestMethod("POST");
		con.setDoOutput(true);
		String userpass = "user : test";		
		String basicAuth = "Basic "
				+ DatatypeConverter.printBase64Binary(userpass.getBytes());

		con.setRequestProperty("Authorization", basicAuth);
		con.setRequestProperty("Content-Type", "text/plain");
		StringBuilder response;
		DataOutputStream wr = null;
		BufferedReader in = null;
		try {
			wr = new DataOutputStream(con.getOutputStream());
			StringWriter sr = new StringWriter();
			mapper.writeValue(sr, user);
			wr.writeBytes(sr.toString());			
			logger.info("post data: " + sr.toString());
			wr.flush();

			int responseCode = con.getResponseCode();
			logger.info("Response Code : " + responseCode);

			in = new BufferedReader(new InputStreamReader(con.getInputStream()));
			String inputLine;
			response = new StringBuilder();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			if (responseCode != 200) {
				throw new Exception("Error invoking post: "
						+ response.toString());
			}
		} finally {
			if (wr != null) {
				try {
					wr.close();
				} catch (IOException e) {
					logger.warning(e.getMessage());
				}
			}
			if (in != null) {
				try {
					in.close();
				} catch (IOException e) {
					logger.warning(e.getMessage());
				}
			}
		}
		// print result
		logger.info("Response: " + response.toString());		
	}
}
