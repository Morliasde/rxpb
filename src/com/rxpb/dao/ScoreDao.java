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

import com.rxpb.dao.i.ScoreDaoInterface;
import com.rxpb.entity.RxpbCompanyInfo;
import com.rxpb.entity.RxpbCompetitionConfig;
import com.rxpb.entity.RxpbScoreQuality;
import com.rxpb.entity.RxpbScoreTaste;

public class ScoreDao extends HibernateDaoSupport implements ScoreDaoInterface {

	@Override
	public List searchScoreQuality(final RxpbScoreQuality scoreQuality) {
		return super.getHibernateTemplate().executeFind(
				  new HibernateCallback(){
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria=session.createCriteria(RxpbScoreQuality.class);
						if(scoreQuality!=null){
							if(scoreQuality.getGroupId()!=null && !scoreQuality.getGroupId().equals("")){
								criteria.add(Restrictions.eq("groupId", scoreQuality.getGroupId()));
							}
							
							if(scoreQuality.getUserId()!=null && !scoreQuality.getUserId().equals("")){
								criteria.add(Restrictions.eq("userId", scoreQuality.getUserId()));
							}
							
							if(scoreQuality.getCrabSex()!=null && !scoreQuality.getCrabSex().equals("")){
								criteria.add(Restrictions.eq("crabSex", scoreQuality.getCrabSex()));
							}
													
							//配置ID，默认当前
							if(scoreQuality.getCompetitionId()!=null && !scoreQuality.getCompetitionId().equals("")){
								criteria.add(Restrictions.eq("competitionId", scoreQuality.getCompetitionId()));
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
	public boolean updateScoreQuality(RxpbScoreQuality scoreQuality) {
		try{
			super.getHibernateTemplate().update(scoreQuality);			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}

	@Override
	public boolean insertScoreQuality(RxpbScoreQuality scoreQuality) {
		
		try{
			super.getHibernateTemplate().save(scoreQuality);			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}

	@Override
	public List searchScoreTaste(final RxpbScoreTaste scoreTaste) {
		return super.getHibernateTemplate().executeFind(
				  new HibernateCallback(){
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria=session.createCriteria(RxpbScoreTaste.class);
						if(scoreTaste!=null){
							if(scoreTaste.getGroupId()!=null && !scoreTaste.getGroupId().equals("")){
								criteria.add(Restrictions.eq("groupId", scoreTaste.getGroupId()));
							}
							
							if(scoreTaste.getUserId()!=null && !scoreTaste.getUserId().equals("")){
								criteria.add(Restrictions.eq("userId", scoreTaste.getUserId()));
							}
							
							if(scoreTaste.getCrabSex()!=null && !scoreTaste.getCrabSex().equals("")){
								criteria.add(Restrictions.eq("crabSex", scoreTaste.getCrabSex()));
							}
													
							//配置ID，默认当前
							if(scoreTaste.getCompetitionId()!=null && !scoreTaste.getCompetitionId().equals("")){
								criteria.add(Restrictions.eq("competitionId", scoreTaste.getCompetitionId()));
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
	public boolean updateScoreTaste(RxpbScoreTaste scoreTaste) {
		try{
			super.getHibernateTemplate().update(scoreTaste);			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}

	@Override
	public boolean insertScoreTaste(RxpbScoreTaste scoreTaste) {
		try{
			super.getHibernateTemplate().save(scoreTaste);			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}

}
