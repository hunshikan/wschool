package com.heping.controller.system.login;


import com.heping.controller.base.BaseController;
import com.heping.service.system.UserManager;
import com.heping.util.PageData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 〈一句话功能简述〉<br>
 * 〈登录controller〉
 *
 * @author liupengtao
 * @create 2017/8/16
 * @since 1.0.0
 */

@Controller
public class LoginController extends BaseController{

    @Resource(name="userService")
    private UserManager userService;


    @RequestMapping(value = "/login_login")
    public ModelAndView toLogin() throws Exception{
        ModelAndView mv=this.getModelAndView();
        mv.setViewName("system/public/index");
        PageData pd = new PageData();
        pd.put("USERNAME", "1");
        pd.put("PASSWORD", "1");
        PageData pages=userService.getUserByNameAndPwd(pd);
        System.out.println(pages.toString());
        return mv;
    }
}
