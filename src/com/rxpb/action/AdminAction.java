package com.rxpb.action;

import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.rxpb.dao.ResultDao;
import com.rxpb.dao.bean.Company;
import com.rxpb.entity.RxpbCompanyInfo;
import com.rxpb.entity.RxpbCompetitionInfo;
import com.rxpb.entity.RxpbCrabInfo;
import com.rxpb.entity.RxpbScoreQuality;
import com.rxpb.entity.RxpbScoreTaste;
import com.rxpb.entity.RxpbUserInfo;
import com.rxpb.service.CompanyService;
import com.rxpb.service.CompetitionService;
import com.rxpb.service.ResultService;
import com.rxpb.service.UserService;
import com.rxpb.tool.MyEncoder;
import com.rxpb.tool.MyMath;

public class AdminAction extends ActionSupport {
	private RxpbUserInfo user;
	private RxpbCompanyInfo company;
	
	private int userId;
	private int companyId;
	private String companyName;
	private int groupId;
	private int groupQueue;
	
	private UserService userService;	
	private CompanyService companyService;
	private ResultService resultService;
	private CompetitionService competitionService;
	
	public RxpbUserInfo getUser() {
		return user;
	}
	public void setUser(RxpbUserInfo user) {
		this.user = user;
	}
	public RxpbCompanyInfo getCompany() {
		return company;
	}
	public void setCompany(RxpbCompanyInfo company) {
		this.company = company;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getCompanyId() {
		return companyId;
	}
	public void setCompanyId(int companyId) {
		this.companyId = companyId;
	}
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	public void setCompanyService(CompanyService companyService) {
		this.companyService = companyService;
	}
	public void setResultService(ResultService resultService) {
		this.resultService = resultService;
	}
	public void setCompetitionService(CompetitionService competitionService) {
		this.competitionService = competitionService;
	}
	
	public String getCompanyName() {
		return companyName;
	}
	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}
	public int getGroupQueue() {
		return groupQueue;
	}
	public void setGroupQueue(int groupQueue) {
		this.groupQueue = groupQueue;
	}
	public String getUserList(){
		List<RxpbUserInfo> list = userService.getUserList();
		ActionContext.getContext().put("users", list);
		return "UserList";
	}
	
	public String getDisableUserList(){
		List<RxpbUserInfo> list = userService.getDisableUserList();
		ActionContext.getContext().put("users", list);
		return "DisUserList";
	}
	
	public String enableUser(){
		user = new RxpbUserInfo();
		user.setUserId(userId);
		user.setStatus(1);
		boolean res = userService.changeUserStatus(user );
		if (res) {
			ActionContext.getContext().put("msg", "启用成功");
		} else {
			ActionContext.getContext().put("msg", "启用失败");
		}
		return this.getDisableUserList();
	}
	
	public String disableUser(){
		user = new RxpbUserInfo();
		user.setUserId(userId);
		user.setStatus(0);
		boolean res = userService.changeUserStatus(user );
		if (res) {
			ActionContext.getContext().put("msg", "禁用成功");
		} else {
			ActionContext.getContext().put("msg", "禁用失败");
		}
		return this.getUserList();
	}
	
	public String deleteUser(){
		user = new RxpbUserInfo();
		user.setUserId(userId);
		user.setStatus(2);
		boolean res = userService.changeUserStatus(user );
		if (res) {
			ActionContext.getContext().put("msg", "删除成功");
		} else {
			ActionContext.getContext().put("msg", "删除失败");
		}
		return this.getDisableUserList();
	}
	
	public String changeUserCompatitionId(){
		user = new RxpbUserInfo();
		user.setUserId(userId);
		boolean res = userService.changeUserCompetitionId(user);
		if (res) {
			ActionContext.getContext().put("msg", "设置成功");
		} else {
			ActionContext.getContext().put("msg", "设置失败");
		}
		return this.getUserList();
	}
	
	public String getUserDetail(){
		user = new RxpbUserInfo();
		user.setUserId(userId);
		user = userService.getUserByUserId(user);
		ActionContext.getContext().put("user", user);
		return "UserDetail";
	}
	
	public String editUser(){
		boolean res = false;
		res = userService.editUser(user);
		if(res){
			ActionContext.getContext().put("msg", "编辑成功");
		}else{
			ActionContext.getContext().put("msg", "编辑失败");
		}
		this.setUserId(user.getUserId());
		return this.getUserDetail();
	}
	
	public String getCompanyList(){
		List<Company> list = companyService.getCompanyList();
		ActionContext.getContext().put("companies", list);
		return "CompanyList";
	}
	
	public String getCompanyDetail(){
		RxpbCompanyInfo company = new RxpbCompanyInfo();
		company.setCompanyId(companyId);
		company = companyService.getCompanyById(company);
		ActionContext.getContext().put("company", company);
		return "CompanyDetail";
	}
	
	public String editCompany(){
		boolean res = false;
		res = companyService.editCompany(company);
		if(res){
			ActionContext.getContext().put("msg", "编辑成功");
		}else{
			ActionContext.getContext().put("msg", "编辑失败");
		}
		this.setCompanyId(company.getCompanyId());
		return this.getCompanyDetail();
	}

