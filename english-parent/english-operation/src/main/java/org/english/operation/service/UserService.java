package org.english.operation.service;


import org.english.operation.exception.BussinessException;
import org.english.operation.model.dto.UserDto;
import org.english.operation.model.po.EmailActivate;
import org.english.operation.model.po.User;



public interface UserService {
	//用户注册
	void register(EmailActivate email) throws BussinessException;
	//用户激活
	void activate(String userId) throws BussinessException;
	//取回密码
	void retry(String account,String email) throws BussinessException;
	//用户登录
	UserDto login(String email,String password) throws BussinessException;
	//查询token是否存在
	boolean isExist(String token) throws BussinessException;
	//用户退出删除token
	void quit(String tokenStr) throws BussinessException;
	//用户签到
	void sign(String tokenStr) throws BussinessException;
	
	
}
