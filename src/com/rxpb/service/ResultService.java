package com.rxpb.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.util.CellRangeAddress;

import com.rxpb.dao.CompanyDao;
import com.rxpb.dao.CompetitionDao;
import com.rxpb.dao.CrabDao;
import com.rxpb.dao.GroupDao;
import com.rxpb.dao.ResultDao;
import com.rxpb.dao.ScoreDao;
import com.rxpb.entity.RxpbCompanyInfo;
import com.rxpb.entity.RxpbCompetitionConfig;
import com.rxpb.entity.RxpbCompetitionInfo;
import com.rxpb.entity.RxpbCrabInfo;
import com.rxpb.entity.RxpbGroupInfo;
import com.rxpb.entity.RxpbScoreFin;
import com.rxpb.entity.RxpbScoreFinId;
import com.rxpb.entity.RxpbScoreQuality;
import com.rxpb.entity.RxpbScoreTaste;
import com.rxpb.service.i.ResultServiceInterface;
import com.rxpb.tool.MyMath;
import com.rxpb.tool.MySearch;

public class ResultService implements ResultServiceInterface {

	GroupDao groupDao;
	CrabDao crabDao;
	CompetitionDao competitionDao;
	ScoreDao scoreDao;
	CompanyDao companyDao;
	ResultDao resultDao;
		
	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public void setCrabDao(CrabDao crabDao) {
		this.crabDao = crabDao;
	}

	public void setCompetitionDao(CompetitionDao competitionDao) {
		this.competitionDao = competitionDao;
	}

	public void setScoreDao(ScoreDao scoreDao) {
		this.scoreDao = scoreDao;
	}
	
	public void setCompanyDao(CompanyDao companyDao) {
		this.companyDao = companyDao;
	}

	public void setResultDao(ResultDao resultDao) {
		this.resultDao = resultDao;
	}

	@Override
	public boolean createScore() {
		RxpbGroupInfo group = new RxpbGroupInfo();
		List <RxpbGroupInfo> groupList = groupDao.search(group);
		Iterator<RxpbGroupInfo> groupIt = groupList.iterator();
		
		RxpbCompetitionInfo competition = new RxpbCompetitionInfo();
		competition.setCompetitionId(competitionDao.getHibernateTemplate().get(RxpbCompetitionConfig.class, 1).getCompetitionId());
		competition = competitionDao.getHibernateTemplate().get(RxpbCompetitionInfo.class, competition.getCompetitionId());
		
		try{
			while(groupIt.hasNext()){
				
				/*处理肥满度分数
				 *得分=平均肥满度+体重参数*平均体重 -肥满度的标准偏差参数*肥满度的标准偏差- 体重的标准偏差参数*体重的标准偏差
				 * */			
				float fatnessAvg;
				float weightAvg;
				float fatnesssSd;
				float weightSd;
				
				float finScore;
				
				MyMath myMath = new MyMath();
				
				group = groupIt.next();
				//获取组的肥满度信息
				RxpbCrabInfo crab = new RxpbCrabInfo();
				crab.setGroupId(group.getGroupId());
				
				//雄蟹		
				crab.setCrabSex(1);
				List<RxpbCrabInfo> crabList = crabDao.searchTop10(crab);			
				//获取雄蟹平均肥满度
				fatnessAvg = myMath.getFatnessAvg(crabList);
				//获取雄蟹平均体重
				weightAvg = myMath.getWeightAvg(crabList);
				//获取雄蟹肥满度标准差
				fatnesssSd = myMath.getFatnesssSd(crabList);
				//获取雄蟹体重标准差
				weightSd = myMath.getWeightSd(crabList);
				//计算
				finScore=fatnessAvg
						+competition.getVarWeightM()*weightAvg
						-competition.getVarMfatnessSd()*fatnesssSd
						-competition.getVarMweightSd()*weightSd;
				//放入数据
				group.setFatnessScoreM(finScore);
				
				//雌蟹
				crab.setCrabSex(2);
				crabList = crabDao.search(crab);	
				//获取雌蟹平均肥满度
				fatnessAvg = myMath.getFatnessAvg(crabList);
				//获取雌蟹平均体重
				weightAvg = myMath.getWeightAvg(crabList);
				//获取雌蟹肥满度标准差
				fatnesssSd = myMath.getFatnesssSd(crabList);
				//获取雌蟹体重标准差
				weightSd = myMath.getWeightSd(crabList);
				//计算
				finScore=fatnessAvg
						+competition.getVarWeightF()*weightAvg
						-competition.getVarFfatnessSd()*fatnesssSd
						-competition.getVarFweightSd()*weightSd;
				//放入数据
				group.setFatnessScoreF(finScore);
				
				/*处理种质分
				 * 
				 * */
				
				RxpbScoreQuality scoreQuality = new RxpbScoreQuality();
				scoreQuality.setGroupId(group.getGroupId());
				List<RxpbScoreQuality> scoreQualityList = new ArrayList<RxpbScoreQuality>();
				
				//雄蟹
				scoreQuality.setCrabSex(1);	
				scoreQualityList = new ArrayList<RxpbScoreQuality>();
				scoreQualityList = scoreDao.searchScoreQuality(scoreQuality);
				//获得雄蟹成绩
				group.setQualityScoreM(myMath.getQualityAvg(scoreQualityList));
				
				//雌蟹
				scoreQuality.setCrabSex(2);	
				scoreQualityList = new ArrayList<RxpbScoreQuality>(); 
				scoreQualityList = scoreDao.searchScoreQuality(scoreQuality);
				//获得雌蟹成绩
				group.setQualityScoreF(myMath.getQualityAvg(scoreQualityList));
				
				/*处理口感分
				 * 
				 * */
				
				RxpbScoreTaste scoreTaste = new RxpbScoreTaste();
				scoreTaste.setGroupId(group.getGroupId());
				List<RxpbScoreTaste> scoreTasteList = new ArrayList<RxpbScoreTaste>();
				
				//雄蟹
				scoreTaste.setCrabSex(1);	
				scoreTasteList = new ArrayList<RxpbScoreTaste>();
				scoreTasteList = scoreDao.searchScoreTaste(scoreTaste);
				//获得雄蟹成绩
				group.setTasteScoreM(myMath.getTasteAvg(scoreTasteList));
				
				//雌蟹
				scoreTaste.setCrabSex(2);	
				scoreTasteList = new ArrayList<RxpbScoreTaste>(); 
				scoreTasteList = scoreDao.searchScoreTaste(scoreTaste);
				//获得雌蟹成绩
				group.setTasteScoreF(myMath.getTasteAvg(scoreTasteList));
							
				
				//保存
				groupDao.update(group);
			}
		}catch (Exception e) {
			e.printStackTrace();
			return false;
		}		
		
		return true;				
	}

