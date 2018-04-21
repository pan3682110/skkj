package org.english.operation.controller;

import org.english.operation.exception.BussinessException;
import org.english.operation.model.po.EmailActivate;

import org.english.operation.model.responseBean.ResultPOJO;
import org.english.operation.service.Send;
import org.english.operation.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;


@RestController
@Api(tags="用户注册接口")
@RequestMapping("/reg")
public class RegisterController {
	@Autowired
	private UserService userService;
	/*@Autowired
	private Send send;*/
	@ApiOperation(value="用户普通注册接口")
	@RequestMapping(value="/mail" ,method=RequestMethod.POST)
	public ResultPOJO register(EmailActivate email) throws BussinessException {
		userService.register(email);
		return new ResultPOJO(200, "true", null);
	}
	@ApiOperation(value="邮件激活接口")
	@RequestMapping(value="/activate/{userId}",method=RequestMethod.GET)
	public ResultPOJO activate(@ApiParam(value = "用户id",required = true) @PathVariable String userId) throws BussinessException {
		userService.activate(userId);
		return new ResultPOJO(200, "true", null);
	}
	
	@ApiOperation(value="取回密码接口")
	@RequestMapping(value="/retry",method=RequestMethod.POST)
	public ResultPOJO retrieve(@ApiParam(value="用户名",required=true) @RequestParam String account,@ApiParam(value="注册邮箱",required=true)@RequestParam String mail) throws BussinessException{
		userService.retry(account, mail);
		return new ResultPOJO(200, "true", null);
	}
	
	/*@RequestMapping("/send")
	public void send() throws Exception {
		send.go();
	}*/
}
