package com.rxpb.action;

import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.rxpb.dao.CrabDao;
import com.rxpb.entity.RxpbCrabInfo;
import com.rxpb.entity.RxpbGroupInfo;
import com.rxpb.entity.RxpbUserInfo;
import com.rxpb.service.CrabService;
import com.rxpb.service.GroupService;
import com.rxpb.service.UserService;
import com.rxpb.tool.MyEncoder;

public class StudentAction extends ActionSupport {
	private RxpbCrabInfo crab;
	
	private int groupId;
	private int crabId;
	private int groupQueue;//显示的组号
	private int number;//数目
	
	private GroupService groupService;
	private CrabService crabService;
	
	public RxpbCrabInfo getCrab() {
		return crab;
	}
	public void setCrab(RxpbCrabInfo crab) {
		this.crab = crab;
	}
	
	public int getGroupId() {
		return groupId;
	}
	public void setGroupId(int groupId) {
		this.groupId = groupId;
	}
	public int getCrabId() {
		return crabId;
	}
	public void setCrabId(int crabId) {
		this.crabId = crabId;
	}
	public int getGroupQueue() {
		return groupQueue;
	}
	public void setGroupQueue(int groupQueue) {
		this.groupQueue = groupQueue;
	}
	public int getNumber() {
		return number;
	}
	public void setNumber(int number) {
		this.number = number;
	}
	
	public void setGroupService(GroupService groupService) {
		this.groupService = groupService;
	}
	public void setCrabService(CrabService crabService) {
		this.crabService = crabService;
	}
	
	
	/*
		获取组列表	student/getGroupList
		获取组内蟹数据详情	student/getGroupData
		添加蟹数目	student/addCrab
		保存蟹数据	student/saveCrabData
		删除蟹数据	student/deleteCrab
			
		获取组内蟹种质分详情	student/getGroupQuailty
		保存组内蟹种质分	student/saveGroupQuailty
		获取组内蟹口感分详情	student/getGroupTaste
		保存组内蟹口感分	student/saveGroupTaste

	 */
	
	
	public String getGroupList(){
		//groupId升序取出
		List<RxpbGroupInfo> list = groupService.getGroupList();
		ActionContext.getContext().put("groups", list);
		return "GroupList";
	}
	
	public String getGroupData(){
		if(this.groupId!=0){
			crab = new RxpbCrabInfo();
			crab.setGroupId(groupId);
			crab.setCrabSex(1);
			List<RxpbCrabInfo> listM = crabService.getCrabByGroupAndSex(crab);
			crab.setCrabSex(2);
			List<RxpbCrabInfo> listF = crabService.getCrabByGroupAndSex(crab);
			ActionContext.getContext().put("crabMs", listM);
			ActionContext.getContext().put("crabFs", listF);
		}
		
		if(this.groupQueue!=0){
			ActionContext.getContext().put("groupQueue", groupQueue);
		}
		
		return "GroupDetail";
	}
	
	public String addCrab(){
		
		boolean res = false;
		
		if (groupId!=0){
			crab.setGroupId(groupId);
			if(crab.getCrabSex()==3){
				crab.setCrabSex(1);
				res = crabService.addCrab(crab, number);
				crab.setCrabSex(2);
				res = crabService.addCrab(crab, number);
			}else if(crab.getCrabSex()==1||crab.getCrabSex()==2){
				res = crabService.addCrab(crab, number);
			}		
			if (res) {
				ActionContext.getContext().put("msg", "添加成功");
			} else {	
				ActionContext.getContext().put("msg", "添加失败");
			}

			if(this.groupId!=0){
				ActionContext.getContext().put("groupId", groupId);
			}
			if(this.groupQueue!=0){
				ActionContext.getContext().put("groupQueue", groupQueue);
			}
		}
		

		return this.getGroupData();
	}
	
