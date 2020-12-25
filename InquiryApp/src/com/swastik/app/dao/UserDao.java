package com.swastik.app.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.swastik.app.vo.UserInfoVO;

public class UserDao extends BaseDao {

	public List<UserInfoVO> getUserByName() {
		List<UserInfoVO> userInfos = new ArrayList();
		try {
			Connection connection = getDataBaseConnection();
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("Select * from User");
			while (rs.next()) {
				UserInfoVO user = new UserInfoVO();

				userInfos.add(user);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return userInfos;
	}
}
