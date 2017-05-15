package com.rxpb.action;

import java.util.List;

import org.apache.struts2.components.ActionComponent;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.rxpb.entity.RxpbCompetitionInfo;
import com.rxpb.entity.RxpbScoreFin;
import com.rxpb.entity.RxpbScoreQuality;
import com.rxpb.entity.RxpbUserInfo;
import com.rxpb.service.CompetitionService;
import com.rxpb.service.ResultService;
import com.rxpb.service.UserService;
import com.rxpb.tool.MyEncoder;

public class GlobalAction extends ActionSupport {
	private RxpbUserInfo user;
	
	private UserService userService;
	private ResultService resultService;
	private CompetitionService competitionService;
	
	public RxpbUserInfo getUser() {
		return user;
	}
	public void setUser(RxpbUserInfo user) {
		this.user = user;
	}


	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public void setResultService(ResultService resultService) {
		this.resultService = resultService;
	}
	public void setCompetitionService(CompetitionService competitionService) {
		this.competitionService = competitionService;
	}
	public String userLogin(){
		if(user!=null){
			MyEncoder encoder = new MyEncoder();
			user.setPassword(encoder.getMd5(user.getPassword()));
			
			user = userService.userLogin(user);	
			if(user==null){
				ActionContext.getContext().put("msg", "用户名或密码错误");
				return "login";				
			}
			
			int roleId=user.getRoleId();
			
			ActionContext.getContext().getSession().put("loginUserId", user.getUserId());
			ActionContext.getContext().getSession().put("loginRoleId", user.getRoleId());
			ActionContext.getContext().getSession().put("loginUserName", user.getUserName());
			ActionContext.getContext().getSession().put("loginDisplayName", user.getDisplayName());
			
			if(roleId==1){
				return "admin";
			}else if(roleId==2){
				return "student";
			}else if(roleId==3){
				return "judge";				
			}else if(roleId==4){
				return "company";				
			}else{
				return "login";
			}
			
		}else{
			ActionContext.getContext().put("msg", "用户名或密码不能为空");
			return "login";
		}
	}	
	
	public String userLogout(){
		ActionContext.getContext().getSession().clear();
		return "index";
	}
	
	public String userRegist(){
		boolean res =false;
		String msg = "";		
		
		if(user.getUserName().isEmpty() || user.getUserName()==null){
			msg="用户名不能为空";
		}else if(user.getPassword().isEmpty()||user.getPassword()==null){
			msg="密码不能为空";
		}else if(user.getRoleId()==null||user.getRoleId()>4||user.getRoleId()<2){
			msg="用户角色错误";
		}else if(userService.getUserByUserName(user)!=null){
			msg="用户名已存在";
		}else{
			if(user.getDisplayName().isEmpty()||user.getDisplayName()==null){
				if(user.getRoleId()==2){
					user.setDisplayName("未命名工作人员");
				}else if(user.getRoleId()==3){
					user.setDisplayName("未命名评委");
				}				
				//msg="显示名不能为空";
			}
			res = userService.userRegist(user);
			if(!res){
				msg = "数据库写入错误";
			}
		}		
		
		if(!res){
			ActionContext.getContext().put("msg", msg);
			ActionContext.getContext().put("user", user);
			return "regist";
		}else{
			ActionContext.getContext().put("msg", "注册成功");
			return "login";
		}
		
		
	}
	public RxpbCompetitionInfo getCompetitionConfig(){
		
		//默认为当前
		RxpbCompetitionInfo competition = new RxpbCompetitionInfo();		
		
		competition = competitionService.getCompetitonConfig(competition).get(0);
		
		return competition;
	}
	
	public String getQualitySort(){
		RxpbCompetitionInfo competition = this.getCompetitionConfig();
		if (competition.getResultQuality()==1){
			List <RxpbScoreFin> sflist = resultService.getQualitySort();
			ActionContext.getContext().put("sflist", sflist);
		}else{
			ActionContext.getContext().put("msg", "成绩未公布");
		}
		
		return "QualitySort";		
	}
	
	public String getTasteSort(){
		RxpbCompetitionInfo competition = this.getCompetitionConfig();
		if (competition.getResultTaste()==1){
			List <RxpbScoreFin> sflist = resultService.getTasteSort();
			ActionContext.getContext().put("sflist", sflist);
		}else{
			ActionContext.getContext().put("msg", "成绩未公布");
		}
		
		return "TasteSort";		
	}
	
	public String getFatnessSort(){
		RxpbCompetitionInfo competition = this.getCompetitionConfig();
		if (competition.getResultFatness()==1){
			List <RxpbScoreFin> sflist = resultService.getFatnessSort();
			ActionContext.getContext().put("sflist", sflist);
		}else{
			ActionContext.getContext().put("msg", "成绩未公布");
		}
		
		return "FatnessSort";		
	}
	
}
