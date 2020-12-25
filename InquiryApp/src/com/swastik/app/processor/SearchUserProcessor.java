package com.swastik.app.processor;

import java.io.StringWriter;

import javax.servlet.ServletInputStream;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import javax.xml.transform.Result;

import com.swastik.app.dto.searchuser.MyApp;
import com.swastik.app.dto.searchuser.Response;
import com.swastik.app.dto.searchuser.UserInfo;
import com.swastik.app.service.SearchUserService;

public class SearchUserProcessor {
	
	private static SearchUserService searchUserService = new SearchUserService();
	
	public static String process(ServletInputStream servletInputStream) {
		try {
			JAXBContext jContext = JAXBContext.newInstance(MyApp.class);
			Unmarshaller unmarshallerObj = jContext.createUnmarshaller();
			MyApp myApp = (MyApp) unmarshallerObj.unmarshal(servletInputStream);
			UserInfo userInfo = new UserInfo();
			userInfo = searchUserService.searchUserByName();
			Response response = new Response();
			response.setUserInfo(userInfo);
			myApp.setResponse(response);
			Marshaller marshallerObj = jContext.createMarshaller();
			marshallerObj.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
			StringWriter sw = new StringWriter();
			marshallerObj.marshal(myApp, sw);
			System.out.println("OUT: "+sw);
			return sw.toString();
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
}