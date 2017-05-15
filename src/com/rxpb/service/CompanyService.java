package com.rxpb.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.rxpb.dao.CompanyDao;
import com.rxpb.dao.GroupDao;
import com.rxpb.dao.ResultDao;
import com.rxpb.dao.bean.Company;
import com.rxpb.entity.RxpbCompanyInfo;
import com.rxpb.entity.RxpbCompetitionConfig;
import com.rxpb.entity.RxpbGroupInfo;
import com.rxpb.entity.RxpbScoreFin;
import com.rxpb.service.i.CompanyServiceInterface;
import com.rxpb.tool.MySearch;

public class CompanyService implements CompanyServiceInterface {

	CompanyDao companyDao;
	GroupDao groupDao;
	ResultDao resultDao;
	
	
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public void setResultDao(ResultDao resultDao) {
		this.resultDao = resultDao;
	}

	@Override
	public List<Company> getCompanyList() {
		MySearch mySearch = new MySearch();
		int competitionId = groupDao.getHibernateTemplate().get(RxpbCompetitionConfig.class,1).getCompetitionId();
		RxpbGroupInfo group = new RxpbGroupInfo();
		RxpbCompanyInfo company = new RxpbCompanyInfo();
		Company cres = new Company();
		group.setCompetitionId(competitionId);
		company.setCompetitionId(competitionId);
		
		List<RxpbGroupInfo> groupList = groupDao.search(group);
		List<RxpbCompanyInfo> companyList = companyDao.search(company);
		Iterator<RxpbGroupInfo> groupIt = groupList.iterator();
		RxpbGroupInfo gtemp;
		
		List <Company> list = new ArrayList<Company>();
		while(groupIt.hasNext()){
			cres = new Company();
			gtemp=new RxpbGroupInfo();
			gtemp = groupIt.next();
			company = new RxpbCompanyInfo();
			company = mySearch.findCompanyByGroupId(groupList, companyList, gtemp.getGroupId());
			
			cres.setGroupId(gtemp.getGroupId());
			cres.setCompanyId(company.getCompanyId());
			cres.setCompanyName(company.getCompanyName());
			
			list.add(cres);
		}		
		
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public RxpbCompanyInfo getCompanyById(RxpbCompanyInfo company) {
		return companyDao.getHibernateTemplate().get(RxpbCompanyInfo.class,company.getCompanyId());
	}

	@Override
	public boolean insertCompany(RxpbCompanyInfo company) {
		Timestamp createDate = new Timestamp(new Date().getTime());
		String createUser = ActionContext.getContext().getSession().get("loginUserName").toString();	
		int competitionId = companyDao.getHibernateTemplate().get(RxpbCompetitionConfig.class,1).getCompetitionId();
		company.setCompetitionId(competitionId);		
		RxpbGroupInfo group = new RxpbGroupInfo();
		
		try{			
			company.setCreateDate(createDate);
			company.setCreateUser(createUser);
			companyDao.insert(company);
			
			group.setCompanyId(companyDao.search(company).get(0).getCompanyId());
			group.setCompetitionId(competitionId);
			groupDao.insert(group);
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public boolean editCompany(RxpbCompanyInfo company) {
		RxpbCompanyInfo cinfo = companyDao.getHibernateTemplate().get(RxpbCompanyInfo.class,company.getCompanyId());
		cinfo.setCompanyName(company.getCompanyName());		
		
		cinfo.setUpdateDate(new Timestamp(new Date().getTime()));
		cinfo.setUpdateUser(ActionContext.getContext().getSession().get("loginUserName").toString());
		
		return companyDao.update(cinfo);
	}

	@Override
	public boolean deleteCompany(RxpbCompanyInfo company) {
		RxpbCompanyInfo cinfo = companyDao.getHibernateTemplate().get(RxpbCompanyInfo.class,company.getCompanyId());
		RxpbGroupInfo ginfo = new RxpbGroupInfo();
		ginfo.setCompanyId(cinfo.getCompanyId());
		int groupId = 0;
		try{
			if(companyDao.delete(cinfo)){
				List <RxpbGroupInfo> glist = groupDao.search(ginfo);
				if(glist.size()>0){
					groupId = glist.get(0).getGroupId();
				}
				if(groupId!=0){
					ginfo = groupDao.getHibernateTemplate().get(RxpbGroupInfo.class,groupId);
					groupDao.delete(ginfo);
				}
			}			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		return true;
	}

}
