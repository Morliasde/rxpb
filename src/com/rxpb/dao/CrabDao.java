package com.rxpb.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;


import com.rxpb.dao.i.CrabDaoInterface;
import com.rxpb.entity.RxpbCrabInfo;
import com.rxpb.entity.RxpbCompetitionConfig;

public class CrabDao extends HibernateDaoSupport implements CrabDaoInterface {

	@Override
	public List search(final RxpbCrabInfo crab) {		
		
		return super.getHibernateTemplate().executeFind(
				  new HibernateCallback(){
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria=session.createCriteria(RxpbCrabInfo.class);
						if(crab!=null){
							if(crab.getCrabId()!=null && !crab.getCrabId().equals("")){
								criteria.add(Restrictions.eq("crabId", crab.getCrabId()));
							}
							
							if(crab.getCrabSex()!=null && !crab.getCrabSex().equals("")){
								criteria.add(Restrictions.eq("crabSex", crab.getCrabSex()));
							}
							
							if(crab.getGroupId()!=null && !crab.getGroupId().equals("")){
								criteria.add(Restrictions.eq("groupId", crab.getGroupId()));
							}
							
							if(crab.getCrabLabel()!=null && !crab.getCrabLabel().equals("")){
								criteria.add(Restrictions.eq("crabLabel", crab.getCrabLabel()));
							}
													
							//配置ID，默认当前
							if(crab.getCompetitionId()!=null && !crab.getCompetitionId().equals("")){
								criteria.add(Restrictions.eq("competitionId", crab.getCompetitionId()));
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
	public List<RxpbCrabInfo> searchByCrabId(final int crabId){
		return super.getHibernateTemplate().executeFind(
				  new HibernateCallback(){
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria=session.createCriteria(RxpbCrabInfo.class);
						if(crabId!=0){
								criteria.add(Restrictions.eq("crabId", crabId));														
						}else{
							return null;
						}
						return criteria.list();
					}
				  });
	}
	
	@Override
	public boolean update(RxpbCrabInfo crab) {	
		try{
			super.getHibernateTemplate().update(crab);			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
		
	}

	@Override
	public boolean insert(RxpbCrabInfo crab) {
		try{
			super.getHibernateTemplate().save(crab);			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}
	
	@Override
	public boolean delete(RxpbCrabInfo crab) {
		try{
			super.getHibernateTemplate().delete(crab);			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}
	
	@Override
	public List searchTop10(final RxpbCrabInfo crab) {		
		
		return super.getHibernateTemplate().executeFind(
				  new HibernateCallback(){
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria=session.createCriteria(RxpbCrabInfo.class);
						if(crab!=null){
													
							if(crab.getCrabSex()!=null && !crab.getCrabSex().equals("")){
								criteria.add(Restrictions.eq("crabSex", crab.getCrabSex()));
							}
							
							if(crab.getGroupId()!=null && !crab.getGroupId().equals("")){
								criteria.add(Restrictions.eq("groupId", crab.getGroupId()));
							}
													
							//配置ID，默认当前
							if(crab.getCompetitionId()!=null && !crab.getCompetitionId().equals("")){
								criteria.add(Restrictions.eq("competitionId", crab.getCompetitionId()));
							}else{
								criteria.add(Restrictions.or(
										Restrictions.eq("competitionId",getHibernateTemplate().get(RxpbCompetitionConfig.class,1).getCompetitionId()),
										Restrictions.eq("competitionId",0))
										);
							}
							
						}
						criteria.addOrder(Order.desc("crabFatness"));
						criteria.setMaxResults(10);

						return criteria.list();
					}
				  });
	}
	
	
}
