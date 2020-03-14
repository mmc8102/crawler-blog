package cn.mmc8102.crawler.query;

import lombok.Getter;
import lombok.Setter;

/**
 * 所有高级查询对象的共同代码封装
 * @author 16282
 */
@Getter@Setter
public class QueryObject{
	private Integer currentPage = 1;
	private Integer pageSize = 5;

	public int getStart() {
		if (currentPage == null) {
			currentPage = 1;
		}
		return (currentPage - 1) * pageSize;
	}
}
