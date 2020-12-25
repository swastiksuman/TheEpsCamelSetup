package com.swastik.app.service;

import com.swastik.app.dto.searchuser.Address;
import com.swastik.app.dto.searchuser.UserInfo;

public class SearchUserService {
	
	public UserInfo searchUserByName() {
		UserInfo userInfo = new UserInfo();
		userInfo.setId((byte) 1);
		userInfo.setName("John Doe");
		Address address = new Address();
		address.setCity("Puri");
		address.setState("OR");
		address.setStreet("Bali Sahi, Barahi Lane");
		userInfo.setAddress(address);
		return userInfo;
	}

}
