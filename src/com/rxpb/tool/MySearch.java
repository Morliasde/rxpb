package com.rxpb.tool;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.rxpb.entity.RxpbCompanyInfo;
import com.rxpb.entity.RxpbGroupInfo;
import com.rxpb.entity.RxpbScoreFin;

public class MySearch {
	
	public RxpbCompanyInfo getCompanyById(List<RxpbCompanyInfo> list,int companyId){
		RxpbCompanyInfo company;
		Iterator<RxpbCompanyInfo> it = list.iterator();
		while(it.hasNext()){
			company = it.next();
			if(company.getCompanyId()==companyId){
				return company;
			}
		}
		company = new RxpbCompanyInfo();
		return company;
	}
	
	public RxpbGroupInfo getGroupById(List<RxpbGroupInfo> list,int groupId){
		RxpbGroupInfo group;
		Iterator<RxpbGroupInfo> it = list.iterator();
		while(it.hasNext()){
			group = it.next();
			if(group.getGroupId()==groupId){
				return group;
			}
		}
		group = new RxpbGroupInfo();
		return group;
	}
	
	public int findGroupQueue(List<RxpbGroupInfo> list,int groupId){
		Iterator<RxpbGroupInfo> it = list.iterator();
		int gqueue = 0;
		while(it.hasNext()){
			gqueue++;
			if(it.next().getGroupId()==groupId){
				return gqueue;
			}
		}
		
		return 0;
	}
	
	public RxpbCompanyInfo findCompanyByGroupId(List<RxpbGroupInfo> groupList,List<RxpbCompanyInfo> companyList,int groupId){
		return this.getCompanyById(companyList, this.getGroupById(groupList, groupId).getCompanyId());
	}
	
}
