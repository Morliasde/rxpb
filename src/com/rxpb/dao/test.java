package com.rxpb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.rxpb.dao.i.UserDaoInterface;
import com.rxpb.entity.RxpbScoreFin;
import com.rxpb.entity.RxpbScoreFinId;
import com.rxpb.entity.RxpbUserInfo;
import com.rxpb.service.UserService;

public class test {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		
		ApplicationContext context = new ClassPathXmlApplicationContext("applicationContext.xml");
		
		ResultDao resultDao = (ResultDao) context.getBean("ResultDao");
		RxpbScoreFin scoreFin = new RxpbScoreFin();
		scoreFin.setCompetitionId(1);
		List<RxpbScoreFin> scoreFinList = resultDao.getFatnessSort(scoreFin);
		System.out.println(scoreFinList.size());
















//		
//		RxpbUserInfo user = new RxpbUserInfo();
//		
//		//添加测试
//		
//		user.setUserName("test1");
//		user.setPassword("21232F297A57A5A743894A0E4A801FC3");
//		user.setDisplayName("test1");
//		user.setRoleId(1);
//		user.setEmail("test@test");
//		user.setCompetitionId(1);
//		System.out.println(userService.userRegist(user));
//		
//		//修改测试
//		user.setUserName("admin");
//		user.setPassword("21232F297A57A5A743894A0E4A801FC3");
//		
//		//修改测试
//		user = userService.userLogin(user);
//		user.setPassword(null);
//		user.setEmail("admin@test2.com");
//		System.out.println(userService.editUser(user));
		
		//登录测试
		//System.out.println(userService.userLogin(user).getDisplayName());
		
		
//		UserDao ud = new UserDao();
//		RxpbUserInfo user = new RxpbUserInfo();
//		
//		user.setUserName("admin");
//		user.setPassword("21232F297A57A5A743894A0E4A801FC3");
//		
//		System.out.println(ud.search(user).size());
		
//		System.out.println(ud.checkPassword(user).getDisplayName());
		
//		List list=ud.search(user);
//		
//		System.out.println(list.size());
		
		
		
		
		
		
		// TODO Auto-generated method stub
//		Connection conn = DbConnection.getConn();
//		
//		String sql = "select user_i	d,user_name,display_name,role_id from rxpb_user_info";
//		
//		try {
//			PreparedStatement pre = conn.prepareStatement(sql);
//			ResultSet rs = pre.executeQuery();
//			rs.next();
//			System.out.println(rs.getString("display_name"));
//			
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DbConnection.closeConn(conn);
//		}
	}

}
