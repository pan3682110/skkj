package org.english.operation.serviceImpl;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.english.operation.exception.BussinessException;
import org.english.operation.mapper.base.EmailActivateMapper;
import org.english.operation.mapper.base.IntegralRecordMapper;
import org.english.operation.mapper.base.TokenMapper;
import org.english.operation.mapper.base.UserMapper;
import org.english.operation.mapper.base.UserSettingMapper;
import org.english.operation.model.dto.UserDto;
import org.english.operation.model.po.EmailActivate;
import org.english.operation.model.po.IntegralRecord;
import org.english.operation.model.po.Token;
import org.english.operation.model.po.User;
import org.english.operation.model.po.UserSetting;
import org.english.operation.service.UserService;
import org.english.operation.utils.AESUtil;
import org.english.operation.utils.DateUtils;
import org.english.operation.utils.IdGen;
import org.english.operation.utils.RecomIdUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Maps;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements UserService {
	@Autowired
    private JavaMailSender mailSender;
	@Autowired
	private EmailActivateMapper emailActivateMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private TokenMapper tokenMapper;
	@Autowired
	private UserSettingMapper userSettingMapper;
	@Autowired
	private IntegralRecordMapper integralRecordMapper;
	//发送邮箱
	@Value("${spring.mail.username}")
    private String Sender;
	//用户普通注册渠道
	@Override
	public void register(EmailActivate email) throws BussinessException  {
		try {
			//用户名不能重复
			String account = email.getAccount();
			Example example=new Example(EmailActivate.class);
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("account", account);
			List<EmailActivate> list = emailActivateMapper.selectByExample(example);
			if(list.size()!=0) {
				throw new BussinessException("用户名已存在");
			}
			String id = IdGen.getDefaultPrimaryKey();
			email.setId(id);
			email.setCreatTime(new Date());
			emailActivateMapper.insertSelective(email);
			//注册成功给用户发送激活邮件
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(Sender);
            helper.setTo(email.getEmail());
            helper.setSubject("单词突击·赢  用户激活");
            StringBuffer sb = new StringBuffer();
            sb.append("你的激活连接为：");
//            sb.append("<a href=\"192.168.0.224:3000\" style=\"color:blue;\">http://localhost:3000/activation/{{random}} </a>");
            sb.append("<a href=");
            sb.append("\"http://localhost:3000/activation/");
            sb.append(id+"\"");
            sb.append(" style=\\\"color:blue;\\\"> ");
            sb.append("http://localhost:3000/activation/"+id+" </a>");
            helper.setText(sb.toString(), true);
	        mailSender.send(message);
		} catch (MessagingException e) {
			throw new BussinessException(e);
		}catch (RuntimeException e) {
			throw new BussinessException(e);
		}
		
	}
	//用户邮件激活
	@Override
	public void activate(String userId) throws BussinessException {
		try {
			EmailActivate emailUser = emailActivateMapper.selectByPrimaryKey(userId);
			if(emailUser==null) {
				throw new BussinessException("用户不存在");
			}
			User user = new User();
			user.setId(emailUser.getId());
			user.setName(emailUser.getName());
			user.setAccount(emailUser.getAccount());
			user.setEmail(emailUser.getEmail());
			user.setPassword(emailUser.getPassword());
			user.setRecomId(emailUser.getRecomId());
			user.setCreateTime(emailUser.getCreatTime());
			user.setCreateType(2);
			user.setSelfRecomId(RecomIdUtil.genRandomNum());
			user.setSignStatus(1);
			userMapper.insertSelective(user);
			UserSetting userSetting = new UserSetting();
			userSetting.setId(emailUser.getId());
			userSettingMapper.insertSelective(userSetting);
			
		}catch (RuntimeException  e) {
			throw new BussinessException(e);
		}
			
	}
	//用户取回密码
	@Override
	public void retry(String account, String email) throws BussinessException {
		try {
			Example example = new Example(User.class);
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("account", account).andEqualTo("email", email);
			List<User> list = userMapper.selectByExample(example);	
			if(list.size()!=1) {
				throw new BussinessException("请使用正确的用户名和邮箱");
			}
			User user = list.get(0);
			SimpleMailMessage message = new SimpleMailMessage();
	        message.setFrom(Sender);
	        message.setTo(user.getEmail()); 
	        message.setSubject("单词突击·赢  找回密码");
	        StringBuffer sb = new StringBuffer();
	        sb.append("亲爱的用户，单词突击·赢   提醒您:你的注册密码是:  [ ");
	        
	        sb.append(user.getPassword()+" ],请注意密码安全(若密码被盗,请即使修改密码)");
	        
	        message.setText(sb.toString());
	        mailSender.send(message);
			
		}catch (RuntimeException  e) {
			throw new BussinessException(e);
		}
		
	}
	//用户登录
	@Override
	public UserDto login(String email, String password) throws BussinessException {
		try {
			//判断邮箱密码是否相符
//			Example example = new Example(User.class);	
//			Criteria criteria = example.createCriteria();
//			criteria.andEqualTo("email", email).andEqualTo("password", password);
//			List<User> list = userMapper.selectByExample(example);
			User user = userMapper.login(email, password);
//			Example example = new Example(User.class);
			if(user!=null) {
				//更新用户表的信息
//				User user = list.get(0);
				String id = user.getId();
				
				/*Date lastLoginTime = user.getLastLoginTime();
				//之前的登录次数
				Integer loginCount = user.getLoginCount();
				//每登录一次加一(24小时内多次登录只算一次)
				Date addTime = DateUtils.addTime(lastLoginTime, "1d");
				//当前时间  防止刷登录次数
				Date nowDate = new Date();
				if(nowDate.after(addTime)) {
					Integer newLoginCount=loginCount+1;
					user.setLoginCount(newLoginCount);
				}
				user.setLastLoginTime(new Date());
				userMapper.updateByExampleSelective(user, example);*/
				user.setLastLoginTime(new Date());
				Integer loginCount = user.getLoginCount();
				Integer newLoginCount=loginCount+1;
				user.setLoginCount(newLoginCount);
				userMapper.updateByPrimaryKeySelective(user);
				Token token = new Token();
				//根据id删除已存在的token
				tokenMapper.deleteByPrimaryKey(id);
				//生成随机数作为salt
				Random random = new Random();
				int i = random.nextInt();
				String salt=Integer.toString(i);
				//将用户id加密得到token并存入数据库
				String tokenId = AESUtil.encrypt(id+salt);
				token.setId(id);
				token.setToken(tokenId);
				token.setCreateTime(new Date());
				token.setOverdueTime(DateUtils.addTime(new Date(), "3h"));
				tokenMapper.insert(token);
				UserDto userDto = new UserDto();			
				userDto.setUserId(user.getId());
				userDto.setAccount(user.getAccount());
				userDto.setEmail(user.getEmail());
				userDto.setSignStatus(user.getSignStatus());
				userDto.setUserStatus(user.getStatus());
				userDto.setToken(tokenId);
				userDto.setGold(user.getIntegral());
				userDto.setName(user.getName());
				UserSetting userSetting = userSettingMapper.selectByPrimaryKey(user.getId());
				userDto.setUserSetting(userSetting);
				return userDto;
			}
			return null;
		} catch (RuntimeException e) {
			throw new BussinessException(e);
		}
	}
	
	/**
	 * 查询token是否存在
	 * 
	 */
	@Override
	public boolean isExist(String token) throws BussinessException {
		try {
			Example example = new Example(Token.class);
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("token", token);
			List<Token> list = tokenMapper.selectByExample(example);
			if(list!=null && !list.isEmpty()) {
				return true;
			}
			return false;
		} catch (RuntimeException e) {
			throw new BussinessException(e);
		}
	}
	
	/**
	 * 用户退出 删除token
	 * @throws BussinessException 
	 */
	@Override
	public void quit(String tokenStr) throws BussinessException {
		try {
			Example example = new Example(Token.class);
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("token", tokenStr);
			tokenMapper.deleteByExample(example);
		} catch (RuntimeException e) {
			throw new BussinessException(e);
		}
		
	}
	/**
	 * 用户手动签到
	 */
	@Override
	public void sign(String tokenStr) throws BussinessException {
		try {
			//根据token串查询用户id,用户状态(0 不可用  1未付费  2已付费 3 已使用)
			Example example = new Example(Token.class);
			Criteria criteria = example.createCriteria();
			criteria.andEqualTo("token", tokenStr);
			List<Token> list = tokenMapper.selectByExample(example);
			if(list== null || list.isEmpty()) {
				throw new BussinessException("用户token查询失败");
			}
			Token token = list.get(0);
			//得到用户的ID
			String userId = token.getId();
			User user = userMapper.selectByPrimaryKey(userId);
			//修改用户积分与状态
			user.setIntegral(user.getIntegral()+5);		
			user.setSignStatus(2);
			userMapper.updateByPrimaryKeySelective(user);
			//插入积分变动明细表
			IntegralRecord integralRecord = new IntegralRecord();
			integralRecord.setUserId(userId);
			integralRecord.setIntegral(5);
			integralRecord.setType(1);
			integralRecord.setBusType(1);
			integralRecord.setCreateTime(new Date());
			integralRecordMapper.insertSelective(integralRecord);

		} catch (RuntimeException e) {
			throw new BussinessException(e);
		}
		
	}
	

}
