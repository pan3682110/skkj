package org.english.operation.service;

import java.util.List;

import org.english.operation.exception.BussinessException;
import org.english.operation.model.dto.CourseTreeDto;
import org.english.operation.model.dto.UserBookDto;
import org.english.operation.model.po.Course;


/**
 * 选择课程
 * @author 冯盼
 * @time 2018-04-18下午3:06:47
 */
public interface CourseService {
	//查询所有课程列表
	List<Course> getCourseModule(int i) throws BussinessException;
	//用户选择课程
	void chooseFreeCourse(int id,String tokenStr) throws BussinessException;
	//用户已订阅课程
	List<UserBookDto> subCourse(String tokenStr) throws BussinessException;
	//用户已订阅过期课程
	List<UserBookDto> subOverCourse(String tokenStr) throws BussinessException;
	//用户卡号开通课程 
	void openCourse(String tokenStr,int courseId,String card,String password) throws BussinessException;
	//显示课程列表
	CourseTreeDto getCourseList() throws BussinessException;
}
