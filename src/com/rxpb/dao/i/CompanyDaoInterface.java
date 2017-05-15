package com.rxpb.dao.i;

import java.util.List;

import com.rxpb.entity.RxpbCompanyInfo;

public interface CompanyDaoInterface {
	public List search(final RxpbCompanyInfo company);//查
	public boolean update(RxpbCompanyInfo company);//改
	public boolean insert(RxpbCompanyInfo company);//增
	public boolean delete(RxpbCompanyInfo company);//删

}
