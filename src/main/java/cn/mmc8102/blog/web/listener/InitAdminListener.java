package cn.mmc8102.blog.web.listener;

import cn.mmc8102.blog.service.ILogininfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;


/**
 * 初始化超级管理员的监听器
 * 1,在spring中,实现了ApplicationListener的类就可以作为spring的监听器,来监听spring中的特殊事件
 * 2,在spring中,ApplicationEvent这个类相当于所有事件,如果我们的监听器实现ApplicationListener<ApplicationEvent>
 * ,相当于我们这个监听器监听的是spring中所有的消息
 * 3,现在我只想监听spring容器启动完成的事件,只需要监听ContextRefreshedEvent事件就可以了
 * @author 16282
 *
 */
@Component
public class InitAdminListener implements ApplicationListener<ContextRefreshedEvent>{

	@Autowired
	private ILogininfoService logininfoService;
	
	/**
	 * 这个方法就是监听到指定事件后,要做的事情;ApplicationEvent就是这次监听到的对象
	 */
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		logininfoService.initAdmin();
	}

}
