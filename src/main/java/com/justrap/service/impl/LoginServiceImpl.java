package com.justrap.service.impl;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.justrap.model.UserInterface;
import com.justrap.service.LoginService;
import com.justrap.service.UserService;

@Service
public class LoginServiceImpl implements LoginService {
	
    @Autowired
    private UserService userService;
    private static final String BASE_URL = "https://www.googleapis.com/identitytoolkit/v3/relyingparty/";
    private static final String OPERATION_AUTH = "verifyPassword";
    private static final String OPERATION_ACCOUNT_INFO = "getAccountInfo";
    private static final String FIREBASE_KEY = "AIzaSyCEpXZnBYeOF7SMqHaXsddSTlmMmyW7UDQ";
    
	@Override
	public UserInterface getUserByCredentials(String mail, String password) throws Exception {
		String token = auth(mail, password);
		if(token == null)
			return null;
		return userService.getUserById(getUserId(token));
	}

    private String auth(String mail, String password) throws Exception { 

        HttpURLConnection urlRequest = null;
        String token = null;

        try {
            URL url = new URL(BASE_URL + OPERATION_AUTH + "?key=" + FIREBASE_KEY);
            urlRequest = (HttpURLConnection) url.openConnection();
            urlRequest.setDoOutput(true);
            urlRequest.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStream os = urlRequest.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            
            osw.write("{\"email\":\"" + mail + "\",\"password\":\"" + password + "\",\"returnSecureToken\":true}");
            osw.flush();
            osw.close();

            urlRequest.connect();

            JsonParser jp = new JsonParser(); //from gson
            JsonElement root = jp.parse(new InputStreamReader((InputStream) urlRequest.getContent())); //Convert the input stream to a json element
            JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 

            token = rootobj.get("idToken").getAsString();

        } catch (Exception e) {
        	System.err.println(e.getMessage());
            return null;

        } finally {
            urlRequest.disconnect();
        }

        return token;
    }

    private String getUserId(String token) throws Exception {

        HttpURLConnection urlRequest = null;
        String id = null;

        try {
            URL url = new URL(BASE_URL + OPERATION_ACCOUNT_INFO + "?key=" + FIREBASE_KEY);
            urlRequest = (HttpURLConnection) url.openConnection();
            urlRequest.setDoOutput(true);
            urlRequest.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
            OutputStream os = urlRequest.getOutputStream();
            OutputStreamWriter osw = new OutputStreamWriter(os, "UTF-8");
            osw.write("{\"idToken\":\"" + token + "\"}");
            osw.flush();
            osw.close();
            urlRequest.connect();

            JsonParser jp = new JsonParser(); //from gson
            JsonElement root = jp.parse(new InputStreamReader((InputStream) urlRequest.getContent())); //Convert the input stream to a json element
            JsonObject rootobj = root.getAsJsonObject(); //May be an array, may be an object. 

            id = rootobj.get("users").getAsJsonArray().get(0).getAsJsonObject().get("localId").getAsString();

        } catch (Exception e) {
            return null;
        } finally {
            urlRequest.disconnect();
        }

        return id;
    }

}
