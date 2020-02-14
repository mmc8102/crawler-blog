package cn.mmc8102.crawler.query;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class QueryObject{
	private Integer page;
	private Integer rows;
	
	public Integer getStart(){
		return (page-1)*rows;
	}
	

	protected String empty2null(String str) {
		return hasLength(str) ? str : null;
	}

	private boolean hasLength(String str) {
		return str != null && !"".equals(str.trim());
	}
}
