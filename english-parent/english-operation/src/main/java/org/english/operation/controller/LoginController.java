package org.english.operation.controller;



import org.english.operation.annotation.Token;
import org.english.operation.exception.BussinessException;
import org.english.operation.model.dto.UserDto;
import org.english.operation.model.responseBean.ResultPOJO;
import org.english.operation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags="登录/退出接口")
@RequestMapping("/login")
public class LoginController {
	@Autowired
	private UserService userService;
	
	@ApiOperation(value="用户登录接口")
	@RequestMapping(value="/in",method=RequestMethod.POST)
	public ResultPOJO login(@ApiParam(value="邮箱/用户名",required=true) @RequestParam String emailoraccount,@ApiParam(value="密码",required=true) @RequestParam String password) throws BussinessException {
		UserDto userDto = userService.login(emailoraccount, password);
		if(userDto!=null) {
			return new ResultPOJO(200, userDto, null);
		}else {
			return new ResultPOJO(500, null, "邮箱与密码不符");
		}
	}
	
	@ApiOperation(value="用户退出接口")
	@Token
	@RequestMapping(value="/quit",method=RequestMethod.GET)
	public ResultPOJO quit(@ApiParam(value = "token", required = true) @RequestHeader(required = true) String tokenStr) throws BussinessException {
		userService.quit(tokenStr);
		return new ResultPOJO(200, "true", null);
		
	}
	
	@ApiOperation(value="用户签到接口")
	@Token
	@RequestMapping(value="/sign",method=RequestMethod.GET)
	public ResultPOJO sign(@ApiParam(hidden=true)@RequestAttribute String tokenStr) throws BussinessException {
		userService.sign(tokenStr);
		return new ResultPOJO(200, "true", null);
		
	}
	
	
	
}
 
