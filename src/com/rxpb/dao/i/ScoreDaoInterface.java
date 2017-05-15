package com.rxpb.dao.i;

import java.util.List;

import com.rxpb.entity.RxpbCompanyInfo;
import com.rxpb.entity.RxpbGroupInfo;
import com.rxpb.entity.RxpbScoreQuality;
import com.rxpb.entity.RxpbScoreTaste;

public interface ScoreDaoInterface {
	public List searchScoreQuality(final RxpbScoreQuality scoreQuality);//查
	public boolean updateScoreQuality(RxpbScoreQuality scoreQuality);//改
	public boolean insertScoreQuality(RxpbScoreQuality scoreQuality);//增
	
	public List searchScoreTaste(final RxpbScoreTaste scoreTaste);//查
	public boolean updateScoreTaste(RxpbScoreTaste scoreTaste);//改
	public boolean insertScoreTaste(RxpbScoreTaste scoreTaste);//增
	
	
}
