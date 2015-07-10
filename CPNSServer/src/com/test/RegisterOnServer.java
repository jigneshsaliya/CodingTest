package com.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONObject;

public class RegisterOnServer {
	
	protected static String registerUrl = "http://localhost:8080/CPNSServer/register";
	protected static String appStateUrl = "http://localhost:8080/CPNSServer/xxxx";
	
	public static void main(String[] args) throws Exception {
		
		JSONObject deviceDetails = new JSONObject();
		deviceDetails.put("perm", true);
		deviceDetails.put("username","MojoJojo");
		JSONObject pIdentifier = new JSONObject();
		pIdentifier.put("pName", "android");
		pIdentifier.put("pVersion", "4.4.2");
		deviceDetails.put("pIdentifier", pIdentifier);
		deviceDetails.put("device_token", "xxxx");
		
		pushNotifyDeviceReg(deviceDetails.toString());
	}
	
	public static void pushNotifyDeviceReg(String deviceRegDetails){
		doPost(registerUrl, deviceRegDetails);
	}
	public static void pushNotifyAppState(String appStateDetails){
		doPost(appStateUrl, appStateDetails);
	}
	public static void doPost(String urlPath, String deviceDetails){
		try{
			URL url = new URL(urlPath);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json");
			conn.setRequestProperty("Keep-Alive", "timeout=15, max=100");
			conn.setDoOutput(true);

			OutputStream os = conn.getOutputStream();
			os.write(deviceDetails.getBytes("UTF-8"));
			os.flush();

			int responseCode = conn.getResponseCode();
			System.out.println("URL: " + url);
			System.out.println("Response Code: " + responseCode);

			
			BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			String inputLine;
			StringBuffer response = new StringBuffer();

			while ((inputLine = in.readLine()) != null) {
				response.append(inputLine);
			}
			in.close();
			
			System.out.println("Response: " + response.toString());
		}
		catch(MalformedURLException e){
			e.printStackTrace();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}

}

