package com.rxpb.service.i;

import java.util.List;

import com.rxpb.entity.RxpbGroupInfo;
import com.rxpb.entity.RxpbScoreQuality;
import com.rxpb.entity.RxpbScoreTaste;
import com.rxpb.entity.RxpbCrabInfo;

public interface GroupServiceInterface {
	public List<RxpbGroupInfo> getGroupList();	//获取组列表
	public List<RxpbCrabInfo> getGroupData(RxpbGroupInfo group);	//	获取组内蟹数据
	public List<RxpbScoreQuality> getGroupQuailty(RxpbGroupInfo group);	//	获取组内种质分详情
	public boolean saveGroupQuailty(List<RxpbScoreQuality> list);	//	保存组种质分
	public List<RxpbScoreTaste> getGroupTaste(RxpbGroupInfo group);	//	获取组内口感分详情
	public boolean saveGroupTaste(List<RxpbScoreTaste> list);	//	保存组口感分
	public List<RxpbScoreQuality> getQualityByJudge(RxpbScoreQuality scoreQuality);	//	获取组内种质分详情（评委）
	public boolean saveQualityByJudge(RxpbScoreQuality scoreQuality);	//	保存组种质分（评委）
	public List<RxpbScoreTaste> getTasteByJudge(RxpbScoreTaste scoreTaste);	//	获取组内口感分详情（评委）
	public boolean saveTasteByJudge(RxpbScoreTaste scoreTaste);	//	保存组口感分（评委）

	public int getGroupIdByLabel(String crabLabel);//根据标签确定组号
	
}
