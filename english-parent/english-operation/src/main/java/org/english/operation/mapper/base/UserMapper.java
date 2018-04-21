package org.english.operation.mapper.base;

import org.apache.ibatis.annotations.Param;
import org.english.operation.mapper.inter.Mapper;
import org.english.operation.model.po.User;

public interface UserMapper extends Mapper<User> {
	User login(@Param("name")String email,@Param("password")String password);
	void updateSignStatus();
}