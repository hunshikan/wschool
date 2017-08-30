package com.heping.interceptor;

import com.heping.entity.system.User;
import com.heping.util.Const;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.Subject;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/**
 * 
* 类名称：登录过滤，权限验证
* 类描述： 
* @author 刘鹏涛
* 创建时间：2017年8月23日
* @version 1.0
 */
public class LoginHandlerInterceptor extends HandlerInterceptorAdapter{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		// TODO Auto-generated method stub
		String path = request.getServletPath();
		if(path.matches(Const.NO_INTERCEPTOR_PATH)){
			return true;
		}else {
			//shiro管理session
			Subject currentUser = SecurityUtils.getSubject();
			Session session = currentUser.getSession();
            User user=(User)session.getAttribute(Const.SESSION_USER);
            if(user!=null){
                return true;
            }else{
                response.sendRedirect(request.getContextPath()+Const.LOGIN);
                return false;
            }
		}
		}
	}
	

