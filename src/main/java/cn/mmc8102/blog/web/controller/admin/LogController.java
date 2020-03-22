package cn.mmc8102.blog.web.controller.admin;

import cn.mmc8102.blog.query.LogQueryObject;
import cn.mmc8102.blog.query.PageResult;
import cn.mmc8102.blog.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/admin")
public class LogController {
	@Autowired
	private ILogService logService;
	
	@RequestMapping("/log")
	public String index(){
		return "admin/log";
	}
	
	@RequestMapping("/log/list")
	@ResponseBody
	public PageResult list(LogQueryObject qo){
		return logService.query(qo);
	}
}
