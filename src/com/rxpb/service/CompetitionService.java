package com.rxpb.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.rxpb.dao.CompetitionDao;
import com.rxpb.entity.RxpbCompanyInfo;
import com.rxpb.entity.RxpbCompetitionConfig;
import com.rxpb.entity.RxpbCompetitionInfo;
import com.rxpb.service.i.CompetitionServiceInterface;

public class CompetitionService implements CompetitionServiceInterface {

	CompetitionDao competitionDao;
	
	public void setCompetitionDao(CompetitionDao competitionDao) {
		this.competitionDao = competitionDao;
	}


	@Override
	public RxpbCompetitionInfo getCompetitionById(RxpbCompetitionInfo competition) {
		return competitionDao.getHibernateTemplate().get(RxpbCompetitionInfo.class, competition.getCompetitionId());
	}


	@Override
	public List<RxpbCompetitionInfo> getCompetitonList(RxpbCompetitionInfo competition) {
		List<RxpbCompetitionInfo> list = competitionDao.searchAll(competition);
		
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}


	@Override
	public boolean editCompetition(RxpbCompetitionInfo competition) {
		RxpbCompetitionInfo cinfo = competitionDao.getHibernateTemplate().get(RxpbCompetitionInfo.class, competition.getCompetitionId());
		
		cinfo.setCompetitionYear(competition.getCompetitionYear());
				
		cinfo.setResultFatness(competition.getResultFatness());
		cinfo.setResultQuality(competition.getResultQuality());
		cinfo.setResultTaste(competition.getResultTaste());
		cinfo.setVarFatnessF(competition.getVarFatnessF());
		cinfo.setVarFatnessM(competition.getVarFatnessM());
		cinfo.setVarFfatnessSd(competition.getVarFfatnessSd());
		cinfo.setVarFweightSd(competition.getVarFweightSd());
		cinfo.setVarMfatnessSd(competition.getVarMfatnessSd());
		cinfo.setVarMweightSd(competition.getVarMweightSd());
		cinfo.setVarWeightF(competition.getVarWeightF());
		cinfo.setVarWeightM(competition.getVarWeightM());
		cinfo.setNote(competition.getNote());
		
		cinfo.setUpdateDate(new Timestamp(new Date().getTime()));
		cinfo.setUpdateUser(ActionContext.getContext().getSession().get("loginUserName").toString());
		
		return competitionDao.update(cinfo);
	}


	@Override
	public boolean createCompetition(RxpbCompetitionInfo competition) {
		competition.setCreateDate(new Timestamp(new Date().getTime()));
		competition.setCreateUser(ActionContext.getContext().getSession().get("loginUserName").toString());
		competition.setStatus(1);
		
		return competitionDao.insert(competition);
	}


	@Override
	public boolean deleteCompetition(RxpbCompetitionInfo competition) {
		RxpbCompetitionInfo cinfo = competitionDao.getHibernateTemplate().get(RxpbCompetitionInfo.class, competition.getCompetitionId());
		
		cinfo.setStatus(0);
		return competitionDao.update(cinfo);
	}


	@Override
	public boolean editConfig(RxpbCompetitionConfig competitionConfig) {
		RxpbCompetitionConfig cinfo = competitionDao.getHibernateTemplate().get(RxpbCompetitionConfig.class, 1);
		cinfo.setCompetitionId(competitionConfig.getCompetitionId());
		cinfo.setUpdateDate(new Timestamp(new Date().getTime()));
		cinfo.setUpdateUser(ActionContext.getContext().getSession().get("loginUserName").toString());
		
		return competitionDao.updateConfig(cinfo);
	}

	
	@Override
	public List<RxpbCompetitionInfo> getCompetitonConfig(RxpbCompetitionInfo competition) {
		List<RxpbCompetitionInfo> list = competitionDao.search(competition);
		
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}
	

}
