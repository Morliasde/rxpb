package com.rxpb.dao;

import java.sql.SQLException;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rxpb.dao.i.CompetitionDaoInterface;
import com.rxpb.entity.RxpbCompanyInfo;
import com.rxpb.entity.RxpbCompetitionConfig;
import com.rxpb.entity.RxpbCompetitionInfo;

public class CompetitionDao extends HibernateDaoSupport implements CompetitionDaoInterface {

	@Override
	public List search(final RxpbCompetitionInfo competition) {
		return super.getHibernateTemplate().executeFind(
				  new HibernateCallback(){
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria=session.createCriteria(RxpbCompetitionInfo.class);
						if(competition!=null){												
							//配置ID，默认当前
							if(competition.getCompetitionId()!=null && !competition.getCompetitionId().equals("")){
								criteria.add(Restrictions.eq("competitionId", competition.getCompetitionId()));
							}else{
								criteria.add(Restrictions.or(
										Restrictions.eq("competitionId",getHibernateTemplate().get(RxpbCompetitionConfig.class,1).getCompetitionId()),
										Restrictions.eq("competitionId",0))
										);
							}
							//状态，默认查找有效
							if(competition.getStatus()!=null && !competition.getStatus().equals("")){
								criteria.add(Restrictions.eq("status", competition.getStatus()));
							}else{
								criteria.add(Restrictions.eq("status", 1));
							}
						}
						return criteria.list();
					}
				  });
	}

	@Override
	public boolean update(RxpbCompetitionInfo competition) {
		try{
			super.getHibernateTemplate().update(competition);			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}

	@Override
	public boolean insert(RxpbCompetitionInfo competition) {
		try{
			super.getHibernateTemplate().save(competition);			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}

	@Override
	public boolean delete(RxpbCompetitionInfo competition) {
		try{
			super.getHibernateTemplate().delete(competition);			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}

	@Override
	public boolean updateConfig(RxpbCompetitionConfig competitionConfig) {
		try{
			super.getHibernateTemplate().update(competitionConfig);			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}

	@Override
	public List searchAll(final RxpbCompetitionInfo competition) {
		return super.getHibernateTemplate().executeFind(
				  new HibernateCallback(){
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria=session.createCriteria(RxpbCompetitionInfo.class);
						if(competition!=null){
							//状态，默认查找有效
							if(competition.getStatus()!=null && !competition.getStatus().equals("")){
								criteria.add(Restrictions.eq("status", competition.getStatus()));
							}else{
								criteria.add(Restrictions.eq("status", 1));
							}
							
							
							//ID升序
							criteria.addOrder(Order.desc("competitionId"));
							
						}
						
						return criteria.list();
					}
				  });
	}
	
}
