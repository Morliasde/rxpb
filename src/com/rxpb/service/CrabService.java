package com.rxpb.service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.rxpb.dao.CrabDao;
import com.rxpb.entity.RxpbCompanyInfo;
import com.rxpb.entity.RxpbCompetitionConfig;
import com.rxpb.entity.RxpbCompetitionInfo;
import com.rxpb.entity.RxpbCrabInfo;
import com.rxpb.service.i.CrabServiceInterface;
import com.rxpb.tool.MyMath;

public class CrabService implements CrabServiceInterface {

	CrabDao crabDao;
		
	public void setCrabDao(CrabDao crabDao) {
		this.crabDao = crabDao;
	}
	
	@Override
	public List<RxpbCrabInfo> getCrabByGroupAndSex(RxpbCrabInfo crab) {
		if(crab!=null&&crab.getGroupId()!=0&&crab.getCrabSex()!=0){
			List<RxpbCrabInfo> list = crabDao.search(crab);
			return list;
		}
		return null;
	}

	@Override
	public int getGroupIdByLabel(String label) {
		RxpbCrabInfo crab = new RxpbCrabInfo();
		int competitionId = crabDao.getHibernateTemplate().get(RxpbCompetitionConfig.class,1).getCompetitionId();
		
		crab.setCompetitionId(competitionId);
		crab.setCrabLabel(label);
		
		List<RxpbCrabInfo> list = crabDao.search(crab);
		
		if(list.size()>0){
			return list.get(0).getGroupId();
		}
		
		return 0;
	}

	@Override
	public boolean addCrab(RxpbCrabInfo crab, int number) {
		//雌雄交给action处理
		RxpbCrabInfo cinfo = new RxpbCrabInfo();	
		
		try{
			Timestamp date = new Timestamp(new Date().getTime());
			String user = ActionContext.getContext().getSession().get("loginUserName").toString();
			int competitionId = crabDao.getHibernateTemplate().get(RxpbCompetitionConfig.class,1).getCompetitionId();
			
			for(int i=0 ;i<number;i++){		
				cinfo.setCrabSex(crab.getCrabSex());
				cinfo.setGroupId(crab.getGroupId());
				cinfo.setCrabLabel("");
				cinfo.setCompetitionId(competitionId);
				cinfo.setCreateDate(date);
				cinfo.setCreateUser(user);
				
				crabDao.insert(cinfo);
			}
			
			//TODO 测试是否需要关闭
			//crabDao.getSessionFactory().close();
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;
		
	}

	@Override
	public boolean saveCrabData(List<RxpbCrabInfo> list) {
		Timestamp updateDate = new Timestamp(new Date().getTime());
		String updateUser = ActionContext.getContext().getSession().get("loginUserName").toString();
		
		//取出肥满度参数计算肥满度
		int competitionId = crabDao.getHibernateTemplate().get(RxpbCompetitionConfig.class,1).getCompetitionId();
		RxpbCompetitionInfo competition = crabDao.getHibernateTemplate().get(RxpbCompetitionInfo.class,competitionId);
		Float varFatnessM = competition.getVarFatnessM();
		Float varFatnessF = competition.getVarFatnessF();
		//计算函数
		MyMath math = new MyMath();
		
		Iterator<RxpbCrabInfo> it = list.iterator();
		
		RxpbCrabInfo crab;
		RxpbCrabInfo cinfo;
		
		try{			
			while(it.hasNext()){
				crab = it.next();
				cinfo = new RxpbCrabInfo();
				cinfo = crabDao.searchByCrabId(crab.getCrabId()).get(0);
				cinfo.setUpdateDate(updateDate);
				cinfo.setUpdateUser(updateUser);
				cinfo.setCrabLabel(crab.getCrabLabel());
				cinfo.setCrabLength(crab.getCrabLength());
				cinfo.setCrabWeight(crab.getCrabWeight());
				if(cinfo.getCrabSex()==1){
					cinfo.setCrabFatness(math.getFatness(crab, varFatnessM));
				}else if (cinfo.getCrabSex()==2){
					cinfo.setCrabFatness(math.getFatness(crab, varFatnessF));
				}
				crabDao.update(cinfo);
			}			
			
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		
		return true;		
	}

	@Override
	public boolean deleteCrab(RxpbCrabInfo crab) {
		RxpbCrabInfo cinfo = crabDao.getHibernateTemplate().get(RxpbCrabInfo.class,crab.getCrabId());
		return crabDao.delete(cinfo);
	}

	

}
