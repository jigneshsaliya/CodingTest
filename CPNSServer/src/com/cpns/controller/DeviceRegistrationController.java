package com.cpns.controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONException;
import org.json.JSONObject;

import com.util.StoreRegistrationDetails;

@WebServlet("/register")
public class DeviceRegistrationController extends HttpServlet {

	private static final long serialVersionUID = 1L;

	public DeviceRegistrationController() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Request method \'GET\' not supported");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int code = 0;
		String message = JSONObject.NULL.toString();
		
		String reader;
		StringBuilder requestData = new StringBuilder();
		String localFileStorage = null;
		
		while ((reader = request.getReader().readLine()) != null) {
			requestData.append(reader);
		}
		
		try 
		{
			JSONObject requestJson = new JSONObject(requestData.toString());
			if(!requestJson.has("device_token") || requestJson.isNull("device_token") || requestJson.get("device_token").toString().isEmpty())
			{
				code = 101;
				message = "Missing device token";
			}
			else if(!requestJson.has("username") || requestJson.isNull("username") || requestJson.get("username").toString().isEmpty())
			{
				code = 102;
				message = "Missing username";
			}
			else
			{
				localFileStorage = getServletContext().getRealPath("") + File.separator;
				StoreRegistrationDetails.storeRegDetails(requestJson, localFileStorage);
			}

			response.setContentType("application/json");
			
			PrintWriter out = response.getWriter();
			JSONObject responseJson = new JSONObject();
			
			responseJson.put("code", new Integer(code));
			responseJson.put("message", message);
			
			out.print(responseJson);
			out.flush();
		}
		catch (JSONException e) {
			e.printStackTrace();
		}
	}
}