	//忽略属性弃用
	@SuppressWarnings("deprecation")
	@Override
	public HSSFWorkbook createExcel(int competitionId) {		
		//新建表格
		HSSFWorkbook wk = new HSSFWorkbook();
		
		try{
			//默认生成当前比赛数据
			if(competitionId==0){
				competitionId = competitionDao.getHibernateTemplate().get(RxpbCompetitionConfig.class,1).getCompetitionId();
			}
						
			// 字体对象
			HSSFFont font = wk.createFont();
			font.setFontName("微软雅黑");
			font.setFontHeightInPoints((short) 14);// 设置字体大小

			// 表样式
			// 红色背景
			CellStyle bgRed = wk.createCellStyle();
			bgRed = wk.createCellStyle();
			bgRed.setFillForegroundColor(IndexedColors.LIGHT_ORANGE.getIndex()); // 背景色
			bgRed.setFillPattern(CellStyle.SOLID_FOREGROUND);
			bgRed.setBorderBottom((short) 1); // 下边框
			bgRed.setBorderLeft((short) 1);// 左边框
			bgRed.setBorderTop((short) 1);// 上边框
			bgRed.setBorderRight((short) 1);// 右边框
			bgRed.setFont(font);
			// 黄色背景
			CellStyle bgYellow = wk.createCellStyle();
			bgYellow = wk.createCellStyle();
			bgYellow.setFillForegroundColor(IndexedColors.LIGHT_YELLOW.getIndex()); // 背景色
			bgYellow.setFillPattern(CellStyle.SOLID_FOREGROUND);
			bgYellow.setBorderBottom((short) 1); // 下边框
			bgYellow.setBorderLeft((short) 1);// 左边框
			bgYellow.setBorderTop((short) 1);// 上边框
			bgYellow.setBorderRight((short) 1);// 右边框
			bgYellow.setFont(font);
			// 橙色背景
			CellStyle bgOrange = wk.createCellStyle();
			bgOrange = wk.createCellStyle();
			bgOrange.setFillForegroundColor(IndexedColors.ORANGE.getIndex()); // 背景色
			bgOrange.setFillPattern(CellStyle.SOLID_FOREGROUND);
			bgOrange.setBorderBottom((short) 1); // 下边框
			bgOrange.setBorderLeft((short) 1);// 左边框
			bgOrange.setBorderTop((short) 1);// 上边框
			bgOrange.setBorderRight((short) 1);// 右边框
			bgOrange.setFont(font);
			// 淡蓝背景
			CellStyle bgBlue = wk.createCellStyle();
			bgBlue = wk.createCellStyle();
			bgBlue.setFillForegroundColor(IndexedColors.LIGHT_GREEN.getIndex()); // 背景色
			bgBlue.setFillPattern(CellStyle.SOLID_FOREGROUND);
			bgBlue.setBorderBottom((short) 1); // 下边框
			bgBlue.setBorderLeft((short) 1);// 左边框
			bgBlue.setBorderTop((short) 1);// 上边框
			bgBlue.setBorderRight((short) 1);// 右边框
			bgBlue.setFont(font);
			// 灰色背景
			CellStyle bgGrey = wk.createCellStyle();
			bgGrey = wk.createCellStyle();
			bgGrey.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex()); // 背景色
			bgGrey.setFillPattern(CellStyle.SOLID_FOREGROUND);
			bgGrey.setBorderBottom((short) 1); // 下边框
			bgGrey.setBorderLeft((short) 1);// 左边框
			bgGrey.setBorderTop((short) 1);// 上边框
			bgGrey.setBorderRight((short) 1);// 右边框
			bgGrey.setFont(font);

			// 白色背景(无背景)
			CellStyle bgWhite = wk.createCellStyle();
			bgWhite.setBorderBottom((short) 1); // 下边框
			bgWhite.setBorderLeft((short) 1);// 左边框
			bgWhite.setBorderTop((short) 1);// 上边框
			bgWhite.setBorderRight((short) 1);// 右边框
			bgWhite.setFont(font);

			// 红字
			CellStyle ftRed = wk.createCellStyle();
			HSSFFont fontRed = wk.createFont();
			fontRed.setFontName("微软雅黑");
			fontRed.setColor(IndexedColors.RED.getIndex());
			fontRed.setFontHeightInPoints((short) 12);// 设置字体大小
			ftRed = wk.createCellStyle();
			ftRed.setFont(fontRed);
			
			//查找函数
			MySearch mySearch = new MySearch();
			
			// 地址
			CellRangeAddress cra;
			
			// 第一个sheet,存放肥满度详情
			HSSFSheet sheet = wk.createSheet("金蟹奖、优质奖-肥满度");
			sheet.setColumnWidth(6, 3 * 256);
			int rows = 0;
			
			HSSFRow row;
			HSSFCell cell;
			
			//显示的组号
			int gqueue = 0;
			
			//参选单位列表
			RxpbCompanyInfo company = new RxpbCompanyInfo();
			company.setCompetitionId(competitionId);
			List<RxpbCompanyInfo> companyList = companyDao.search(company);
			
			RxpbGroupInfo group = new RxpbGroupInfo();
			group.setCompetitionId(competitionId);
			List<RxpbGroupInfo> groupList = groupDao.search(group);
			Iterator<RxpbGroupInfo> groupIt = groupList.iterator();
			
			RxpbCrabInfo crab;
			
			while(groupIt.hasNext()){
				group = groupIt.next();
				gqueue++;
				crab = new RxpbCrabInfo();
				crab.setGroupId(group.getGroupId());
				crab.setCompetitionId(competitionId);
				
				// 空两行
				row = sheet.createRow(rows++);
				row = sheet.createRow(rows++);
				
				//定位到第三行
				row = sheet.createRow(rows);
				
				// 第一行:组号
				int column = 1;// 第一列空着
				cell = row.createCell(column++);
				cell.setCellValue("第" + gqueue + "组");
				
				// 设置样式
				// 红色背景
				cell.setCellStyle(bgRed);

				// 合并单元格
				cra = new CellRangeAddress(rows, rows, (short) 1, (short) 11);
				sheet.addMergedRegion(cra);

				// 填充空列
				for (int a = 2; a <= 11; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					// 红色背景
					cell.setCellStyle(bgRed);
				}
				
				// 转到雌雄行
				rows++;

				// 第二行:雌雄
				// 合并单元格
				cra = new CellRangeAddress(rows, rows, (short) 1, (short) 5);
				sheet.addMergedRegion(cra);
				cra = new CellRangeAddress(rows, rows, (short) 7, (short) 11);
				sheet.addMergedRegion(cra);
				
				// 输入数据
				row = sheet.createRow(rows++);
				
				//雌列
				column = 1;
				cell = row.createCell(column);
				cell.setCellValue("雌");
				cell.setCellStyle(bgBlue);
				// 填充空列
				for (int a = 2; a <= 5; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					cell.setCellStyle(bgBlue);
				}

				// 灰列
				cell = row.createCell(6);
				cell.setCellValue("");
				// 设置样式
				cell.setCellStyle(bgGrey);
				
				// 雄列
				column = 7;
				cell = row.createCell(column);
				cell.setCellValue("雄");
				cell.setCellStyle(bgBlue);
				// 填充空列
				for (int a = 8; a <= 11; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					cell.setCellStyle(bgBlue);
				}
				
				// 第三行标题
				row = sheet.createRow(rows++);
				column = 1;
				cell = row.createCell(column++);
				cell.setCellValue("序号");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("标识");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("体重");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("壳长");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("肥满度");
				cell.setCellStyle(bgWhite);
				cell.setCellStyle(bgBlue);
				cell = row.createCell(column++);
				cell.setCellValue(" ");
				cell.setCellStyle(bgGrey);
				cell = row.createCell(column++);
				cell.setCellValue("序号");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("标识");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("体重");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("壳长");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("肥满度");
				cell.setCellStyle(bgBlue);
				
				// 第四至十三行:数据
				//雌蟹列表
				crab.setCrabSex(2);
				List<RxpbCrabInfo> crabListF =crabDao.searchTop10(crab);
				Iterator<RxpbCrabInfo> clFIt = crabListF.iterator();
				//雄蟹列表
				crab.setCrabSex(1);
				List<RxpbCrabInfo> crabListM =crabDao.searchTop10(crab);
				Iterator<RxpbCrabInfo> clMIt = crabListM.iterator();
				
				RxpbCrabInfo cTemp = new RxpbCrabInfo();
				
				for(int i =1;i<=10;i++){
					column = 1;
					row = sheet.createRow(rows++);
					
					cTemp = new RxpbCrabInfo();
					
					if(clFIt.hasNext()){
						cTemp = clFIt.next();
						//null值出错
						Float z = new Float(0);
						if(cTemp.getCrabFatness()==null){
							cTemp.setCrabFatness(z);
						}
						if(cTemp.getCrabLength()==null){
							cTemp.setCrabLength(z);
						}
						if(cTemp.getCrabWeight()==null){
							cTemp.setCrabWeight(z);
						}
					}else{
						//小于10个
						Float z = new Float(0);
						cTemp.setCrabFatness(z);
						cTemp.setCrabLength(z);
						cTemp.setCrabWeight(z);
					}
									
					//雌蟹
					//序号列
					cell = row.createCell(column++);
					cell.setCellValue("雌" + i);
					cell.setCellStyle(bgWhite);
					
					//标识
					cell = row.createCell(column++);
					cell.setCellValue(cTemp.getCrabLabel());
					cell.setCellStyle(bgWhite);
					
					//体重
					cell = row.createCell(column++);
					cell.setCellValue(cTemp.getCrabWeight());
					cell.setCellStyle(bgWhite);
					
					//壳长
					cell = row.createCell(column++);
					cell.setCellValue(cTemp.getCrabLength());
					cell.setCellStyle(bgWhite);
					
					//肥满度
					cell = row.createCell(column++);
					cell.setCellValue(cTemp.getCrabFatness());
					cell.setCellStyle(bgWhite);
					
					//灰列
					cell = row.createCell(column++);
					cell.setCellValue(" ");
					cell.setCellStyle(bgGrey);
					
					//雄蟹				
					cTemp = new RxpbCrabInfo();
					
					if(clMIt.hasNext()){
						cTemp = clMIt.next();
						//null值出错
						Float z = new Float(0);
						if(cTemp.getCrabFatness()==null){
							cTemp.setCrabFatness(z);
						}
						if(cTemp.getCrabLength()==null){
							cTemp.setCrabLength(z);
						}
						if(cTemp.getCrabWeight()==null){
							cTemp.setCrabWeight(z);
						}
					}else{
						//小于10个
						Float z = new Float(0);
						cTemp.setCrabFatness(z);
						cTemp.setCrabLength(z);
						cTemp.setCrabWeight(z);
					}
					
					//序号列
					cell = row.createCell(column++);
					cell.setCellValue("雌" + i);
					cell.setCellStyle(bgWhite);
					
					//标识
					cell = row.createCell(column++);
					cell.setCellValue(cTemp.getCrabLabel());
					cell.setCellStyle(bgWhite);
					
					//体重
					cell = row.createCell(column++);
					cell.setCellValue(cTemp.getCrabWeight());
					cell.setCellStyle(bgWhite);
					
					//壳长
					cell = row.createCell(column++);
					cell.setCellValue(cTemp.getCrabLength());
					cell.setCellStyle(bgWhite);
					
					//肥满度
					cell = row.createCell(column++);
					cell.setCellValue(cTemp.getCrabFatness());
					cell.setCellStyle(bgWhite);
					
				}
				
				
				// 第十四行:得分
				// 合并单元格
				cra = new CellRangeAddress(rows, rows, (short) 1, (short) 5);
				sheet.addMergedRegion(cra);
				cra = new CellRangeAddress(rows, rows, (short) 7, (short) 11);
				sheet.addMergedRegion(cra);
				// 数据
				row = sheet.createRow(rows++);
				column = 1;
				cell = row.createCell(column);
				cell.setCellValue("雌蟹得分:"
						+ group.getFatnessScoreF());
				cell.setCellStyle(bgYellow);
				// 填充空列
				for (int a = 2; a <= 5; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					cell.setCellStyle(bgYellow);
				}

				// 灰列
				cell = row.createCell(6);
				cell.setCellValue("");
				// 设置样式
				cell.setCellStyle(bgGrey);

				column += 6;
				cell = row.createCell(column);
				cell.setCellValue("雄蟹得分:"
						+ group.getFatnessScoreM());
				cell.setCellStyle(bgYellow);

				// 填充空列
				for (int a = 8; a <= 11; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					cell.setCellStyle(bgYellow);
				}
				
				// 第十五行:蟹数目达标
				// 合并单元格
				cra = new CellRangeAddress(rows, rows, (short) 1, (short) 5);
				sheet.addMergedRegion(cra);
				cra = new CellRangeAddress(rows, rows, (short) 7, (short) 11);
				sheet.addMergedRegion(cra);
				// 数据
				String isNumOk = "达标";
				row = sheet.createRow(rows++);
				column = 1;
				cell = row.createCell(column);
				if (crabListF.size() < 10) {
					isNumOk = "未达标";
				}
				cell.setCellValue("雌蟹数目是否达标:" + isNumOk);
				cell.setCellStyle(bgYellow);

				// 填充空列
				for (int a = 2; a <= 5; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					cell.setCellStyle(bgYellow);
				}
				// 灰列
				cell = row.createCell(6);
				cell.setCellValue("");
				// 设置样式
				cell.setCellStyle(bgGrey);

				isNumOk = "达标";
				column += 6;
				cell = row.createCell(column);
				if (crabListM.size()< 10) {
					isNumOk = "未达标";
				}
				cell.setCellValue("雄蟹数目是否达标:" + isNumOk);
				cell.setCellStyle(bgYellow);
				// 填充空列
				for (int a = 8; a <= 11; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					cell.setCellStyle(bgYellow);
				}

				// 第十六行:组达标
				// 合并单元格
				cra = new CellRangeAddress(rows, rows, (short) 1, (short) 11);
				sheet.addMergedRegion(cra);
				// 数据
				isNumOk = "达标";
				row = sheet.createRow(rows++);
				column = 1;
				cell = row.createCell(column);
				if (crabListM.size() < 10 || crabListF.size() < 10) {
					isNumOk = "未达标";
				}
				cell.setCellValue("本组蟹数目是否达标:" + isNumOk);
				cell.setCellStyle(bgOrange);

				// 填充空列
				for (int a = 2; a <= 11; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					// 红色背景
					cell.setCellStyle(bgOrange);
				}

				// 第十七行:雌雄平均得分
				// 合并单元格
				cra = new CellRangeAddress(rows, rows, (short) 1, (short) 11);
				sheet.addMergedRegion(cra);
				// 数据
				
				row = sheet.createRow(rows++);
				column = 1;
				cell = row.createCell(column);
				cell.setCellValue("雌雄平均得分:"
						+ (((group.getFatnessScoreF()+group.getFatnessScoreM()) / 2)));
				cell.setCellStyle(bgOrange);

				// 填充空列
				for (int a = 2; a <= 11; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					// 红色背景
					cell.setCellStyle(bgOrange);				
				}
				
				
			}
			
			// 第二个sheet,肥满度排名
			sheet = wk.createSheet("金蟹奖、优质奖-肥满度排名");
			// 设置列宽
			sheet.setColumnWidth(0, 10 * 256);
			sheet.setColumnWidth(1, 70 * 256);
			sheet.setColumnWidth(2, 20 * 256);
			sheet.setColumnWidth(3, 10 * 256);
			rows = 0;

			// 第一行:标题
			row = sheet.createRow(rows++);
			int column = 0;
			cell = row.createCell(column++);
			cell.setCellValue("组号");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(column++);
			cell.setCellValue("单位名称");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(column++);
			cell.setCellValue("肥满度分");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(column++);
			cell.setCellValue("排名");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(++column);
			cell.setCellValue("前16名为金蟹奖、其余为优质奖");
			cell.setCellStyle(ftRed);
			
			RxpbScoreFin scoreFin = new RxpbScoreFin();
			scoreFin.setCompetitionId(competitionId);
			List<RxpbScoreFin> scoreFinList = resultDao.getFatnessSort(scoreFin);
			Iterator<RxpbScoreFin> scoreFinIt = scoreFinList.iterator();
			//排名
			int order=0;		
			
			while(scoreFinIt.hasNext()){
				order++;
				scoreFin=scoreFinIt.next();
				
				row = sheet.createRow(rows++);
				column = 0;
				//得到组号
				gqueue = mySearch.findGroupQueue(groupList,scoreFin.getGroupId());
				//组号
				cell = row.createCell(column++);
				cell.setCellValue("第" + gqueue + "组");
				cell.setCellStyle(bgBlue);
				//参选单位
				cell = row.createCell(column++);
				cell.setCellValue(scoreFin.getCompanyName());
				cell.setCellStyle(bgWhite);			
				//得分
				cell = row.createCell(column++);
				cell.setCellValue(scoreFin.getFatnessScore());
				cell.setCellStyle(bgWhite);			
				//排名
				cell = row.createCell(column++);
				cell.setCellValue(order);
				cell.setCellStyle(bgYellow);
				
				if (order <= 16) {
					cell = row.createCell(column++);
					cell.setCellValue("金蟹奖");
					cell.setCellStyle(ftRed);
				}
				
			}
			
			// 第三个sheet,种质分
			sheet = wk.createSheet("最佳种质奖-专家");
			rows=0;
			
			RxpbScoreQuality scoreQuality;
			RxpbScoreQuality sqtemp;
			List<RxpbScoreQuality> scoreQualityListF;
			Iterator<RxpbScoreQuality> scoreQualityFIt;
			
			List<RxpbScoreQuality> scoreQualityListM;
			Iterator<RxpbScoreQuality> scoreQualityMIt;
			
			groupIt = groupList.iterator();
			gqueue=0;
			while(groupIt.hasNext()){
				gqueue++;
				group = groupIt.next();
				
				scoreQuality = new RxpbScoreQuality();
				scoreQuality.setCompetitionId(competitionId);
				scoreQuality.setGroupId(group.getGroupId());
				
				scoreQuality.setCrabSex(2);			
				scoreQualityListF = scoreDao.searchScoreQuality(scoreQuality);
				scoreQualityFIt = scoreQualityListF.iterator();
				
				scoreQuality.setCrabSex(1);			
				scoreQualityListM = scoreDao.searchScoreQuality(scoreQuality);
				scoreQualityMIt = scoreQualityListM.iterator();
				
				// 空两行
				row = sheet.createRow(rows++);
				row = sheet.createRow(rows++);

				// 定位到第三行
				row = sheet.createRow(rows);

				// 第一行:组号
				column = 1;// 第一列空着
				cell = row.createCell(column++);
				cell.setCellValue("第" + gqueue + "组");

				// 设置样式
				// 红色背景
				cell.setCellStyle(bgRed);

				// 合并单元格
				cra = new CellRangeAddress(rows, rows, (short) 1, (short) 15);
				sheet.addMergedRegion(cra);

				// 填充空列
				for (int a = 2; a <= 15; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					// 红色背景
					cell.setCellStyle(bgRed);
				}

				// 转到雌雄行
				rows++;
				
				// 第二行:雌雄
				// 合并单元格
				cra = new CellRangeAddress(rows, rows, (short) 1, (short) 7);
				sheet.addMergedRegion(cra);
				cra = new CellRangeAddress(rows, rows, (short) 9, (short) 15);
				sheet.addMergedRegion(cra);
				// 输入数据
				row = sheet.createRow(rows++);

				// 雌列
				column = 1;
				cell = row.createCell(column);
				cell.setCellValue("雌");
				cell.setCellStyle(bgBlue);
				// 填充空列
				for (int a = 2; a <= 7; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					cell.setCellStyle(bgBlue);
				}

				// 灰列
				cell = row.createCell(8);
				cell.setCellValue("");
				// 设置样式
				cell.setCellStyle(bgGrey);

				// 雄列
				column = 9;
				cell = row.createCell(column);
				cell.setCellValue("雄");
				cell.setCellStyle(bgBlue);
				// 填充空列
				for (int a = 10; a <= 15; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					cell.setCellStyle(bgBlue);
				}
				
				// 第三行标题
				row = sheet.createRow(rows++);
				column = 1;
				cell = row.createCell(column++);
				cell.setCellValue("专家");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("体色(背)");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("体色(腹)");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("额齿");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("第4侧齿");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("背部疣状突");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("得分");
				cell.setCellStyle(bgBlue);
				cell = row.createCell(column++);
				cell.setCellValue(" ");
				cell.setCellStyle(bgGrey);
				cell = row.createCell(column++);
				cell.setCellValue("专家");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("体色(背)");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("体色(腹)");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("额齿");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("第4侧齿");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("背部疣状突");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("得分");
				cell.setCellStyle(bgBlue);	
				
				// 第四至十三行:数据
				int maxSize = scoreQualityListF.size();
				if(scoreQualityListM.size()>maxSize){
					maxSize = scoreQualityListM.size();
				}
				
				for(int i=1;i<=maxSize;i++){
					column = 1;
					row = sheet.createRow(rows++);
					
					//雌蟹
					sqtemp = new RxpbScoreQuality();
					if(scoreQualityFIt.hasNext()){
						sqtemp = scoreQualityFIt.next();
						Float z = new Float(0);
						if(sqtemp.getScoreBbyzt()==null){
							sqtemp.setScoreBbyzt(z);
						}
						if(sqtemp.getScoreBts()==null){
							sqtemp.setScoreBts(z);
						}
						if(sqtemp.getScoreDscc()==null){
							sqtemp.setScoreDscc(z);
						}
						if(sqtemp.getScoreEc()==null){
							sqtemp.setScoreEc(z);
						}
						if(sqtemp.getScoreFin()==null){
							sqtemp.setScoreFin(z);
						}
						if(sqtemp.getScoreFts()==null){
							sqtemp.setScoreFts(z);
						}
					}else{
						Float z = new Float(0);
						sqtemp.setScoreBbyzt(z);
						sqtemp.setScoreBts(z);
						sqtemp.setScoreDscc(z);
						sqtemp.setScoreEc(z);
						sqtemp.setScoreFin(z);
						sqtemp.setScoreFts(z);
					}
					
					//专家
					cell = row.createCell(column++);
					cell.setCellValue("专家" + i);
					cell.setCellStyle(bgWhite);				
					//体色(背)
					cell = row.createCell(column++);
					cell.setCellValue(sqtemp.getScoreBts());
					cell.setCellStyle(bgWhite);				
					//体色(腹)
					cell = row.createCell(column++);
					cell.setCellValue(sqtemp.getScoreFts());
					cell.setCellStyle(bgWhite);				
					//额齿
					cell = row.createCell(column++);
					cell.setCellValue(sqtemp.getScoreEc());
					cell.setCellStyle(bgWhite);				
					//第4侧齿
					cell = row.createCell(column++);
					cell.setCellValue(sqtemp.getScoreDscc());
					cell.setCellStyle(bgWhite);				
					//背部疣状突
					cell = row.createCell(column++);
					cell.setCellValue(sqtemp.getScoreBbyzt());
					cell.setCellStyle(bgWhite);				
					//得分
					cell = row.createCell(column++);
					cell.setCellValue(sqtemp.getScoreFin());
					cell.setCellStyle(bgBlue);				
					//灰列
					cell = row.createCell(column++);
					cell.setCellValue(" ");
					cell.setCellStyle(bgGrey);
					
					//雄蟹
					sqtemp = new RxpbScoreQuality();
					if(scoreQualityMIt.hasNext()){
						sqtemp = scoreQualityMIt.next();
						Float z = new Float(0);
						if(sqtemp.getScoreBbyzt()==null){
							sqtemp.setScoreBbyzt(z);
						}
						if(sqtemp.getScoreBts()==null){
							sqtemp.setScoreBts(z);
						}
						if(sqtemp.getScoreDscc()==null){
							sqtemp.setScoreDscc(z);
						}
						if(sqtemp.getScoreEc()==null){
							sqtemp.setScoreEc(z);
						}
						if(sqtemp.getScoreFin()==null){
							sqtemp.setScoreFin(z);
						}
						if(sqtemp.getScoreFts()==null){
							sqtemp.setScoreFts(z);
						}
					}else{
						Float z = new Float(0);
						sqtemp.setScoreBbyzt(z);
						sqtemp.setScoreBts(z);
						sqtemp.setScoreDscc(z);
						sqtemp.setScoreEc(z);
						sqtemp.setScoreFin(z);
						sqtemp.setScoreFts(z);
					}		
					
					//专家
					cell = row.createCell(column++);
					cell.setCellValue("专家" + i);
					cell.setCellStyle(bgWhite);				
					//体色(背)
					cell = row.createCell(column++);
					cell.setCellValue(sqtemp.getScoreBts());
					cell.setCellStyle(bgWhite);				
					//体色(腹)
					cell = row.createCell(column++);
					cell.setCellValue(sqtemp.getScoreFts());
					cell.setCellStyle(bgWhite);				
					//额齿
					cell = row.createCell(column++);
					cell.setCellValue(sqtemp.getScoreEc());
					cell.setCellStyle(bgWhite);				
					//第4侧齿
					cell = row.createCell(column++);
					cell.setCellValue(sqtemp.getScoreDscc());
					cell.setCellStyle(bgWhite);				
					//背部疣状突
					cell = row.createCell(column++);
					cell.setCellValue(sqtemp.getScoreBbyzt());
					cell.setCellStyle(bgWhite);				
					//得分
					cell = row.createCell(column++);
					cell.setCellValue(sqtemp.getScoreFin());
					cell.setCellStyle(bgBlue);	
										
				}
				
				// 第十四行:平均分
				// 合并单元格
				cra = new CellRangeAddress(rows, rows, (short) 1, (short) 7);
				sheet.addMergedRegion(cra);
				cra = new CellRangeAddress(rows, rows, (short) 9, (short) 15);
				sheet.addMergedRegion(cra);
				
				// 数据
				row = sheet.createRow(rows++);
				column = 1;
				cell = row.createCell(column);
				cell.setCellValue("去掉一个最低分，去掉一个最高分平均:"
						+ group.getQualityScoreF());
				cell.setCellStyle(bgYellow);
				// 填充空列
				for (int a = 2; a <= 7; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					cell.setCellStyle(bgYellow);
				}

				// 灰列
				cell = row.createCell(8);
				cell.setCellValue("");
				// 设置样式
				cell.setCellStyle(bgGrey);

				column = 9;
				cell = row.createCell(column);
				cell.setCellValue("去掉一个最低分，去掉一个最高分平均:"
						+ group.getQualityScoreM());
				cell.setCellStyle(bgYellow);

				// 填充空列
				for (int a = 10; a <= 15; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					cell.setCellStyle(bgYellow);
				}
				
				// 第十七行:雌雄平均得分
				// 合并单元格
				cra = new CellRangeAddress(rows, rows, (short) 1, (short) 15);
				sheet.addMergedRegion(cra);
				// 数据
				
				row = sheet.createRow(rows++);
				column = 1;
				cell = row.createCell(column);
				cell.setCellValue("雌雄平均得分:"
						+ (((group.getQualityScoreF()+group.getQualityScoreM()) / 2)));
				cell.setCellStyle(bgOrange);

				// 填充空列
				for (int a = 2; a <= 11; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					// 红色背景
					cell.setCellStyle(bgOrange);				
				}
			}		
			
			// 第四个sheet,种质分排名
			sheet = wk.createSheet("最佳种质奖-排名");
			// 设置列宽
			sheet.setColumnWidth(0, 10 * 256);
			sheet.setColumnWidth(1, 70 * 256);
			sheet.setColumnWidth(2, 20 * 256);
			sheet.setColumnWidth(3, 10 * 256);
			rows = 0;

			// 第一行:标题
			row = sheet.createRow(rows++);
			column = 0;
			cell = row.createCell(column++);
			cell.setCellValue("组号");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(column++);
			cell.setCellValue("单位名称");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(column++);
			cell.setCellValue("种质专家分");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(column++);
			cell.setCellValue("排名");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(++column);
			cell.setCellValue("前三名获得最佳种质奖");
			cell.setCellStyle(ftRed);

			scoreFin = new RxpbScoreFin();
			scoreFinList.clear();
			scoreFin.setCompetitionId(competitionId);
			scoreFinList = resultDao.getQualitySort(scoreFin);
			scoreFinIt = scoreFinList.iterator();
			// 排名
			order = 0;

			while (scoreFinIt.hasNext()) {
				order++;
				scoreFin=scoreFinIt.next();

				row = sheet.createRow(rows++);
				column = 0;
				// 得到组号
				gqueue = mySearch.findGroupQueue(groupList, scoreFin
						.getGroupId());
				// 组号
				cell = row.createCell(column++);
				cell.setCellValue("第" + gqueue + "组");
				cell.setCellStyle(bgBlue);
				// 参选单位
				cell = row.createCell(column++);
				cell.setCellValue(scoreFin.getCompanyName());
				cell.setCellStyle(bgWhite);
				// 得分
				cell = row.createCell(column++);
				cell.setCellValue(scoreFin.getQualityScore());
				cell.setCellStyle(bgWhite);
				// 排名
				cell = row.createCell(column++);
				cell.setCellValue(order);
				cell.setCellStyle(bgYellow);

				if (order <= 3) {
					cell = row.createCell(column++);
					cell.setCellValue("最佳种质奖");
					cell.setCellStyle(ftRed);
				}

			}
			
			// 第五个sheet,口感分
			sheet = wk.createSheet("最佳口感奖-专家");
			rows = 0;
			
			RxpbScoreTaste scoreTaste;
			RxpbScoreTaste sttemp;
			List<RxpbScoreTaste> scoreTasteListF;
			Iterator<RxpbScoreTaste> scoreTasteFIt;
			
			List<RxpbScoreTaste> scoreTasteListM;
			Iterator<RxpbScoreTaste> scoreTasteMIt;
			
			groupIt = groupList.iterator();
			gqueue=0;
			while (groupIt.hasNext()) {
				gqueue++;
				group = groupIt.next();

				scoreTaste = new RxpbScoreTaste();
				scoreTaste.setCompetitionId(competitionId);
				scoreTaste.setGroupId(group.getGroupId());

				scoreTaste.setCrabSex(2);
				scoreTasteListF = scoreDao.searchScoreTaste(scoreTaste);
				scoreTasteFIt = scoreTasteListF.iterator();

				scoreTaste.setCrabSex(1);
				scoreTasteListM = scoreDao.searchScoreTaste(scoreTaste);
				scoreTasteMIt = scoreTasteListM.iterator();

				// 空两行
				row = sheet.createRow(rows++);
				row = sheet.createRow(rows++);

				// 定位到第三行
				row = sheet.createRow(rows);

				// 第一行:组号
				column = 1;// 第一列空着
				cell = row.createCell(column++);
				cell.setCellValue("第" + gqueue + "组");

				// 设置样式
				// 红色背景
				cell.setCellStyle(bgRed);

				// 合并单元格
				cra = new CellRangeAddress(rows, rows, (short) 1, (short) 19);
				sheet.addMergedRegion(cra);

				// 填充空列
				for (int a = 2; a <= 19; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					// 红色背景
					cell.setCellStyle(bgRed);
				}

				// 转到雌雄行
				rows++;

				// 第二行:雌雄
				// 合并单元格
				cra = new CellRangeAddress(rows, rows, (short) 1, (short) 9);
				sheet.addMergedRegion(cra);
				cra = new CellRangeAddress(rows, rows, (short) 11, (short) 19);
				sheet.addMergedRegion(cra);
				// 输入数据
				row = sheet.createRow(rows++);

				// 雌列
				column = 1;
				cell = row.createCell(column);
				cell.setCellValue("雌");
				cell.setCellStyle(bgBlue);
				// 填充空列
				for (int a = 2; a <= 9; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					cell.setCellStyle(bgBlue);
				}

				// 灰列
				cell = row.createCell(10);
				cell.setCellValue("");
				// 设置样式
				cell.setCellStyle(bgGrey);

				// 雄列
				column = 11;
				cell = row.createCell(column);
				cell.setCellValue("雄");
				cell.setCellStyle(bgBlue);
				// 填充空列
				for (int a = 12; a <= 19; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					cell.setCellStyle(bgBlue);
				}

				// 第三行标题
				row = sheet.createRow(rows++);
				column = 1;
				cell = row.createCell(column++);
				cell.setCellValue("专家");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("蟹盖颜色");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("鳃颜色");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("膏、黄颜色");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("腥味、香味");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("膏、黄");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("腹部肌肉");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("第二、三步足肌肉");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("得分");
				cell.setCellStyle(bgBlue);
				cell = row.createCell(column++);
				cell.setCellValue(" ");
				cell.setCellStyle(bgGrey);
				cell = row.createCell(column++);
				cell.setCellValue("专家");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("蟹盖颜色");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("鳃颜色");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("膏、黄颜色");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("腥味、香味");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("膏、黄");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("腹部肌肉");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("第二、三步足肌肉");
				cell.setCellStyle(bgWhite);
				cell = row.createCell(column++);
				cell.setCellValue("得分");
				cell.setCellStyle(bgBlue);

				// 第四至十三行:数据
				int maxSize = scoreTasteListF.size();
				if (scoreTasteListM.size() > maxSize) {
					maxSize = scoreTasteListM.size();
				}

				for (int i = 1; i <= maxSize; i++) {
					column = 1;
					row = sheet.createRow(rows++);

					// 雌蟹
					sttemp = new RxpbScoreTaste();
					if (scoreTasteFIt.hasNext()) {
						sttemp = scoreTasteFIt.next();
						Float z = new Float(0);
						if (sttemp.getScoreBzjr() == null) {
							sttemp.setScoreBzjr(z);
						}
						if (sttemp.getScoreFbjr() == null) {
							sttemp.setScoreFbjr(z);
						}
						if (sttemp.getScoreFin() == null) {
							sttemp.setScoreFin(z);
						}
						if (sttemp.getScoreGh() == null) {
							sttemp.setScoreGh(z);
						}
						if (sttemp.getScoreGhys() == null) {
							sttemp.setScoreGhys(z);
						}
						if (sttemp.getScoreSys() == null) {
							sttemp.setScoreSys(z);
						}
						if (sttemp.getScoreXwxw() == null) {
							sttemp.setScoreXwxw(z);
						}
						if (sttemp.getScoreYgys() == null) {
							sttemp.setScoreYgys(z);
						}
					} else {
						Float z = new Float(0);
						sttemp.setScoreBzjr(z);
						sttemp.setScoreFbjr(z);
						sttemp.setScoreFin(z);
						sttemp.setScoreGh(z);
						sttemp.setScoreGhys(z);
						sttemp.setScoreSys(z);
						sttemp.setScoreXwxw(z);
						sttemp.setScoreYgys(z);
					}

					// 专家
					cell = row.createCell(column++);
					cell.setCellValue("专家" + i);
					cell.setCellStyle(bgWhite);
					// 蟹盖颜色
					cell = row.createCell(column++);
					cell.setCellValue(sttemp.getScoreYgys());
					cell.setCellStyle(bgWhite);
					// 鳃颜色
					cell = row.createCell(column++);
					cell.setCellValue(sttemp.getScoreSys());
					cell.setCellStyle(bgWhite);
					// 膏、黄颜色
					cell = row.createCell(column++);
					cell.setCellValue(sttemp.getScoreGhys());
					cell.setCellStyle(bgWhite);
					// 腥味、香味
					cell = row.createCell(column++);
					cell.setCellValue(sttemp.getScoreXwxw());
					cell.setCellStyle(bgWhite);
					// 膏、黄
					cell = row.createCell(column++);
					cell.setCellValue(sttemp.getScoreGh());
					cell.setCellStyle(bgWhite);
					// 腹部肌肉
					cell = row.createCell(column++);
					cell.setCellValue(sttemp.getScoreFbjr());
					cell.setCellStyle(bgWhite);
					// 第二、三步足肌肉
					cell = row.createCell(column++);
					cell.setCellValue(sttemp.getScoreBzjr());
					cell.setCellStyle(bgWhite);
					// 得分
					cell = row.createCell(column++);
					cell.setCellValue(sttemp.getScoreFin());
					cell.setCellStyle(bgBlue);

					// 灰列
					cell = row.createCell(column++);
					cell.setCellValue(" ");
					cell.setCellStyle(bgGrey);

					// 雄蟹
					sttemp = new RxpbScoreTaste();
					if (scoreTasteMIt.hasNext()) {
						sttemp = scoreTasteMIt.next();
						Float z = new Float(0);
						if (sttemp.getScoreBzjr() == null) {
							sttemp.setScoreBzjr(z);
						}
						if (sttemp.getScoreFbjr() == null) {
							sttemp.setScoreFbjr(z);
						}
						if (sttemp.getScoreFin() == null) {
							sttemp.setScoreFin(z);
						}
						if (sttemp.getScoreGh() == null) {
							sttemp.setScoreGh(z);
						}
						if (sttemp.getScoreGhys() == null) {
							sttemp.setScoreGhys(z);
						}
						if (sttemp.getScoreSys() == null) {
							sttemp.setScoreSys(z);
						}
						if (sttemp.getScoreXwxw() == null) {
							sttemp.setScoreXwxw(z);
						}
						if (sttemp.getScoreYgys() == null) {
							sttemp.setScoreYgys(z);
						}
					} else {
						Float z = new Float(0);
						sttemp.setScoreBzjr(z);
						sttemp.setScoreFbjr(z);
						sttemp.setScoreFin(z);
						sttemp.setScoreGh(z);
						sttemp.setScoreGhys(z);
						sttemp.setScoreSys(z);
						sttemp.setScoreXwxw(z);
						sttemp.setScoreYgys(z);
					}

					// 专家
					cell = row.createCell(column++);
					cell.setCellValue("专家" + i);
					cell.setCellStyle(bgWhite);
					// 蟹盖颜色
					cell = row.createCell(column++);
					cell.setCellValue(sttemp.getScoreYgys());
					cell.setCellStyle(bgWhite);
					// 鳃颜色
					cell = row.createCell(column++);
					cell.setCellValue(sttemp.getScoreSys());
					cell.setCellStyle(bgWhite);
					// 膏、黄颜色
					cell = row.createCell(column++);
					cell.setCellValue(sttemp.getScoreGhys());
					cell.setCellStyle(bgWhite);
					// 腥味、香味
					cell = row.createCell(column++);
					cell.setCellValue(sttemp.getScoreXwxw());
					cell.setCellStyle(bgWhite);
					// 膏、黄
					cell = row.createCell(column++);
					cell.setCellValue(sttemp.getScoreGh());
					cell.setCellStyle(bgWhite);
					// 腹部肌肉
					cell = row.createCell(column++);
					cell.setCellValue(sttemp.getScoreFbjr());
					cell.setCellStyle(bgWhite);
					// 第二、三步足肌肉
					cell = row.createCell(column++);
					cell.setCellValue(sttemp.getScoreBzjr());
					cell.setCellStyle(bgWhite);
					// 得分
					cell = row.createCell(column++);
					cell.setCellValue(sttemp.getScoreFin());
					cell.setCellStyle(bgBlue);

					

				}
				// 第十四行:平均分
				// 合并单元格
				cra = new CellRangeAddress(rows, rows, (short) 1, (short) 9);
				sheet.addMergedRegion(cra);
				cra = new CellRangeAddress(rows, rows, (short) 11,
						(short) 19);
				sheet.addMergedRegion(cra);

				// 数据
				row = sheet.createRow(rows++);
				column = 1;
				cell = row.createCell(column);
				cell.setCellValue("去掉一个最低分，去掉一个最高分平均:"
						+ group.getTasteScoreF());
				cell.setCellStyle(bgYellow);
				// 填充空列
				for (int a = 2; a <= 9; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					cell.setCellStyle(bgYellow);
				}

				// 灰列
				cell = row.createCell(10);
				cell.setCellValue("");
				// 设置样式
				cell.setCellStyle(bgGrey);

				column += 8;
				cell = row.createCell(column);
				cell.setCellValue("去掉一个最低分，去掉一个最高分平均:"
						+ group.getTasteScoreF());
				cell.setCellStyle(bgYellow);

				// 填充空列
				for (int a = 12; a <= 19; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					cell.setCellStyle(bgYellow);
				}

				// 第十七行:雌雄平均得分
				// 合并单元格
				cra = new CellRangeAddress(rows, rows, (short) 1,
						(short) 19);
				sheet.addMergedRegion(cra);
				// 数据

				row = sheet.createRow(rows++);
				column = 1;
				cell = row.createCell(column);
				cell.setCellValue("雌雄平均得分:"
						+ (((group.getTasteScoreF() + group
								.getTasteScoreM()) / 2)));
				cell.setCellStyle(bgOrange);

				// 填充空列
				for (int a = 2; a <= 19; a++) {
					cell = row.createCell(a);
					cell.setCellValue("");
					// 设置样式
					// 红色背景
					cell.setCellStyle(bgOrange);
				}
			}

			// 第六个sheet,口感分排名
			sheet = wk.createSheet("最佳口感奖-排名");
			// 设置列宽
			sheet.setColumnWidth(0, 10 * 256);
			sheet.setColumnWidth(1, 70 * 256);
			sheet.setColumnWidth(2, 20 * 256);
			sheet.setColumnWidth(3, 10 * 256);
			rows = 0;

			// 第一行:标题
			row = sheet.createRow(rows++);
			column = 0;
			cell = row.createCell(column++);
			cell.setCellValue("组号");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(column++);
			cell.setCellValue("单位名称");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(column++);
			cell.setCellValue("口感专家分");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(column++);
			cell.setCellValue("排名");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(++column);
			cell.setCellValue("15组，其中金蟹奖10组，最佳口感奖为前三名");
			cell.setCellStyle(ftRed);
			
			scoreFin = new RxpbScoreFin();
			scoreFin.setCompetitionId(competitionId);
			scoreFinList = resultDao.getTasteSort(scoreFin);
			scoreFinIt = scoreFinList.iterator();
			// 排名
			order = 0;

			while (scoreFinIt.hasNext()) {
				order++;
				scoreFin=scoreFinIt.next();

				row = sheet.createRow(rows++);
				column = 0;
				// 得到组号
				gqueue = mySearch.findGroupQueue(groupList, scoreFin
						.getGroupId());
				// 组号
				cell = row.createCell(column++);
				cell.setCellValue("第" + gqueue + "组");
				cell.setCellStyle(bgBlue);
				// 参选单位
				cell = row.createCell(column++);
				cell.setCellValue(scoreFin.getCompanyName());
				cell.setCellStyle(bgWhite);
				// 得分
				cell = row.createCell(column++);
				cell.setCellValue(scoreFin.getTasteScore());
				cell.setCellStyle(bgWhite);
				// 排名
				cell = row.createCell(column++);
				cell.setCellValue(order);
				cell.setCellStyle(bgYellow);

				if (order <= 15) {
					if(order<=3){
						cell = row.createCell(column++);
						cell.setCellValue("最佳口感奖");
						cell.setCellStyle(ftRed);
					}else{
						cell = row.createCell(column++);
						cell.setCellValue("金蟹奖");
						cell.setCellStyle(ftRed);
					}				
				}

			}
			
			//第七个sheet,蟹后
			sheet = wk.createSheet("蟹后");
			// 设置列宽
			sheet.setColumnWidth(0, 10 * 256);//名次
			sheet.setColumnWidth(1, 10 * 256);//组号
			sheet.setColumnWidth(2, 70 * 256);//参选单位
			sheet.setColumnWidth(3, 20 * 256);//壳长
			sheet.setColumnWidth(4, 20 * 256);//体重
			sheet.setColumnWidth(5, 20 * 256);//肥满度
			rows = 0;

			// 第一行:标题
			row = sheet.createRow(rows++);
			column = 0;
			cell = row.createCell(column++);
			cell.setCellValue("名次");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(column++);
			cell.setCellValue("组号");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(column++);
			cell.setCellValue("单位名称");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(column++);
			cell.setCellValue("壳长");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(column++);
			cell.setCellValue("体重");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(column++);
			cell.setCellValue("肥满度");
			cell.setCellStyle(bgOrange);
			
			crab = new RxpbCrabInfo();
			crab.setCrabSex(2);
			crab.setCompetitionId(competitionId);
			
			List <RxpbCrabInfo> crabList = crabDao.searchTop10(crab);
			Iterator<RxpbCrabInfo> crabListIt = crabList.iterator();
			
			groupIt = groupList.iterator();
			gqueue=0;
			order = 0;
			while (crabListIt.hasNext()){
				crab = new RxpbCrabInfo();
				company = new RxpbCompanyInfo();
				crab = crabListIt.next();
				row = sheet.createRow(rows++);
				column = 0;
				order++;
				gqueue = mySearch.findGroupQueue(groupList, crab.getGroupId());
				company = mySearch.findCompanyByGroupId(groupList,companyList,crab.getGroupId());
				
				cell = row.createCell(column++);
				cell.setCellValue(order);
				cell.setCellStyle(bgBlue);
				cell = row.createCell(column++);
				cell.setCellValue("第" + gqueue + "组");
				cell.setCellStyle(bgBlue);
				cell = row.createCell(column++);
				cell.setCellValue(company.getCompanyName());
				cell.setCellStyle(bgBlue);
				cell = row.createCell(column++);
				cell.setCellValue(crab.getCrabLength());
				cell.setCellStyle(bgBlue);
				cell = row.createCell(column++);
				cell.setCellValue(crab.getCrabWeight());
				cell.setCellStyle(bgBlue);
				cell = row.createCell(column++);
				cell.setCellValue(crab.getCrabFatness());
				cell.setCellStyle(bgBlue);	
				
			}
			
			
			//第八个sheet,蟹王
			sheet = wk.createSheet("蟹王");
			// 设置列宽
			sheet.setColumnWidth(0, 10 * 256);//名次
			sheet.setColumnWidth(1, 10 * 256);//组号
			sheet.setColumnWidth(2, 70 * 256);//参选单位
			sheet.setColumnWidth(3, 20 * 256);//壳长
			sheet.setColumnWidth(4, 20 * 256);//体重
			sheet.setColumnWidth(5, 20 * 256);//肥满度
			rows = 0;

			// 第一行:标题
			row = sheet.createRow(rows++);
			column = 0;
			cell = row.createCell(column++);
			cell.setCellValue("名次");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(column++);
			cell.setCellValue("组号");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(column++);
			cell.setCellValue("单位名称");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(column++);
			cell.setCellValue("壳长");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(column++);
			cell.setCellValue("体重");
			cell.setCellStyle(bgOrange);
			cell = row.createCell(column++);
			cell.setCellValue("肥满度");
			cell.setCellStyle(bgOrange);
			
			crab = new RxpbCrabInfo();
			crab.setCrabSex(1);
			crab.setCompetitionId(competitionId);
			
			crabList = crabDao.searchTop10(crab);
			crabListIt = crabList.iterator();
			
			groupIt = groupList.iterator();
			gqueue=0;
			order = 0;
			while (crabListIt.hasNext()){
				crab = new RxpbCrabInfo();
				company = new RxpbCompanyInfo();
				crab = crabListIt.next();
				row = sheet.createRow(rows++);
				column = 0;
				order++;
				gqueue = mySearch.findGroupQueue(groupList, crab.getGroupId());
				company = mySearch.findCompanyByGroupId(groupList,companyList,crab.getGroupId());
				
				cell = row.createCell(column++);
				cell.setCellValue(order);
				cell.setCellStyle(bgBlue);
				cell = row.createCell(column++);
				cell.setCellValue("第" + gqueue + "组");
				cell.setCellStyle(bgBlue);
				cell = row.createCell(column++);
				cell.setCellValue(company.getCompanyName());
				cell.setCellStyle(bgBlue);
				cell = row.createCell(column++);
				cell.setCellValue(crab.getCrabLength());
				cell.setCellStyle(bgBlue);
				cell = row.createCell(column++);
				cell.setCellValue(crab.getCrabWeight());
				cell.setCellStyle(bgBlue);
				cell = row.createCell(column++);
				cell.setCellValue(crab.getCrabFatness());
				cell.setCellStyle(bgBlue);	
				
			}
			
			
			
			// 第九个sheet,参选单位表
			sheet = wk.createSheet("参选单位");
			// 设置列宽
			sheet.setColumnWidth(0, 10 * 256);
			sheet.setColumnWidth(1, 70 * 256);
			rows = 0;

			// 第一行:标题
			row = sheet.createRow(rows++);
			column = 0;
			cell = row.createCell(column++);
			cell.setCellValue("组号");
			cell.setCellStyle(bgBlue);
			cell = row.createCell(column++);
			cell.setCellValue("单位名称");
			cell.setCellStyle(bgBlue);
			
			scoreFin = new RxpbScoreFin();
			scoreFin.setCompetitionId(competitionId);
			
			//获取组-参选单位对应表
			List<RxpbScoreFin> gcList = resultDao.getGroupSort(scoreFin);
			Iterator<RxpbScoreFin> gcListIt = gcList.iterator();
			RxpbScoreFin gctemp;
			gqueue=0;
			while(gcListIt.hasNext()){
				gqueue++;
				gctemp = gcListIt.next();
				row = sheet.createRow(rows++);
				column = 0;
				cell = row.createCell(column++);
				cell.setCellValue("第" + gqueue + "组");
				cell.setCellStyle(bgBlue);
				cell = row.createCell(column++);
				cell.setCellValue(gctemp.getCompanyName());
				cell.setCellStyle(bgWhite);
			}
			
		}catch (Exception e) {
			e.printStackTrace();
			return  wk ;
			//return null;
		}
			
