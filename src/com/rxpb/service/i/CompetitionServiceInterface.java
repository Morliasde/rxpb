package com.rxpb.service.i;

import java.util.List;

import com.rxpb.entity.RxpbCompetitionConfig;
import com.rxpb.entity.RxpbCompetitionInfo;

public interface CompetitionServiceInterface {
	public RxpbCompetitionInfo getCompetitionById(RxpbCompetitionInfo competition);//	获取当前比赛配置
	public List<RxpbCompetitionInfo> getCompetitonList(RxpbCompetitionInfo competition);//	获取配置列表
	public boolean editCompetition(RxpbCompetitionInfo competition);//	编辑比赛配置
	public boolean createCompetition(RxpbCompetitionInfo competition);//	新建比赛
	public boolean deleteCompetition(RxpbCompetitionInfo competition);//	删除比赛（慎用！！！！）
	
	public boolean editConfig(RxpbCompetitionConfig competitionConfig);//设置当前比赛配置
	public List<RxpbCompetitionInfo> getCompetitonConfig(RxpbCompetitionInfo competition);//获取当前配置
}
