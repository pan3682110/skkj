package org.english.operation.mapper.base;

import java.util.List;

import org.english.operation.mapper.inter.Mapper;
import org.english.operation.model.po.UserBook;

public interface UserBookMapper extends Mapper<UserBook> {
	
	//查询订阅课程课程
	List<UserBook> checkoverdue(String userId);
	//查询过期课程
	List<UserBook> overdue(String userId);
	
}