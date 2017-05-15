package com.rxpb.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.rxpb.dao.bean.Company;
import com.rxpb.entity.RxpbCompanyInfo;
import com.rxpb.entity.RxpbCompetitionConfig;
import com.rxpb.entity.RxpbCompetitionInfo;
import com.rxpb.entity.RxpbUserInfo;
import com.rxpb.service.CompanyService;
import com.rxpb.service.CompetitionService;
import com.rxpb.service.ResultService;
import com.rxpb.service.UserService;
import com.rxpb.tool.MyEncoder;

public class AdminCompetitionAction extends ActionSupport {
	private CompetitionService competitionService;
	private ResultService resultService;
	
	private RxpbCompetitionConfig competitionConfig;//当前配置
	private RxpbCompetitionInfo competition;//配置详情
	
	private int competitionId;
	
	public void setCompetitionService(CompetitionService competitionService) {
		this.competitionService = competitionService;
	}
	
	public void setResultService(ResultService resultService) {
		this.resultService = resultService;
	}

	public RxpbCompetitionInfo getCompetition() {
		return competition;
	}


	public void setCompetition(RxpbCompetitionInfo competition) {
		this.competition = competition;
	}


	public void setCompetitionConfig(RxpbCompetitionConfig competitionConfig) {
		this.competitionConfig = competitionConfig;
	}
	
	public int getCompetitionId() {
		return competitionId;
	}

	public void setCompetitionId(int competitionId) {
		this.competitionId = competitionId;
	}

	/*
	获取当前大赛参数	getCompetitionConfig
	大赛参数编辑	editCompetition
	新建大赛（参数）	createCompetition
	设置当前比赛配置	editConfig
	生成成绩	createScore
	生成表格	createExcel
	生成往届成绩表格	createPreviousExcel
	*/

	

	public String getCompetitionConfig(){
		
		if(competition==null){
			//dao操作默认为当前
			competition = new RxpbCompetitionInfo();
		}
		
		competition = competitionService.getCompetitonConfig(competition).get(0);
		
		ActionContext.getContext().put("competition", competition);
		return "CompetitionDetail";
	}
	
	public String getCompetitionList(){
		if(competition==null){
			competition = new RxpbCompetitionInfo();
			//有效
			competition.setStatus(1);
		}	
		
		List<RxpbCompetitionInfo> list = competitionService.getCompetitonList(competition);
		
		ActionContext.getContext().put("competitions", list);
		return "CompetitionList";
	}
	
	public String editCompetition(){
		boolean res = false;
		if(competition!=null){
			if(competition.getCompetitionId()!=null){
				res = competitionService.editCompetition(competition);
			}else{
				res = competitionService.createCompetition(competition);
			}
			
		}		
		if(res){
			ActionContext.getContext().put("msg", "编辑成功");
		}else{
			ActionContext.getContext().put("msg", "编辑失败");
		}
		return this.getCompetitionConfig();
	}
	
	
	//新建大赛
	public String createCompetition(){
		boolean res = false;
		res = competitionService.createCompetition(competition);
		if(res){
			ActionContext.getContext().put("msg", "添加成功");
		}else{
			ActionContext.getContext().put("msg", "添加失败");
		}
		return this.getCompetitionConfig();
		
	}
		
	public String editConfig(){
		//传入一个Competition(ID),修改config
		boolean res = false;
		competitionConfig = new RxpbCompetitionConfig();
		competitionConfig.setCompetitionId(this.getCompetitionId());
		res = competitionService.editConfig(competitionConfig);
		if(res){
			ActionContext.getContext().put("msg", "编辑成功");
		}else{
			ActionContext.getContext().put("msg", "编辑失败");
		}
		return this.getCompetitionConfig();
	}
	
	public String createScore(){
		
		boolean res = false;
		res = resultService.createScore();
		if(res){
			ActionContext.getContext().put("msg", "生成成功");
		}else{
			ActionContext.getContext().put("msg", "生成失败");
		}
		return this.getCompetitionConfig();
	}
	
	// 获取输出流
	public InputStream getInputExcel() {
		return (InputStream) ActionContext.getContext().get("excelStream");
	}
	
	public String createExcel(){		
		HSSFWorkbook wk = resultService.createExcel(0);
		
		// 输出excel
		try {
			ByteArrayOutputStream out = new ByteArrayOutputStream();
			wk.write(out);
			ActionContext.getContext().put("excelStream",
					new ByteArrayInputStream(out.toByteArray()));
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH-mm-ss");
			ActionContext.getContext().put(
					"filename",
					"FinalScore"
							+ df.format(System.currentTimeMillis()).toString()
							+ ".xls");// 设置文件名
			return SUCCESS;
		} catch (Throwable th) {
			th.printStackTrace();
			System.out.println("无法输出Excel文件");
			return ERROR;
		}
		
	}
	public String createPreviousExcel(){
		return null;
	}

	
	
}





