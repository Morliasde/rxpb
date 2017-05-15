package com.rxpb.service.i;

import java.util.List;

import com.rxpb.entity.RxpbCrabInfo;

public interface CrabServiceInterface {
	public List<RxpbCrabInfo> getCrabByGroupAndSex(RxpbCrabInfo crab);//获取蟹列表
	public int getGroupIdByLabel(String label);
	public boolean addCrab(RxpbCrabInfo crab, int number);	//添加蟹数目
	public boolean saveCrabData(List<RxpbCrabInfo> list);	//保存蟹数据
	public boolean deleteCrab(RxpbCrabInfo crab);	//删除蟹数据

}
