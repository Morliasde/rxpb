package com.rxpb.dao.i;

import java.util.List;

import com.rxpb.entity.RxpbCompetitionConfig;
import com.rxpb.entity.RxpbCompetitionInfo;

public interface CompetitionDaoInterface {
	public List search(final RxpbCompetitionInfo competition);//查
	public List searchAll(final RxpbCompetitionInfo competition);
	public boolean update(RxpbCompetitionInfo competition);//改
	public boolean insert(RxpbCompetitionInfo competition);//增
	public boolean delete(RxpbCompetitionInfo competition);//删
	
	public boolean updateConfig(RxpbCompetitionConfig competitionConfig);
}
