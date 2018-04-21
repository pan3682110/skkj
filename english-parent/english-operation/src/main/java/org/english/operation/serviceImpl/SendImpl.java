package org.english.operation.serviceImpl;

import java.util.Date;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.english.operation.mapper.base.BookBackMapper;
import org.english.operation.mapper.base.BookMapper;
import org.english.operation.mapper.base.CourseMapper;
import org.english.operation.mapper.base.TokenMapper;
import org.english.operation.mapper.base.WordBackMapper;
import org.english.operation.mapper.base.WordMapper;
import org.english.operation.model.po.Book;
import org.english.operation.model.po.BookBack;
import org.english.operation.model.po.Course;
import org.english.operation.model.po.Token;
import org.english.operation.model.po.Word;
import org.english.operation.model.po.WordBack;
import org.english.operation.service.Send;
import org.english.operation.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

@Service
public class SendImpl implements Send {
	@Autowired
    private JavaMailSender mailSender;
	/*@Override
	public void send() throws MessagingException {
		SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("fengpansynnex@163.com");
        message.setTo("517207038@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText("测试邮件内容");

        mailSender.send(message);
		//注册成功给用户发送激活邮件
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom("fengpansynnex@163.com");
        helper.setTo("517207038@qq.com");
        helper.setSubject("老杨英语突击用户激活");
        StringBuffer sb = new StringBuffer();
        sb.append("你的激活连接为：");
        sb.append("<a href=\"localhost:3000\" style=\"color:red;\">localhost:3000</a>");
        helper.setText(sb.toString(), true);
        mailSender.send(message);
		
	}*/
	@Autowired
	private BookBackMapper bookBackMapper;
	@Autowired
	private BookMapper bookMapper;
	@Autowired
	private CourseMapper courseMapper;
	@Autowired
	private WordMapper wordMapper;
	@Autowired
	private WordBackMapper wordBackMapper;
	@Autowired
	private TokenMapper tokenMapper;
	
	@Override
	public void send() throws Exception {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void go() {
		Token token = tokenMapper.selectByPrimaryKey("18041617123692000000000000000000000a");
		Date createTime = token.getCreateTime();
		Date overdueTime = token.getOverdueTime();
		int days = DateUtils.days(createTime, overdueTime);
		System.out.println(days);
		
		}	
	}
	
	 

