package com.rxpb.dao;

import java.sql.SQLException;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rxpb.dao.i.UserDaoInterface;
import com.rxpb.dao.oldClass.HibernateSessionFactory;
import com.rxpb.entity.RxpbCompetitionConfig;
import com.rxpb.entity.RxpbUserInfo;

public class UserDao extends HibernateDaoSupport implements UserDaoInterface {

	@Override
	public List search(final RxpbUserInfo user) {		
		
		return super.getHibernateTemplate().executeFind(
				  new HibernateCallback(){
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria=session.createCriteria(RxpbUserInfo.class);
						if(user!=null){
							//用户名
							if(user.getUserName()!=null && !user.getUserName().equals("")){
								criteria.add(Restrictions.eq("userName", user.getUserName()));
							}
							//密码
							if(user.getPassword()!=null && !user.getPassword().equals("")){
								criteria.add(Restrictions.eq("password", user.getPassword()));
							}
							//状态，默认查找有效用户
							if(user.getStatus()!=null && !user.getStatus().equals("")){
								criteria.add(Restrictions.eq("status", user.getStatus()));
							}else{
								criteria.add(Restrictions.eq("status", 1));
							}
							
							//配置ID，默认当前
							if(user.getCompetitionId()!=null && !user.getCompetitionId().equals("")){
								criteria.add(Restrictions.eq("competitionId", user.getCompetitionId()));
							}else{
								criteria.add(Restrictions.or(
										Restrictions.eq("competitionId",getHibernateTemplate().get(RxpbCompetitionConfig.class,1).getCompetitionId()),
										Restrictions.eq("competitionId",0))
										);
							}
							
						}
						return criteria.list();
					}
				  });
	}
	
	@Override
	public boolean update(RxpbUserInfo user) {	
		try{
			super.getHibernateTemplate().update(user);			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
		
	}

	@Override
	public boolean insert(RxpbUserInfo user) {
		try{
			super.getHibernateTemplate().save(user);			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}
	
	@Override	
public List getUserByUserName(final RxpbUserInfo user) {		
		
		return super.getHibernateTemplate().executeFind(
				  new HibernateCallback(){
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria=session.createCriteria(RxpbUserInfo.class);
						if(user!=null){
							//用户名
							if(user.getUserName()!=null && !user.getUserName().equals("")){
								criteria.add(Restrictions.eq("userName", user.getUserName()));
							}						
						}
						return criteria.list();
					}
				  });
	}
	
	

}
