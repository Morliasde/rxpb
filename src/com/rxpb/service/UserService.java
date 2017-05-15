package com.rxpb.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.rxpb.dao.UserDao;
import com.rxpb.entity.RxpbCompetitionConfig;
import com.rxpb.entity.RxpbUserInfo;
import com.rxpb.service.i.UserServiceInterface;
import com.rxpb.tool.MyEncoder;

public class UserService implements UserServiceInterface {
	
	UserDao userDao;	
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Override
	public RxpbUserInfo userLogin(RxpbUserInfo user) {
		List list = userDao.search(user);
		Iterator it = list.iterator();
		
		if(it.hasNext()){
			return (RxpbUserInfo)it.next();
		}else{
			return null;
		}
	}


	@Override
	public boolean userRegist(RxpbUserInfo user) {
		MyEncoder myEncoder = new MyEncoder();
		
		user.setPassword(myEncoder.getMd5(user.getPassword()));
		user.setCreateDate(new Timestamp(new Date().getTime()));
		user.setCreateUser("web");
		//注册初始态为禁用，需要管理员审核
		user.setStatus(0);
		//默认为当赛事建立用户
		user.setCompetitionId(userDao.getHibernateTemplate().get(RxpbCompetitionConfig.class,1).getCompetitionId());
		
		return userDao.insert(user);
	}


	@Override
	public boolean changeUserCompetitionId(RxpbUserInfo user) {
		RxpbUserInfo uinfo = userDao.getHibernateTemplate().get(RxpbUserInfo.class, user.getUserId());
		if(uinfo.getCompetitionId()!=0){
			uinfo.setCompetitionId(0);
		}else{
			uinfo.setCompetitionId(userDao.getHibernateTemplate().get(RxpbCompetitionConfig.class,1).getCompetitionId());
		}
		
		uinfo.setUpdateDate(new Timestamp(new Date().getTime()));
		uinfo.setUpdateUser(ActionContext.getContext().getSession().get("loginUserName").toString());
		
		return userDao.update(uinfo);
	}

	@Override
	public boolean changeUserStatus(RxpbUserInfo user) {
		RxpbUserInfo uinfo = userDao.getHibernateTemplate().get(RxpbUserInfo.class, user.getUserId());
		uinfo.setStatus(user.getStatus());
		
		uinfo.setUpdateDate(new Timestamp(new Date().getTime()));
		uinfo.setUpdateUser(ActionContext.getContext().getSession().get("loginUserName").toString());
		
		return userDao.update(uinfo);
	}

	@Override
	public boolean editUser(RxpbUserInfo user) {
		RxpbUserInfo uinfo = userDao.getHibernateTemplate().get(RxpbUserInfo.class, user.getUserId());
		
		uinfo.setDisplayName(user.getDisplayName());
		uinfo.setRoleId(user.getRoleId());
		uinfo.setEmail(user.getEmail());
		
		uinfo.setUpdateDate(new Timestamp(new Date().getTime()));
		uinfo.setUpdateUser(ActionContext.getContext().getSession().get("loginUserName").toString());
		
		return userDao.update(uinfo);
	}


	@Override
	public boolean editUserSelf(RxpbUserInfo user) {
		RxpbUserInfo uinfo = userDao.getHibernateTemplate().get(RxpbUserInfo.class, user.getUserId());
		
		uinfo.setDisplayName(user.getDisplayName());
		uinfo.setEmail(user.getEmail());
		if(user.getPassword()!=null&& !user.getPassword().equals("")){
			uinfo.setPassword(user.getPassword());
		}
		
		uinfo.setUpdateDate(new Timestamp(new Date().getTime()));
		uinfo.setUpdateUser(ActionContext.getContext().getSession().get("loginUserName").toString());
		
		
		return userDao.update(uinfo);
	}


	@Override
	public RxpbUserInfo getUserByUserName(RxpbUserInfo user) {
		List list = userDao.getUserByUserName(user);
		Iterator it = list.iterator();
		
		if(it.hasNext()){
			return (RxpbUserInfo)it.next();
		}else{
			return null;
		}
	}


	@Override
	public RxpbUserInfo getUserByUserId(RxpbUserInfo user) {
		return userDao.getHibernateTemplate().get(RxpbUserInfo.class, user.getUserId());
	}


	@Override
	public List<RxpbUserInfo> getUserList() {
		RxpbUserInfo user = new RxpbUserInfo();
		//获取所有有效用户
		user.setStatus(1);
		
		List list = userDao.search(user);
		
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public List<RxpbUserInfo> getDisableUserList() {
		RxpbUserInfo user = new RxpbUserInfo();
		//获取所有待审核用户
		user.setStatus(0);
		
		List list = userDao.search(user);
		
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	

	
	
}
