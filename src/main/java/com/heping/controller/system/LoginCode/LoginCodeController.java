package com.heping.controller.system.LoginCode;

import com.heping.util.Const;
import com.heping.util.login_code.Captcha;
import com.heping.util.login_code.GifCaptcha;
import com.heping.util.login_code.SpecCaptcha;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 〈一句话功能简述〉<br>
 * 〈登录验证码〉
 *
 * @author liupengtao
 * @create 2017/8/25
 * @since 1.0.0
 */
@Controller
@RequestMapping("/code")
public class LoginCodeController {
    /**
     * java验证码jpg版本
     * @param response
     * @param request
     */
    @RequestMapping(value="getJPGCode",method= RequestMethod.GET)
    public void getJPGCode(HttpServletResponse response, HttpServletRequest request){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/jpg");
            /**
             * jgp格式验证码
             * 宽，高，位数。
             */
            Captcha captcha = new SpecCaptcha(146,33,4);
            //输出
            captcha.out(response.getOutputStream());
            Subject currentUser= SecurityUtils.getSubject();
            Session session=currentUser.getSession();
            //存入Session
            session.setAttribute(Const.SESSION_SECURITY_CODE,captcha.text());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取验证码（Gif版本）
     * @param response
     */
    @RequestMapping(value="getGifCode",method=RequestMethod.GET)
    public void getGifCode(HttpServletResponse response,HttpServletRequest request){
        try {
            response.setHeader("Pragma", "No-cache");
            response.setHeader("Cache-Control", "no-cache");
            response.setDateHeader("Expires", 0);
            response.setContentType("image/gif");
            /**
             * gif格式动画验证码
             * 宽，高，位数。
             */
            Captcha captcha = new GifCaptcha(116,36,4);
            //输出
            captcha.out(response.getOutputStream());
            Subject currentUser= SecurityUtils.getSubject();
            Session session=currentUser.getSession();
            //存入Session
            session.setAttribute(Const.SESSION_SECURITY_CODE,captcha.text());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
