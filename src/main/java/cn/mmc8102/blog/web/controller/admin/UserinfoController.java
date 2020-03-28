package cn.mmc8102.blog.web.controller.admin;

import cn.mmc8102.blog.domain.Userinfo;
import cn.mmc8102.blog.query.PageResult;
import cn.mmc8102.blog.service.IUserinfoService;
import cn.mmc8102.blog.util.JSONResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author wangli28
 * @Date: 2020/3/16 21:46
 */
@Controller
@RequestMapping("admin/user")
public class UserinfoController {
    @Autowired
    private IUserinfoService userinfoService;

    /**
     * 跳转用户信息首页
     * @return
     */
    @RequestMapping("index")
    public String index(){
        return "/admin/personal";
    }

    /**
     * 查询用户信息
     * @return
     */
    @RequestMapping("info")
    @ResponseBody
    public PageResult info(){
        return userinfoService.query();
    }

    /**
     * 更新用户信息
     * @param userinfo
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public JSONResult update(Userinfo userinfo){
        JSONResult result = new JSONResult();
        try {
            userinfoService.update(userinfo);
            result.setMsg("更新成功!");
        }catch (Exception e){
            result.setSuccess(false);
            result.setMsg(e.getMessage());
        }
        return result;
    }

    /**
     * 修改密码
     * @param password
     * @param newPwd
     * @return
     */
    @RequestMapping("update_pwd")
    @ResponseBody
    public JSONResult pwd(String password, String newPwd){
        JSONResult json = new JSONResult();
        try {
            userinfoService.editPWD(password, newPwd);
            json.setMsg("修改成功，请重新登陆！");
        } catch (Exception e) {
            e.printStackTrace();
            json.setSuccess(false);
            json.setMsg(e.getMessage());
        }
        return json;
    }
}
