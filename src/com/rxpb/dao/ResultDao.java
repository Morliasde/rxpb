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

import com.rxpb.dao.i.ResultDaoInterface;
import com.rxpb.entity.RxpbCompanyInfo;
import com.rxpb.entity.RxpbCompetitionConfig;
import com.rxpb.entity.RxpbCrabInfo;
import com.rxpb.entity.RxpbGroupInfo;
import com.rxpb.entity.RxpbScoreFin;
import com.rxpb.entity.RxpbScoreFinId;
import com.rxpb.entity.RxpbScoreQuality;
import com.rxpb.entity.RxpbScoreTaste;

public class ResultDao extends HibernateDaoSupport implements ResultDaoInterface {

	@Override
	public List getFatnessSort(final RxpbScoreFin scoreFin) {
		return super.getHibernateTemplate().executeFind(
				  new HibernateCallback(){
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria=session.createCriteria(RxpbScoreFin.class);
						if(scoreFin!=null){						
													
							//配置ID，默认当前
							if(scoreFin.getCompetitionId()!=null && !scoreFin.getCompetitionId().equals("")){
								criteria.add(Restrictions.eq("competitionId", scoreFin.getCompetitionId()));
							}else{
								criteria.add(Restrictions.or(
										Restrictions.eq("competitionId",getHibernateTemplate().get(RxpbCompetitionConfig.class,1).getCompetitionId()),
										Restrictions.eq("competitionId",0))
										);
							}
							
							//肥满度分降序
							criteria.addOrder(Order.desc("fatnessScore"));
							
						}
						return criteria.list();
					}
				  });
		
	}

	@Override
	public List getQualitySort(final RxpbScoreFin scoreFin) {
		return super.getHibernateTemplate().executeFind(
				  new HibernateCallback(){
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria=session.createCriteria(RxpbScoreFin.class);
						if(scoreFin!=null){						
													
							//配置ID，默认当前
							if(scoreFin.getCompetitionId()!=null && !scoreFin.getCompetitionId().equals("")){
								criteria.add(Restrictions.eq("competitionId", scoreFin.getCompetitionId()));
							}else{
								criteria.add(Restrictions.or(
										Restrictions.eq("competitionId",getHibernateTemplate().get(RxpbCompetitionConfig.class,1).getCompetitionId()),
										Restrictions.eq("competitionId",0))
										);
							}
							
							//降序
							criteria.addOrder(Order.desc("qualityScore"));							
						}
						return criteria.list();
					}
				  });
	}

	@Override
	public List getTasteSort(final RxpbScoreFin scoreFin) {
		return super.getHibernateTemplate().executeFind(
				  new HibernateCallback(){
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria=session.createCriteria(RxpbScoreFin.class);
						if(scoreFin!=null){						
													
							//配置ID，默认当前
							if(scoreFin.getCompetitionId()!=null && !scoreFin.getCompetitionId().equals("")){
								criteria.add(Restrictions.eq("competitionId", scoreFin.getCompetitionId()));
							}else{
								criteria.add(Restrictions.or(
										Restrictions.eq("competitionId",getHibernateTemplate().get(RxpbCompetitionConfig.class,1).getCompetitionId()),
										Restrictions.eq("competitionId",0))
										);
							}
							
							//肥满度分降序
							criteria.addOrder(Order.desc("tasteScore"));
							
						}
						return criteria.list();
					}
				  });
	}
	
	public List getGroupSort(final RxpbScoreFin scoreFin){
		return super.getHibernateTemplate().executeFind(
				  new HibernateCallback(){
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria=session.createCriteria(RxpbScoreFin.class);
						if(scoreFin!=null){						
													
							//配置ID，默认当前
							if(scoreFin.getCompetitionId()!=null && !scoreFin.getCompetitionId().equals("")){
								criteria.add(Restrictions.eq("competitionId", scoreFin.getCompetitionId()));
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

}
