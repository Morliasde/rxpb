package com.rxpb.service.i;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.rxpb.entity.RxpbCompanyInfo;
import com.rxpb.entity.RxpbCrabInfo;
import com.rxpb.entity.RxpbGroupInfo;
import com.rxpb.entity.RxpbScoreFin;
import com.rxpb.entity.RxpbScoreQuality;
import com.rxpb.entity.RxpbScoreTaste;

public interface ResultServiceInterface {
	public boolean createScore();//	生成成绩
	public HSSFWorkbook createExcel(int competitionId);//	生成表格,0为当前
	//public HSSFWorkbook createPreviousExcel();//	生成往届成绩表格
		
	public List<RxpbScoreFin> getFatnessSort();//	查看肥满度排名
	public List<RxpbScoreFin> getQualitySort();//	查看种质排名
	public List<RxpbScoreFin> getTasteSort();//	查看口感排名
	public List<RxpbCrabInfo> getKingSort();//	查看蟹王排名
	public List<RxpbCrabInfo> getQueenSort();//	查看蟹后排名
		
	public List<RxpbCrabInfo> getFatnessScoreByCompany(RxpbCompanyInfo company,int groupId,int crabSex);//	获取本单位肥满度详情
	public List<RxpbScoreQuality> getQualityScoreByCompany(RxpbCompanyInfo company,int groupId,int crabSex);//	获取本单位种质分详情
	public List<RxpbScoreTaste> getTasteScoreByCompany(RxpbCompanyInfo company,int groupId,int crabSex);//	获取本单位口感分详情

}
