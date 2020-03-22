package cn.mmc8102.blog.util;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter@Setter@ToString
public class JSONResult {
	private boolean success = true;
	private String msg;
	
	public JSONResult() {}
	
	public JSONResult(String msg) {
		this.msg = msg;
	}

	public JSONResult(Boolean success, String msg) {
		this.success = success;
		this.msg = msg;
	}
}
