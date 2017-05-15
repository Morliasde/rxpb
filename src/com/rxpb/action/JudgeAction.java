package com.rxpb.action;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.rxpb.entity.RxpbGroupInfo;
import com.rxpb.entity.RxpbScoreQuality;
import com.rxpb.entity.RxpbScoreTaste;
import com.rxpb.entity.RxpbUserInfo;
import com.rxpb.service.GroupService;
import com.rxpb.service.UserService;
import com.rxpb.tool.MyEncoder;

public class JudgeAction extends ActionSupport {
	private RxpbGroupInfo group;
	private RxpbScoreQuality scoreQualityF;
	private RxpbScoreQuality scoreQualityM;
	private RxpbScoreTaste scoreTasteF;
	private RxpbScoreTaste scoreTasteM;
	
	private int groupId;
	private int groupQueue;//显示的组号
	
	private GroupService groupService;

	public RxpbGroupInfo getGroup() {
		return group;
	}

	public void setGroup(RxpbGroupInfo group) {
		this.group = group;
	}

	public RxpbScoreQuality getScoreQualityF() {
		return scoreQualityF;
	}

	public void setScoreQualityF(RxpbScoreQuality scoreQualityF) {
		this.scoreQualityF = scoreQualityF;
	}

	public RxpbScoreQuality getScoreQualityM() {
		return scoreQualityM;
	}

	public void setScoreQualityM(RxpbScoreQuality scoreQualityM) {
		this.scoreQualityM = scoreQualityM;
	}

	public RxpbScoreTaste getScoreTasteF() {
		return scoreTasteF;
	}

	public void setScoreTasteF(RxpbScoreTaste scoreTasteF) {
		this.scoreTasteF = scoreTasteF;
	}

	public RxpbScoreTaste getScoreTasteM() {
		return scoreTasteM;
	}

	public void setScoreTasteM(RxpbScoreTaste scoreTasteM) {
		this.scoreTasteM = scoreTasteM;
	}

	public int getGroupId() {
		return groupId;
	}

	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}

	public int getGroupQueue() {
		return groupQueue;
	}

	public void setGroupQueue(int groupQueue) {
		this.groupQueue = groupQueue;
	}

	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	
	
	/*
		获取组列表	judge/getGroupList
		获取本人组内蟹种质评分	judge/getQualityByJudge
		保存本人组内蟹种质评分	judge/saveQualityByJudge
		获取本人组内蟹口感评分	judge/getTasteByJudge
		保存本人组内蟹口感评分	judge/saveTasteByJudge
	 * 	
	 */
	
	public String getGroupList(){
		//groupId升序取出
		List<RxpbGroupInfo> list = groupService.getGroupList();
		ActionContext.getContext().put("groups", list);
		return "GroupList";
	}
		
	public String getQualityByJudge(){
		RxpbScoreQuality scoreQuality = new RxpbScoreQuality();
		scoreQuality.setGroupId(groupId);
		List<RxpbScoreQuality> list = groupService.getQualityByJudge(scoreQuality);
		
		if(list == null){
			list = new ArrayList<RxpbScoreQuality>();
		}
		
		Iterator<RxpbScoreQuality> it = list.iterator();
		RxpbScoreQuality sqtemp = new RxpbScoreQuality();
		scoreQualityF = new RxpbScoreQuality();
		scoreQualityM = new RxpbScoreQuality();
		
		while(it.hasNext()){
			sqtemp = it.next();
			if(sqtemp.getCrabSex()==2){
				scoreQualityF = sqtemp;
			}else if(sqtemp.getCrabSex()==1){
				scoreQualityM = sqtemp;
			}
		}
		
		ActionContext.getContext().put("scoreQualityF",scoreQualityF);
		ActionContext.getContext().put("scoreQualityM",scoreQualityM);
		
		if(this.groupId!=0){
			ActionContext.getContext().put("groupId", groupId);
		}
		if(this.groupQueue!=0){
			ActionContext.getContext().put("groupQueue", groupQueue);
		}
		
		return "QualityDetail";
	}
	
	public String saveQualityByJudge(){
		boolean res = false;
		scoreQualityF.setGroupId(groupId);
		scoreQualityF.setCrabSex(2);
		res = groupService.saveQualityByJudge(scoreQualityF);
		
		scoreQualityM.setGroupId(groupId);
		scoreQualityM.setCrabSex(1);
		res = groupService.saveQualityByJudge(scoreQualityM);
		
		if (res) {
			ActionContext.getContext().put("msg", "保存成功");
		} else {	
			ActionContext.getContext().put("msg", "保存失败");
		}
		
				
		
		return "QualityDetail";
	}
	
	public String getTasteByJudge(){
		
		RxpbScoreTaste scoreTaste = new RxpbScoreTaste();
		scoreTaste.setGroupId(groupId);
		List<RxpbScoreTaste> list = groupService.getTasteByJudge(scoreTaste);
		
		if(list == null){
			list = new ArrayList<RxpbScoreTaste>();
		}
		
		Iterator<RxpbScoreTaste> it = list.iterator();
		RxpbScoreTaste sqtemp = new RxpbScoreTaste();
		scoreTasteF = new RxpbScoreTaste();
		scoreTasteM = new RxpbScoreTaste();
		
		while(it.hasNext()){
			sqtemp = it.next();
			if(sqtemp.getCrabSex()==2){
				scoreTasteF = sqtemp;
			}else if(sqtemp.getCrabSex()==1){
				scoreTasteM = sqtemp;
			}
		}
		
		ActionContext.getContext().put("scoreTasteF",scoreTasteF);
		ActionContext.getContext().put("scoreTasteM",scoreTasteM);
		
		if(this.groupId!=0){
			ActionContext.getContext().put("groupId", groupId);
		}
		if(this.groupQueue!=0){
			ActionContext.getContext().put("groupQueue", groupQueue);
		}
		
		return "TasteDetail";
	}
	
	public String saveTasteByJudge(){
		boolean res = false;
		scoreTasteF.setGroupId(groupId);
		scoreTasteF.setCrabSex(2);
		res = groupService.saveTasteByJudge(scoreTasteF);
		
		scoreTasteM.setGroupId(groupId);
		scoreTasteM.setCrabSex(1);
		res = groupService.saveTasteByJudge(scoreTasteM);

		if (res) {
			ActionContext.getContext().put("msg", "保存成功");
		} else {	
			ActionContext.getContext().put("msg", "保存失败");
		}		
		
		
		
		return "TasteDetail";
	}
	
	
}
