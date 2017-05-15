package com.rxpb.dao.i;

import java.util.List;

import com.rxpb.entity.RxpbCrabInfo;

public interface CrabDaoInterface {
	public List search(final RxpbCrabInfo crab);//查
	public boolean update(RxpbCrabInfo crab);//改
	public boolean insert(RxpbCrabInfo crab);//增
	public boolean delete(RxpbCrabInfo crab);//删
	
	public List<RxpbCrabInfo> searchByCrabId(final int crabId);
	public List searchTop10(final RxpbCrabInfo crab);

}
