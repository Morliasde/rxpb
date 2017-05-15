package com.rxpb.dao.i;

import java.util.List;

import com.rxpb.entity.RxpbCompanyInfo;
import com.rxpb.entity.RxpbGroupInfo;
import com.rxpb.entity.RxpbScoreFin;

public interface ResultDaoInterface {
	public List getFatnessSort(final RxpbScoreFin scoreFin);//
	public List getQualitySort(final RxpbScoreFin scoreFin);//
	public List getTasteSort(final RxpbScoreFin scoreFin);//
}
