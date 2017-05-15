package com.rxpb.tool;

import java.util.Iterator;
import java.util.List;

import com.rxpb.entity.RxpbCrabInfo;
import com.rxpb.entity.RxpbScoreQuality;
import com.rxpb.entity.RxpbScoreTaste;

public class MyMath {
	
	public float getFatnessAvg(List<RxpbCrabInfo> list){
		if(list.size()>0){
			Iterator<RxpbCrabInfo> it = list.iterator();
			float sum=0;
			Float temp;
			while (it.hasNext()){
				temp = it.next().getCrabFatness();
				if(temp!=null){
					sum+=temp;	
				}				
			}
			return sum/list.size(); 
		}		
		return 0;
	}
	
	public float getWeightAvg(List<RxpbCrabInfo> list){
		if(list.size()>0){
			Iterator<RxpbCrabInfo> it = list.iterator();
			float sum=0;
			Float temp;
			while (it.hasNext()){
				temp = it.next().getCrabWeight();
				if(temp!=null){
					sum+=temp;	
				}				
			}		
			
			return sum/list.size(); 
		}		
		return 0;
	}
	
	public float getFatnesssSd(List<RxpbCrabInfo> list){
		float avg = this.getFatnessAvg(list);
		if(list.size()>0){
			Iterator<RxpbCrabInfo> it = list.iterator();
			float sum=0;
			Float temp;
			
			while (it.hasNext()){
				temp = it.next().getCrabFatness();
				if(temp!=null){
					sum += Math.sqrt((temp -avg) * (temp -avg));
				}				
			}
			return sum/(list.size()-1); 
		}		
		return 0;
	}
	
	public float getWeightSd(List<RxpbCrabInfo> list){
		float avg = this.getWeightAvg(list);
		if(list.size()>0){
			Iterator<RxpbCrabInfo> it = list.iterator();
			float sum=0;
			Float temp;
			
			while (it.hasNext()){
				temp  =it.next().getCrabWeight();
				if(temp!=null){
					sum += Math.sqrt((temp -avg) * (temp -avg));
				}
			}
			return sum/(list.size()-1); 
		}		
		return 0;
	}
	
	public float getQualityMax(List<RxpbScoreQuality> list){
		RxpbScoreQuality scoreQuality = new RxpbScoreQuality();
		if(list.size()>0){
			Iterator<RxpbScoreQuality> it = list.iterator();
			scoreQuality = it.next();
			Float temp;
			float max=0;
			temp = scoreQuality.getScoreFin();
			
			if(temp!=null){
				max=temp;
			}
			while (it.hasNext()){
				temp = it.next().getScoreFin();
				if(temp!=null&&temp>max){
					max=temp;
				}
			}
			return max; 
		}		
		return 0;
	}
	
	public float getQualityMin(List<RxpbScoreQuality> list){
		RxpbScoreQuality scoreQuality = new RxpbScoreQuality();
		if(list.size()>0){
			Iterator<RxpbScoreQuality> it = list.iterator();
			scoreQuality = it.next();
			Float temp;
			float min=0;
			temp = scoreQuality.getScoreFin();
			
			if(temp!=null){
				min=temp;
			}
			while (it.hasNext()){
				temp = it.next().getScoreFin();
				if(temp!=null&&temp<min){
					min=temp;
				}
			}
			return min; 
		}		
		return 0;
	}
	
	//去掉一个最高分，去掉一个最低分
	public float getQualityAvg(List<RxpbScoreQuality> list){
		if(list.size()>2){
			Iterator<RxpbScoreQuality> it = list.iterator();
			float sum=0;
			Float temp;
			while (it.hasNext()){
				temp=it.next().getScoreFin();
				if(temp!=null){
					sum+=temp;
				}				
			}
			return (sum-getQualityMax(list)-getQualityMin(list))/(list.size()-2); 
		}		
		return 0;
	}
	
	public float getTasteMax(List<RxpbScoreTaste> list){
		RxpbScoreTaste scoreTaste = new RxpbScoreTaste();
		if(list.size()>0){
			Iterator<RxpbScoreTaste> it = list.iterator();
			scoreTaste = it.next();
			Float temp;
			float max=0;
			temp = scoreTaste.getScoreFin();
			
			if(temp!=null){
				max=temp;
			}
			while (it.hasNext()){
				temp = it.next().getScoreFin();
				if(temp!=null&&temp>max){
					max=temp;
				}
			}
			return max; 
		}		
		return 0;
	}
	
	public float getTasteMin(List<RxpbScoreTaste> list){
		RxpbScoreTaste scoreTaste = new RxpbScoreTaste();
		if(list.size()>0){
			Iterator<RxpbScoreTaste> it = list.iterator();
			scoreTaste = it.next();
			Float temp;
			float min=0;
			temp = scoreTaste.getScoreFin();
			
			if(temp!=null){
				min=temp;
			}
			while (it.hasNext()){
				temp = it.next().getScoreFin();
				if(temp!=null&&temp<min){
					min=temp;
				}
			}
			return min; 
		}		
		return 0;
	}
	
	//去掉一个最高分，去掉一个最低分
	public float getTasteAvg(List<RxpbScoreTaste> list){
		if(list.size()>2){
			Iterator<RxpbScoreTaste> it = list.iterator();
			float sum=0;
			Float temp;
			while (it.hasNext()){
				temp=it.next().getScoreFin();
				if(temp!=null){
					sum+=temp;
				}				
			}
			return (sum-getTasteMax(list)-getTasteMin(list))/(list.size()-2); 
		}		
		return 0;
	}
	
	
	public float getFatness(RxpbCrabInfo crab, float varFatness){
		float res = 0;
		float weight = crab.getCrabWeight();
		float lenght = crab.getCrabLength();
		if(lenght!=0){
			res = (varFatness*weight*100)/(lenght*lenght*lenght);
		}
		
		return res;
	}
	
}