	public String insertCompany(){
		int i =1;
		String companyName;
		String errorMsg = "";
		while (true) {
			companyName = ServletActionContext.getRequest().getParameter(
					"companyName" + i);
			//没有更多元素了，停止插入
			if (companyName == null) {
				break;
			}else{
				//输入为空，不执行插入
				if(companyName!=""){
					company=new RxpbCompanyInfo();
					company.setCompanyName(companyName);
					boolean res = companyService.insertCompany(company);
					if(!res){errorMsg+=companyName+" ";}
				}				
			}			
			i++;
		}
		if(errorMsg!=""){
			ActionContext.getContext().put("msg", errorMsg+"添加失败");
		}else{
			ActionContext.getContext().put("msg", "添加成功");
		}	
		return this.getCompanyList();		
	}
	
	public String deleteCompany(){
		boolean res = false;
		company = new RxpbCompanyInfo();
		company.setCompanyId(companyId);
		res = companyService.deleteCompany(company);
		if(res){
			ActionContext.getContext().put("msg", "删除成功");
		}else{
			ActionContext.getContext().put("msg", "删除失败");
		}
		return this.getCompanyList();
	}
	
	public String getCompanyScore(){
		//companyId
		//肥满度
		
		if(companyId!=0&&!companyName.isEmpty()&&groupId!=0&&groupQueue!=0){
			MyMath myMath = new MyMath();
			
			RxpbCompetitionInfo competition = new RxpbCompetitionInfo();
			competition = competitionService.getCompetitonList(competition).get(0);
			
			company = new RxpbCompanyInfo();
			company.setCompanyId(companyId);
			//雌
			List<RxpbCrabInfo> clistF = resultService.getFatnessScoreByCompany(company,groupId, 2);
			//雄
			List<RxpbCrabInfo> clistM = resultService.getFatnessScoreByCompany(company,groupId, 1);
			
			//获取雄蟹平均肥满度
			float fatnessAvg = myMath.getFatnessAvg(clistF);
			//获取雄蟹平均体重
			float weightAvg = myMath.getWeightAvg(clistF);
			//获取雄蟹肥满度标准差
			float fatnesssSd = myMath.getFatnesssSd(clistF);
			//获取雄蟹体重标准差
			float weightSd = myMath.getWeightSd(clistF);
			//计算
			float finScoreF=fatnessAvg
					+competition.getVarWeightF()*weightAvg
					-competition.getVarFfatnessSd()*fatnesssSd
					-competition.getVarFweightSd()*weightSd;
			
			//获取雄蟹平均肥满度
			fatnessAvg = myMath.getFatnessAvg(clistF);
			//获取雄蟹平均体重
			weightAvg = myMath.getWeightAvg(clistF);
			//获取雄蟹肥满度标准差
			fatnesssSd = myMath.getFatnesssSd(clistF);
			//获取雄蟹体重标准差
			weightSd = myMath.getWeightSd(clistF);
			//计算
			float finScoreM=fatnessAvg
					+competition.getVarWeightM()*weightAvg
					-competition.getVarMfatnessSd()*fatnesssSd
					-competition.getVarMweightSd()*weightSd;
			
			
			//种质分
			//雌
			List<RxpbScoreQuality> cqListF = resultService.getQualityScoreByCompany(company,groupId, 2);
			//雄
			List<RxpbScoreQuality> cqListM = resultService.getQualityScoreByCompany(company,groupId, 1);
			
			float finQScoreF = myMath.getQualityAvg(cqListF);
			float finQScoreM = myMath.getQualityAvg(cqListM);
			
			// 口感分
			// 雌
			List<RxpbScoreTaste> ctListF = resultService.getTasteScoreByCompany(company,groupId, 2);
			// 雄
			List<RxpbScoreTaste> ctListM = resultService.getTasteScoreByCompany(company,groupId, 1);
			
			float finTScoreF = myMath.getTasteAvg(ctListF);
			float finTScoreM = myMath.getTasteAvg(ctListM);
			
			ActionContext.getContext().put("clistF", clistF);
			ActionContext.getContext().put("clistM", clistM);
			ActionContext.getContext().put("finScoreF", finScoreF);
			ActionContext.getContext().put("finScoreM", finScoreM);
			ActionContext.getContext().put("cqListF", cqListF);
			ActionContext.getContext().put("cqListM", cqListM);
			ActionContext.getContext().put("finQScoreF", finQScoreF);
			ActionContext.getContext().put("finQScoreM", finQScoreM);
			ActionContext.getContext().put("ctListF", ctListF);
			ActionContext.getContext().put("ctListM", ctListM);
			ActionContext.getContext().put("finTScoreF", finTScoreF);
			ActionContext.getContext().put("finTScoreM", finTScoreM);
			ActionContext.getContext().put("companyId", companyId);
			ActionContext.getContext().put("companyName", companyName);
			ActionContext.getContext().put("groupId", groupId);
			ActionContext.getContext().put("groupQueue", groupQueue);
		}
				
		return "CompanyScore";
	}
	
}





