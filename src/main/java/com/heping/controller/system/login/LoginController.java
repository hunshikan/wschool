package com.heping.controller.system.login;


import com.heping.controller.base.BaseController;
import com.heping.entity.system.User;
import com.heping.service.system.UserManager;
import com.heping.service.system.wslog.WSlogManager;
import com.heping.util.Const;
import com.heping.util.Jurisdiction;
import com.heping.util.PageData;
import com.heping.util.Tools;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

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
    @Resource(name="wslogService")
    private WSlogManager WSLOG;


    @RequestMapping(value = "/login_login")
    public ModelAndView toLogin() throws Exception{
        ModelAndView mv=this.getModelAndView();
        mv.setViewName("system/public/login");
        return mv;
    }
    @RequestMapping(value = "/Do_login",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public Object DoLogin() throws Exception{
        Map<String,String> map = new HashMap<String,String>();
        String message="";
        PageData pd=this.getPageData();

        String code=pd.getString("code");
        String USERNAME=pd.getString("username");
        String PASSWORD=pd.getString("password");

        Subject currentUser= SecurityUtils.getSubject();
        Session session=currentUser.getSession();
        String sessionCode=(String)session.getAttribute(Const.SESSION_SECURITY_CODE);

        if(Tools.notEmpty(sessionCode)&&sessionCode.equalsIgnoreCase(code)){
            pd.put("USERNAME",USERNAME);
            pd.put("PASSWORD",PASSWORD);
            pd=userService.getUserByNameAndPwd(pd);
            if(pd!= null){
                User user=new User();
                user .setUSERNAME(pd.getString("USERNAME"));
                user .setPASSWORD(pd.getString("PASSWORD"));
                session.setAttribute(Const.SESSION_USER,user);
                session.removeAttribute(Const.SESSION_SECURITY_CODE);
                session.setAttribute(Const.SESSION_USERNAME, USERNAME);

                Subject subject=SecurityUtils.getSubject();
                UsernamePasswordToken token=new UsernamePasswordToken(USERNAME,PASSWORD);
                try {
                    subject.login(token);
                } catch (AuthenticationException e) {
                    message= "身份验证失败！";
                }
            }else{
                message= "usererror";            //用户名密码有误
                logBefore(logger, USERNAME+"登录系统密码或用户名错误");
                WSLOG.save(USERNAME, "登录系统密码或用户名错误");
            }
        }else {
            message = "codeerror";				 	//验证码输入有误
        }
        if(Tools.isEmpty(message)){
            message = "success";					//验证成功
            logBefore(logger, USERNAME+"登录系统");
            WSLOG.save(USERNAME, "登录系统");
        }
        map.put("result",message);
        return map;
    }


    /**
     *
     * @return  进入首页
     * @throws Exception
     */
    @RequestMapping(value = "/index")
    public String  toIndex() throws Exception{
        return "system/public/index";
    }

    /**
     * 进入tab标签
     * @return
     */
    @RequestMapping(value="/welcome")
    public String welcome(){
        return "system/public/welcome";
    }

    @RequestMapping(value = "/list")
    public String  toList() throws Exception{
        return "system/public/list";
    }

    @RequestMapping(value = "/404")
    public String  toerror() throws Exception{
        return "system/public/404";
    }
    @RequestMapping(value = "/logout")
    public ModelAndView logout() throws Exception{
        String USERNAME= Jurisdiction.getUsername();    //当前登录用户名
        System.out.println(USERNAME);
        logBefore(logger,USERNAME+"退出系统");
        WSLOG.save(USERNAME,"退出");
        ModelAndView mv=this.getModelAndView();
        PageData pd=new PageData();
        this.removeSession(USERNAME);//请缓存

        //shiro销毁登录
        Subject subject = SecurityUtils.getSubject();
        subject.logout();

        mv.setViewName("system/public/login");

        return mv;

    }

    /**
     * 清理session
     */
    public void removeSession(String USERNAME){
        Session session = Jurisdiction.getSession();	//以下清除session缓存
        session.removeAttribute(Const.SESSION_USER);
    }

}
