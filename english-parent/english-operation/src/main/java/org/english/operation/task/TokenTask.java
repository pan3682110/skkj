package org.english.operation.task;

import java.util.Date;
import java.util.List;

import org.english.operation.mapper.base.IntegralRecordMapper;
import org.english.operation.mapper.base.TokenMapper;
import org.english.operation.mapper.base.UserMapper;
import org.english.operation.model.po.User;
import org.english.operation.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 定时任务，查询token是否过期
 * @author 冯盼
 * @time 2018-04-17上午11:23:13
 */
@Component
public class TokenTask {
	@Autowired
	private TokenMapper tokenMapper;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private IntegralRecordMapper integralRecordMapper;
	@Scheduled(cron="0 0 0/3 * * ?")//每三小时执行一次
//	@Scheduled(cron="0 0/1 * * * ?")//每三小时执行一次
	public void checkToken() {
		tokenMapper.deleteToken();
	}
	
	//修改状态
	@Scheduled(cron="0 0 0 * * ?")
	public void updateSignStatus() {
		userMapper.updateSignStatus();
	}
	//积分扣除
	@Scheduled(cron="0 0 1 * * ?")
	public void updateGold() {
		List<User> userList = userMapper.selectAll();
		for(User user:userList) {
			Date lastLoginTime = user.getLastLoginTime();
			Date nowTime = new Date();
			if(DateUtils.days(lastLoginTime, nowTime)!=0) {
				
			}
		}
	}
	
	
	
}
