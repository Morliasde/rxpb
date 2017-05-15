package com.rxpb.dao.i;

import java.util.List;

import com.rxpb.entity.RxpbGroupInfo;

public interface GroupDaoInterface {
	public List search(final RxpbGroupInfo group);//查
	public boolean update(RxpbGroupInfo group);//改
	public boolean insert(RxpbGroupInfo group);//增
	public boolean delete(RxpbGroupInfo group);//删
}
