package com.rxpb.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
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

import com.rxpb.dao.bean.Company;
import com.rxpb.dao.i.CompanyDaoInterface;
import com.rxpb.dao.oldClass.DbConnection;
import com.rxpb.entity.RxpbCompanyInfo;
import com.rxpb.entity.RxpbCompetitionConfig;

public class CompanyDao extends HibernateDaoSupport implements CompanyDaoInterface {

	@Override
	public List<RxpbCompanyInfo> search(final RxpbCompanyInfo company) {		
		
		return super.getHibernateTemplate().executeFind(
				  new HibernateCallback(){
					public Object doInHibernate(Session session) throws HibernateException, SQLException {
						Criteria criteria=session.createCriteria(RxpbCompanyInfo.class);
						if(company!=null){
							if(company.getCompanyId()!=null && !company.getCompanyId().equals("")){
								criteria.add(Restrictions.eq("companyId", company.getCompanyId()));
							}
							
							if(company.getCompanyName()!=null && !company.getCompanyName().equals("")){
								criteria.add(Restrictions.eq("companyName", company.getCompanyName()));
							}
													
							//配置ID，默认当前
							if(company.getCompetitionId()!=null && !company.getCompetitionId().equals("")){
								criteria.add(Restrictions.eq("competitionId", company.getCompetitionId()));
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
	public boolean update(RxpbCompanyInfo company) {	
		try{
			super.getHibernateTemplate().update(company);			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
		
	}

	@Override
	public boolean insert(RxpbCompanyInfo company) {
		
		try{
			super.getHibernateTemplate().save(company);			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}
	
	@Override
	public boolean delete(RxpbCompanyInfo company) {
		try{
			super.getHibernateTemplate().delete(company);			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}
	
	//获取参选单位列表--弃用
	public List getCompanyList(){
		Company company = null;
		List<Company> list = null;
		int competitionId = getHibernateTemplate().get(RxpbCompetitionConfig.class,1).getCompetitionId();
		
		Session session = getSession();
		String hql = "select c.companyId,c.companyName,g.groupId from RxpbCompanyInfo c left join RxpbGroupInfo g on c.companyId=g.companyId where c.competitionId =? order by g.groupId asc";
		
		Query query = session.createQuery(hql);
		list = query.list();
		return list;
//		
//		Connection conn = DbConnection.getConn();
//		String sql = "select c.company_id,company_name,g.group_id from rxpb_company_info c left join rxpb_group_info as g on c.company_id=g.company_id where c.competition_id =(select cg.competition_id from rxpb_competition_config as cg where id = 1) order by group_id asc";
//		
//		try {
//			PreparedStatement pre = conn.prepareStatement(sql);
//			
//			ResultSet rs = pre.executeQuery();
//			list = new ArrayList<Company>();
//
//			while (rs.next()) {
//				company = new Company();
//				company.setCompanyId(rs.getInt("company_id"));
//				company.setCompanyName(rs.getString("company_name"));
//				company.setGroupId(rs.getInt("group_id"));
//				list.add(company);
//			}
//		} catch (Exception e) {
//			e.printStackTrace();
//		} finally {
//			DbConnection.closeConn(conn);
//		}
//
//		return list;
	}
	

}
