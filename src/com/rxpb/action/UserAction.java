package com.rxpb.action;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.rxpb.entity.RxpbUserInfo;
import com.rxpb.service.UserService;
import com.rxpb.tool.MyEncoder;

public class UserAction extends ActionSupport {
	private RxpbUserInfo user;
	
	private String newPassword;
	private String newPassworda;	
	
	private UserService userService;	
	
	public RxpbUserInfo getUser() {
		return user;
	}
	public void setUser(RxpbUserInfo user) {
		this.user = user;
	}
	public String getNewPassword() {
		return newPassword;
	}
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}
	public String getNewPassworda() {
		return newPassworda;
	}
	public void setNewPassworda(String newPassworda) {
		this.newPassworda = newPassworda;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	public String getSelfDetail(){
		user = new RxpbUserInfo();
		user.setUserId(Integer.parseInt(ActionContext.getContext().getSession().get("loginUserId").toString()));
		user = userService.getUserByUserId(user);
		ActionContext.getContext().put("user", user);
		return "SelfDetail";
	}
	
	public String editSelf(){
		boolean res = false;
		
		String msg="";
		
		if(!user.getPassword().isEmpty()&&user.getPassword()!=null){
			MyEncoder encoder = new MyEncoder();			
			user.setPassword(encoder.getMd5(user.getPassword()));
			
			if(!newPassword.equals(newPassworda)){
				msg="两次密码输入不一致";
			}else if(newPassword==""||newPassword==null){
				msg="密码不能为空";
			}else{
				if(userService.userLogin(user)!=null){
					
					user.setPassword(encoder.getMd5(newPassword));
				}else{
					msg="密码不正确";
				}				
			}			
		}
		if(msg.isEmpty()){
			res = userService.editUserSelf(user);
		}
		if(res){
			ActionContext.getContext().put("msg", "编辑成功");
		}else{
			ActionContext.getContext().put("msg", "编辑失败"+msg);
		}

		return this.getSelfDetail();
	}
	
	
	
}
