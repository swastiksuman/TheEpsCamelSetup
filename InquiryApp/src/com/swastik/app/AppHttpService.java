package com.swastik.app;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.swastik.app.dto.searchuser.MyApp;
import com.swastik.app.processor.SearchUserProcessor;

/**
 * Servlet implementation class AppHttpService
 */
@WebServlet("/AppHttpService")
public class AppHttpService extends HttpServlet {
	private static final long serialVersionUID = 1L;

    /**
     * Default constructor. 
     */
    public AppHttpService() {
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("+++++++");
		response.setContentType("text/html");
		PrintWriter out = response.getWriter();
		out.print("<html><h1>Hello</h1></html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String responseXML = null;
		System.out.println(request);
		switch(request.getHeader("SERVICENAME")) {
		case "SEARCHUSER":
			responseXML = SearchUserProcessor.process(request.getInputStream());
			
		}
		
		System.out.println(responseXML);
		response.setContentType("application/xml");
		PrintWriter out = response.getWriter();
		out.print(responseXML);
	}

}
