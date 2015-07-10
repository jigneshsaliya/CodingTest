package com.cpns.sender;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.MalformedURLException;
import java.net.Proxy;
import java.net.URL;

import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.cpns.exception.CustomException;
import com.cpns.notification.AndroidNotification;
import com.cpns.notification.NotificationBase;

public class GCMSSender extends SenderBase{

	private final String apiKey = "AIzaSyDw-reriSDy2TfB6yYv1TKRM8HbkHxx2OE";

	public void sendNotification(NotificationBase notification) throws CustomException{
		if (notification != null)
		{
			if (notification instanceof AndroidNotification)
			{
				try{
					URL url = new URL("https://android.googleapis.com/gcm/send");
					//Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.tcs.com", 8080));
					HttpURLConnection conn = (HttpURLConnection) url.openConnection();
					conn.setRequestMethod("POST");
					conn.setRequestProperty("Content-Type", "application/json");
					conn.setRequestProperty("Authorization", "key="+apiKey);
					conn.setDoOutput(true);

					DataOutputStream dos = new DataOutputStream(conn.getOutputStream());
					dos.writeBytes(((AndroidNotification) notification).toJSON());
					dos.flush();
					dos.close();

					int responseCode = conn.getResponseCode();
					
					if(responseCode == HttpServletResponse.SC_OK)
					{
						BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
						String inputLine;
						StringBuffer GCMResponse = new StringBuffer();

						while ((inputLine = in.readLine()) != null) {
							GCMResponse.append(inputLine);
						}
						in.close();
						
						try 
						{
							JSONObject GCMResponseJSON = new JSONObject(GCMResponse.toString());
							//if(GCMResponseJSON.getInt("failure") == 1)
								throw new CustomException(GCMResponse.toString());
						}
						catch (JSONException e) {
							e.printStackTrace();
						}
					}
					else if(responseCode >= 500)
						throw new CustomException("There was an internal error in the GCM server while trying to process the request, or that the server is temporarily unavailable.");
					else if(responseCode == HttpServletResponse.SC_UNAUTHORIZED)
						throw new CustomException("There was an error authenticating the sender account.");
					else if(responseCode == HttpServletResponse.SC_BAD_REQUEST)
						throw new CustomException("Request could not be parsed as JSON, or it contained invalid fields.");
					else{
						throw new CustomException("Exception occurred with response code: " + responseCode);
					}
				}
				catch(MalformedURLException e){
					e.printStackTrace();
				}
				catch(IOException e){
					e.printStackTrace();
				}
			}
			else
			{
				throw new CustomException("Notification object must be of type AndroidNotification");
			}
		}
		else
		{
			throw new CustomException("Notification cannot be null");
		}

	}

}
