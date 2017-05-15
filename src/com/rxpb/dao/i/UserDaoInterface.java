package com.rxpb.dao.i;

import java.util.List;

import com.rxpb.entity.RxpbUserInfo;

public interface UserDaoInterface {
	public List search(final RxpbUserInfo user);//查
	public boolean update(RxpbUserInfo user);//改
	public boolean insert(RxpbUserInfo user);//增
	
	public List getUserByUserName(final RxpbUserInfo user);

}
