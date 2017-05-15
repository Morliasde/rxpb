package com.rxpb.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.rxpb.dao.CrabDao;
import com.rxpb.dao.GroupDao;
import com.rxpb.dao.ScoreDao;
import com.rxpb.entity.RxpbCompetitionConfig;
import com.rxpb.entity.RxpbCrabInfo;
import com.rxpb.entity.RxpbGroupInfo;
import com.rxpb.entity.RxpbScoreQuality;
import com.rxpb.entity.RxpbScoreTaste;
import com.rxpb.service.i.GroupServiceInterface;
import com.rxpb.tool.MySearch;

public class GroupService implements GroupServiceInterface {

	GroupDao groupDao;
	CrabDao crabDao;
	ScoreDao scoreDao;
	
	
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}
	
	public void setCrabDao(CrabDao crabDao) {
		this.crabDao = crabDao;
	}	

	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}

	@Override
	public List<RxpbGroupInfo> getGroupList() {
		RxpbGroupInfo group = new RxpbGroupInfo();
		List list = groupDao.search(group);
		
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public List<RxpbCrabInfo> getGroupData(RxpbGroupInfo group) {
		
		RxpbCrabInfo crab = new RxpbCrabInfo();
		crab.setGroupId(group.getGroupId());
		
		List list = crabDao.search(crab);
		
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public List<RxpbScoreQuality> getGroupQuailty(RxpbGroupInfo group) {
		RxpbScoreQuality scoreQuality = new RxpbScoreQuality();
		scoreQuality.setGroupId(group.getGroupId());
		
		List list = scoreDao.searchScoreQuality(scoreQuality);
		
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public boolean saveGroupQuailty(List<RxpbScoreQuality> list) {
		Timestamp updateDate = new Timestamp(new Date().getTime());
		String updateUser = ActionContext.getContext().getSession().get("loginUserName").toString();
		
		
		Iterator<RxpbScoreQuality> it = list.iterator();
		RxpbScoreQuality scoreQuality;
		try{
			while (it.hasNext()){
				scoreQuality=it.next();
				scoreQuality.setUpdateDate(updateDate);
				scoreQuality.setUpdateUser(updateUser);
				
				return scoreDao.updateScoreQuality(scoreQuality);
			}			
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<RxpbScoreTaste> getGroupTaste(RxpbGroupInfo group) {
		RxpbScoreTaste scoreTaste = new RxpbScoreTaste();
		scoreTaste.setGroupId(group.getGroupId());
		
		List list = scoreDao.searchScoreTaste(scoreTaste);
		
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public boolean saveGroupTaste(List<RxpbScoreTaste> list) {
		Timestamp updateDate = new Timestamp(new Date().getTime());
		String updateUser = ActionContext.getContext().getSession().get("loginUserName").toString();
		
		Iterator<RxpbScoreTaste> it = list.iterator();
		RxpbScoreTaste scoreTaste;
		try{
			while (it.hasNext()){
				scoreTaste=it.next();
				scoreTaste.setUpdateDate(updateDate);
				scoreTaste.setUpdateUser(updateUser);
				return scoreDao.updateScoreTaste(scoreTaste);
			}			
		}catch (Exception e){
			e.printStackTrace();
			return false;
		}
		return true;
	}

	@Override
	public List<RxpbScoreQuality> getQualityByJudge(RxpbScoreQuality scoreQuality) {
		RxpbScoreQuality sq = new RxpbScoreQuality();
		sq.setUserId( Integer.parseInt(ActionContext.getContext().getSession().get("loginUserId").toString()));
		sq.setGroupId(scoreQuality.getGroupId());
		if(scoreQuality.getCrabSex()!=null && scoreQuality.getCrabSex()!=0){
			sq.setCrabSex(scoreQuality.getCrabSex());
		}
		
		List<RxpbScoreQuality> list = scoreDao.searchScoreQuality(sq);
		
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public boolean saveQualityByJudge(RxpbScoreQuality scoreQuality) {
		RxpbScoreQuality sinfo;// = new RxpbScoreQuality();
		
		List<RxpbScoreQuality> list = this.getQualityByJudge(scoreQuality);		
		
		if(list!=null){
			sinfo = list.get(0);
			
			sinfo.setScoreBbyzt(scoreQuality.getScoreBbyzt());
			sinfo.setScoreBts(scoreQuality.getScoreBts());
			sinfo.setScoreDscc(scoreQuality.getScoreDscc());
			sinfo.setScoreEc(scoreQuality.getScoreEc());
			sinfo.setScoreFts(scoreQuality.getScoreFts());
			sinfo.setScoreFin(scoreQuality.getScoreFin());
			
			sinfo.setUpdateDate(new Timestamp(new Date().getTime()));
			sinfo.setUpdateUser(ActionContext.getContext().getSession().get("loginUserName").toString());
			
			return scoreDao.updateScoreQuality(sinfo);
		}else{
			int competitionId = crabDao.getHibernateTemplate().get(RxpbCompetitionConfig.class,1).getCompetitionId();
			sinfo = new RxpbScoreQuality();
			
			sinfo.setScoreBbyzt(scoreQuality.getScoreBbyzt());
			sinfo.setScoreBts(scoreQuality.getScoreBts());
			sinfo.setScoreDscc(scoreQuality.getScoreDscc());
			sinfo.setScoreEc(scoreQuality.getScoreEc());
			sinfo.setScoreFts(scoreQuality.getScoreFts());
			sinfo.setScoreFin(scoreQuality.getScoreFin());
			
			sinfo.setCreateDate(new Timestamp(new Date().getTime()));
			sinfo.setCreateUser(ActionContext.getContext().getSession().get("loginUserName").toString());
			sinfo.setGroupId(scoreQuality.getGroupId());
			sinfo.setUserId(Integer.parseInt(ActionContext.getContext().getSession().get("loginUserId").toString()));
			sinfo.setCrabSex(scoreQuality.getCrabSex());
			sinfo.setCompetitionId(competitionId);
			
			return scoreDao.insertScoreQuality(sinfo);
		}
		
		
	}

	@Override
	public List<RxpbScoreTaste> getTasteByJudge(RxpbScoreTaste scoreTaste) {
		RxpbScoreTaste st = new RxpbScoreTaste();
		st.setUserId( Integer.parseInt(ActionContext.getContext().getSession().get("loginUserId").toString()));
		st.setGroupId(scoreTaste.getGroupId());
		if(scoreTaste.getCrabSex()!=null && scoreTaste.getCrabSex()!=0){
			st.setCrabSex(scoreTaste.getCrabSex());
		}
		
		List<RxpbScoreTaste> list = scoreDao.searchScoreTaste(st);
		
		if(list.size()>0){
			return list;
		}else{
			return null;
		}
	}

	@Override
	public boolean saveTasteByJudge(RxpbScoreTaste scoreTaste) {
		RxpbScoreTaste sinfo;// = new RxpbScoreTaste();
		
		List<RxpbScoreTaste> list = this.getTasteByJudge(scoreTaste);
		
		if(list!=null){
			sinfo = list.get(0);
			
			sinfo.setScoreBzjr(scoreTaste.getScoreBzjr());
			sinfo.setScoreFbjr(scoreTaste.getScoreFbjr());
			sinfo.setScoreGh(scoreTaste.getScoreGh());
			sinfo.setScoreGhys(scoreTaste.getScoreGhys());
			sinfo.setScoreSys(scoreTaste.getScoreSys());
			sinfo.setScoreXwxw(scoreTaste.getScoreXwxw());
			sinfo.setScoreYgys(scoreTaste.getScoreYgys());
			sinfo.setScoreFin(scoreTaste.getScoreFin());
		
			sinfo.setUpdateDate(new Timestamp(new Date().getTime()));
			sinfo.setUpdateUser(ActionContext.getContext().getSession().get("loginUserName").toString());
			
			return scoreDao.updateScoreTaste(sinfo);
		}else{
			int competitionId = crabDao.getHibernateTemplate().get(RxpbCompetitionConfig.class,1).getCompetitionId();
			sinfo = new RxpbScoreTaste();
			
			sinfo.setScoreBzjr(scoreTaste.getScoreBzjr());
			sinfo.setScoreFbjr(scoreTaste.getScoreFbjr());
			sinfo.setScoreGh(scoreTaste.getScoreGh());
			sinfo.setScoreGhys(scoreTaste.getScoreGhys());
			sinfo.setScoreSys(scoreTaste.getScoreSys());
			sinfo.setScoreXwxw(scoreTaste.getScoreXwxw());
			sinfo.setScoreYgys(scoreTaste.getScoreYgys());
			sinfo.setScoreFin(scoreTaste.getScoreFin());
			
			sinfo.setCreateDate(new Timestamp(new Date().getTime()));
			sinfo.setCreateUser(ActionContext.getContext().getSession().get("loginUserName").toString());
			sinfo.setGroupId(scoreTaste.getGroupId());
			sinfo.setUserId(Integer.parseInt(ActionContext.getContext().getSession().get("loginUserId").toString()));
			sinfo.setCrabSex(scoreTaste.getCrabSex());
			sinfo.setCompetitionId(competitionId);
			
			return scoreDao.insertScoreTaste(sinfo);
		}
	}

	@Override
	public int getGroupIdByLabel(String crabLabel) {
		int competitionId = crabDao.getHibernateTemplate().get(RxpbCompetitionConfig.class,1).getCompetitionId();
		RxpbCrabInfo crab = new RxpbCrabInfo();
		RxpbGroupInfo group = new RxpbGroupInfo();
		crab.setCompetitionId(competitionId);
		crab.setCrabLabel(crabLabel);
		
		List<RxpbGroupInfo> glist = groupDao.search(group);
		List <RxpbCrabInfo> list = crabDao.search(crab);
		
		if(list.size()>0){
			MySearch mySearch = new MySearch();
			return mySearch.findGroupQueue(glist, list.get(0).getGroupId());
		}else{
			return 0;
		}
	}

}
