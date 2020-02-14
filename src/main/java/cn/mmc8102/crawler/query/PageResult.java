package cn.mmc8102.crawler.query;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
@Getter@Setter
public class PageResult {
	private Integer total;
	private List<?> rows;
	
	public PageResult(int total, List<?> rows) {
		this.total = total;
		this.rows = rows;
	}

}
