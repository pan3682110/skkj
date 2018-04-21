package org.english.operation.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.english.operation.annotation.Token;
import org.english.operation.model.responseBean.ResultPOJO;
import org.english.operation.service.UserService;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

/**
 * 
 * @author 冯盼
 * @time 2018-04-17下午2:44:03
 */
@Component
public class TokenInterceptor implements HandlerInterceptor {
	
	@Autowired
	private UserService userService;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		//跨域试探请求不处理
    	if(!(handler instanceof HandlerMethod)){
    		return true;
    	}
     	HandlerMethod method = (HandlerMethod) handler;
		Token token = method.getMethodAnnotation(Token.class);
		if (token != null) {
			String tokenStr = request.getHeader("authorization");
			if(StringUtils.isBlank(tokenStr)){
//				response.setHeader("Access-Control-Allow-Credentials","false");
				response.setHeader("Access-Control-Allow-Origin","*");
				response.setHeader("Access-Control-Allow-Headers","*");
				response.setHeader("Access-Control-Expose-Headers","Set-Cookie");
				response.setHeader("content-type", "application/json");
				response.setHeader("Access-Control-Allow-Methods","*");
				response.setCharacterEncoding("utf-8");
				response.getWriter().write(JSONObject.toJSONString(new ResultPOJO(502,null,"登录信息已经过期")));
				return false;
			}
			 
			
			if(!userService.isExist(tokenStr)){
//				response.setHeader("Access-Control-Allow-Credentials","false");
				response.setHeader("Access-Control-Allow-Origin","*");
				response.setHeader("Access-Control-Expose-Headers","Set-Cookie");
				response.setHeader("content-type", "application/json");
				response.setHeader("Access-Control-Allow-Headers","*");
				response.setHeader("Access-Control-Allow-Methods","*");
				response.setCharacterEncoding("utf-8");
				response.getWriter().write(JSONObject.toJSONString(new ResultPOJO(502,null,"登录信息已经过期")));
				return false;
			}
			request.setAttribute("tokenStr", tokenStr);
			
		}
		 
    	return true;
    }
	

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		request.removeAttribute("tokenStr");
		
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		
	}

}