		return wk;
	}

	/*
	@Override
	public HSSFWorkbook createPreviousExcel() {
		// TODO Auto-generated method stub
		return null;
	}
	*/

	@Override
	public List<RxpbScoreFin> getFatnessSort() {
		RxpbScoreFin scoreFin = new RxpbScoreFin();
		int competitionId = competitionDao.getHibernateTemplate().get(RxpbCompetitionConfig.class, 1).getCompetitionId();
		scoreFin.setCompetitionId(competitionId);
		List<RxpbScoreFin> scoreFinList = resultDao.getFatnessSort(scoreFin);

		return scoreFinList;
	}

	@Override
	public List<RxpbScoreFin> getQualitySort() {
		RxpbScoreFin scoreFin = new RxpbScoreFin();
		int competitionId = competitionDao.getHibernateTemplate().get(RxpbCompetitionConfig.class, 1).getCompetitionId();
		scoreFin.setCompetitionId(competitionId);
		List<RxpbScoreFin> scoreFinList = resultDao.getQualitySort(scoreFin);

		return scoreFinList;
		
	}

	@Override
	public List<RxpbScoreFin> getTasteSort() {
		RxpbScoreFin scoreFin = new RxpbScoreFin();
		int competitionId = competitionDao.getHibernateTemplate().get(RxpbCompetitionConfig.class, 1).getCompetitionId();
		scoreFin.setCompetitionId(competitionId);
		List<RxpbScoreFin> scoreFinList = resultDao.getTasteSort(scoreFin);

		return scoreFinList;
	}

	@Override
	public List<RxpbCrabInfo> getKingSort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RxpbCrabInfo> getQueenSort() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<RxpbCrabInfo> getFatnessScoreByCompany(RxpbCompanyInfo company,int groupId,int crabSex) {
		RxpbGroupInfo group = new RxpbGroupInfo();
		//group.setCompanyId(company.getCompanyId());
		//List<RxpbGroupInfo> glist = groupDao.search(group);
		//if(glist.size()>0){
			//group = glist.get(0);
			RxpbCrabInfo crab = new RxpbCrabInfo();			
			crab.setGroupId(groupId);//group.getGroupId());
			crab.setCrabSex(new Integer(crabSex));
			List <RxpbCrabInfo> clist = crabDao.search(crab);
			if(!clist.isEmpty()){
				return clist;
			}
		//}
		return null;
	}

	@Override
	public List<RxpbScoreQuality> getQualityScoreByCompany(RxpbCompanyInfo company,int groupId,int crabSex) {
		RxpbGroupInfo group = new RxpbGroupInfo();
		//group.setCompanyId(company.getCompanyId());
		//List<RxpbGroupInfo> glist = groupDao.search(group);
		//if(glist.size()>0){
			//group = glist.get(0);
			RxpbScoreQuality scoreQuality = new RxpbScoreQuality();			
			if(groupId!=0){
				scoreQuality.setGroupId(groupId);//group.getGroupId());
			}			
			scoreQuality.setCrabSex(new Integer(crabSex));
			List <RxpbScoreQuality> cqlist = scoreDao.searchScoreQuality(scoreQuality);
			if(!cqlist.isEmpty()){
				return cqlist;
			}
		//}
		return null;
	}

	@Override
	public List<RxpbScoreTaste> getTasteScoreByCompany(RxpbCompanyInfo company,int groupId,int crabSex) {
		RxpbGroupInfo group = new RxpbGroupInfo();
		//group.setCompanyId(company.getCompanyId());
		//List<RxpbGroupInfo> glist = groupDao.search(group);
		//if(glist.size()>0){
			//group = glist.get(0);
			RxpbScoreTaste scoreTaste = new RxpbScoreTaste();			
			if(groupId!=0){
				scoreTaste.setGroupId(groupId);//group.getGroupId());
			}
			scoreTaste.setCrabSex(new Integer(crabSex));
			List <RxpbScoreTaste> ctlist = scoreDao.searchScoreTaste(scoreTaste);
			if(!ctlist.isEmpty()){
				return ctlist;
			}
		//}
		return null;
	}

}
