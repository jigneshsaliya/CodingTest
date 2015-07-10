package com.cpns.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.cpns.exception.CustomException;
import com.cpns.notification.AndroidNotification;
import com.cpns.notification.NotificationBase;
import com.cpns.sender.GCMSSender;
import com.cpns.sender.SenderBase;

@WebServlet("/sendNotification")
public class SendNotificationController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
    
    public SendNotificationController() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("index.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		
		if(request.getParameter("platform") == null || request.getParameter("platform").equals("0") ||
				request.getParameter("title") == null || request.getParameter("title").isEmpty() ||
				request.getParameter("message") == null || request.getParameter("message").isEmpty() || 
				request.getParameter("deviceToken") == null || request.getParameter("deviceToken").isEmpty())
		{
			String errorMsg = "<font color='RED'>Please do not leave any field empty.</font>";
			request.setAttribute("errorMsg", errorMsg);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		else
		{
			String platform = request.getParameter("platform");
			String title = request.getParameter("title");
			String message = request.getParameter("message");
			String deviceToken = request.getParameter("deviceToken");
			String responseMsg = "<font color='GREEN'>Success</font>";
			NotificationBase notification = null;
			SenderBase sender = null;
			
			if(platform.equals("android"))
			{
				notification = new AndroidNotification(title, message, deviceToken);
				sender = new GCMSSender();
				try 
				{
					sender.sendNotification(notification);
				}
				catch (CustomException e) {
					responseMsg = e.getMessage();
				}
				
			}
			else if(platform.equals("iOS"))
			{
				//call the APNSSender
			}
			else
			{
				//call the MPNSSender
			}
			request.setAttribute("responseMsg", responseMsg);
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		
	}

}
