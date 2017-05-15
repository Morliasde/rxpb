package com.rxpb.service.i;

import java.util.List;

import com.rxpb.dao.bean.Company;
import com.rxpb.entity.RxpbCompanyInfo;

public interface CompanyServiceInterface {
	public List<Company> getCompanyList();	//获取参赛单位列表
	public RxpbCompanyInfo getCompanyById(RxpbCompanyInfo company); //获取参选单位
	public boolean insertCompany(RxpbCompanyInfo company); //添加参选单位(批量)
	public boolean editCompany(RxpbCompanyInfo company); //编辑参选单位
	public boolean deleteCompany(RxpbCompanyInfo company); //删除参选单位

}