	public String saveCrabData(){
		int i = 0;
		String nextId;
		String nextLabel;
		String nextWight;
		String nextLength;
		List<RxpbCrabInfo> list = new ArrayList<RxpbCrabInfo>();

		// 从table获得数据，包装成数组
		// 雌
		while (true) {
			i++;
			nextId = ServletActionContext.getRequest().getParameter(
					"crabF.crabId" + i);
			if (nextId == null) {
				break;
			}
			nextWight = ServletActionContext.getRequest().getParameter(
					"crabF.crabWeight" + i);
			if (nextWight == null||nextWight == "") {
				continue;
			}
			nextLength = ServletActionContext.getRequest().getParameter(
					"crabF.crabLength" + i);
			if (nextLength == null||nextLength=="") {
				continue;
			}
			nextLabel = ServletActionContext.getRequest().getParameter(
					"crabF.crabLabel" + i);
			
			//检查标签是否存在
			if(nextLabel!=""){
				int gid= crabService.getGroupIdByLabel(nextLabel);
				if(gid>0 && gid!=groupId){
					nextLabel="标识重复";
				}
			}						

			crab = new RxpbCrabInfo();
			crab.setCrabId(Integer.parseInt(nextId));
			crab.setCrabWeight(Float.parseFloat(nextWight));
			crab.setCrabLength(Float.parseFloat(nextLength));
			crab.setCrabLabel(nextLabel);

			list.add(crab);
			
		}
		i = 0;
		// 雄
		while (true) {
			i++;
			nextId = ServletActionContext.getRequest().getParameter(
					"crabM.crabId" + i);
			if (nextId == null) {
				break;
			}
			nextWight = ServletActionContext.getRequest().getParameter(
					"crabM.crabWeight" + i);
			if (nextWight == null||nextWight=="") {
				continue;
			}
			nextLength = ServletActionContext.getRequest().getParameter(
					"crabM.crabLength" + i);
			if (nextLength == null||nextLength=="") {
				continue;
			}
			nextLabel = ServletActionContext.getRequest().getParameter(
					"crabM.crabLabel" + i);
			
			//检查标签是否存在
			if(nextLabel!=""){
				int gid= crabService.getGroupIdByLabel(nextLabel);
				if(gid>0 && gid!=groupId){
					nextLabel="标识重复";
				}
			}	
			
			crab = new RxpbCrabInfo();
			crab.setCrabId(Integer.parseInt(nextId));
			crab.setCrabWeight(Float.parseFloat(nextWight));
			crab.setCrabLength(Float.parseFloat(nextLength));
			crab.setCrabLabel(nextLabel);

			list.add(crab);
		}

		boolean res = crabService.saveCrabData(list);
		if (res) {
			ActionContext.getContext().put("msg", "提交成功");
		} else {
			ActionContext.getContext().put("msg", "提交失败");
		}
		
		if(this.groupId!=0){
			ActionContext.getContext().put("groupId", groupId);
		}
		if(this.groupQueue!=0){
			ActionContext.getContext().put("groupQueue", groupQueue);
		}
		return this.getGroupData();
	}
	
	public String deleteCrab(){
		boolean res = false;
		
		crab = new RxpbCrabInfo();
		crab.setCrabId(crabId);
		
		res = crabService.deleteCrab(crab);
		
		if(this.groupId!=0){
			ActionContext.getContext().put("groupId", groupId);
		}
		if(this.groupQueue!=0){
			ActionContext.getContext().put("groupQueue", groupQueue);
		}
		
		return this.getGroupData();
	}
	
	public String getGroupIdByLabel(){
		int groupQueue = groupService.getGroupIdByLabel(crab.getCrabLabel());
		
		if(groupQueue>0){
			ActionContext.getContext().put("msg", groupQueue);
		}else{
			ActionContext.getContext().put("msg", "该标识不存在");
		}
		
		return "SearchLabel";
	}
	
	//TODO	
	public String getGroupQuailty(){
		
		return null;
	}
	
	public String saveGroupQuailty(){
		
		return null;
	}
	
	public String getGroupTaste(){
		
		return null;
	}
	
	public String saveGroupTaste(){
		
		return null;
	}
	

	
	
	
	
}
