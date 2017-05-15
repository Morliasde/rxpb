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
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.rxpb.dao.i.GroupDaoInterface;
import com.rxpb.dao.oldClass.HibernateSessionFactory;
import com.rxpb.entity.RxpbCompanyInfo;
import com.rxpb.entity.RxpbGroupInfo;
import com.rxpb.entity.RxpbCompetitionConfig;

public class GroupDao extends HibernateDaoSupport implements GroupDaoInterface {

	@Override
	public List<RxpbGroupInfo> search(final RxpbGroupInfo group) {		
		
		return super.getHibernateTemplate().executeFind(
				  new HibernateCallback(){
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria=session.createCriteria(RxpbGroupInfo.class);
						if(group!=null){
							if(group.getGroupId()!=null && !group.getGroupId().equals("")){
								criteria.add(Restrictions.eq("groupId", group.getGroupId()));
							}
							if(group.getCompanyId()!=null && !group.getCompanyId().equals("")){
								criteria.add(Restrictions.eq("companyId", group.getCompanyId()));
							}
													
							//配置ID，默认当前
							if(group.getCompetitionId()!=null && !group.getCompetitionId().equals("")){
								criteria.add(Restrictions.eq("competitionId", group.getCompetitionId()));
							}else{
								criteria.add(Restrictions.or(
										Restrictions.eq("competitionId",getHibernateTemplate().get(RxpbCompetitionConfig.class,1).getCompetitionId()),
										Restrictions.eq("competitionId",0))
										);
							}
							
							//ID升序
							criteria.addOrder(Order.asc("groupId"));
							
						}
						return criteria.list();
					}
				  });
	}
	
	@Override
	public boolean update(RxpbGroupInfo group) {	
		try{
			super.getHibernateTemplate().update(group);			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
		
	}

	@Override
	public boolean insert(RxpbGroupInfo group) {
		try{
			super.getHibernateTemplate().save(group);			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}
	
	@Override
	public boolean delete(RxpbGroupInfo group) {
		try{
			super.getHibernateTemplate().delete(group);			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}

}
