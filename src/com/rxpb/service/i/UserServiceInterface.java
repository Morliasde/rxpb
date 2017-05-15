package com.rxpb.service.i;

import java.util.List;

import com.rxpb.entity.RxpbUserInfo;

public interface UserServiceInterface {
	public RxpbUserInfo userLogin (RxpbUserInfo user);//	用户登录
	public boolean userRegist (RxpbUserInfo user);//	用户注册
	
	public boolean changeUserCompetitionId(RxpbUserInfo user);//修改用户是否为长期有效
	public boolean changeUserStatus (RxpbUserInfo user);//	改变用户状态,1：可用 0：禁用 2:删除
	public boolean editUser (RxpbUserInfo user);//	编辑用户
	public boolean editUserSelf (RxpbUserInfo user);//	编辑个人资料
	public RxpbUserInfo getUserByUserName (RxpbUserInfo user);//	通过用户名获取用户信息
	public RxpbUserInfo getUserByUserId (RxpbUserInfo user);//	通过用户ID获取用户信息
	public List<RxpbUserInfo> getUserList ();//	获取用户列表
	public List<RxpbUserInfo> getDisableUserList ();//	获取待审核用户列表
}
