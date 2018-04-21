package org.english.operation.controller;

import java.util.List;

import org.english.operation.annotation.Token;
import org.english.operation.exception.BussinessException;
import org.english.operation.model.dto.CourseTreeDto;
import org.english.operation.model.dto.UserBookDto;
import org.english.operation.model.po.Course;
import org.english.operation.model.responseBean.ResultPOJO;
import org.english.operation.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;

@RestController
@Api(tags="选课接口")
@RequestMapping("/ch")
public class CourseController {
	@Autowired
	private CourseService courseService;
	
	@ApiOperation(value="课程列表接口")
	@Token
	@RequestMapping(value="/courselist",method=RequestMethod.POST)
	public ResultPOJO getCourseList(@ApiParam(value="Id 默认为1",required=false) @RequestParam(defaultValue="1") int id) throws BussinessException {
		
		List<Course> list = courseService.getCourseModule(id);
		return new ResultPOJO(200, list, null);
	}
	@ApiOperation(value="所有课程列表接口")
	@Token
	@RequestMapping(value="/all",method=RequestMethod.POST)
	public ResultPOJO getCourse() throws BussinessException {
		
		 CourseTreeDto dto = courseService.getCourseList();
		return new ResultPOJO(200, dto, null);
	}
	
	@ApiOperation(value="新用户课程选择接口")
	@Token
	@RequestMapping(value="/free",method=RequestMethod.POST)
	public ResultPOJO chooseFreeCourse(@ApiParam(value="课程id",required=true) @RequestParam int id,@ApiParam(hidden=true)@RequestAttribute String tokenStr) throws BussinessException {
		courseService.chooseFreeCourse(id, tokenStr);
		return new ResultPOJO(200, "true", null);
		
	}
	@ApiOperation(value="用户已订阅课程接口")
	@Token
	@RequestMapping(value="/sub",method=RequestMethod.POST)
	public ResultPOJO subCourse(@ApiParam(hidden=true) @RequestAttribute String tokenStr) throws BussinessException{
		List<UserBookDto> list = courseService.subCourse(tokenStr);
		return new ResultPOJO(200, list, null);
		
	}
	@ApiOperation(value="用户过期课程接口")
	@Token
	@RequestMapping(value="/subover",method=RequestMethod.POST)
	public ResultPOJO subOverCourse(@ApiParam(hidden=true) @RequestAttribute String tokenStr) throws BussinessException{
		List<UserBookDto> list = courseService.subOverCourse(tokenStr);
		return new ResultPOJO(200, list, null);
		
	}
	@ApiOperation(value="用户卡号开通课程接口")
	@Token
	@RequestMapping(value="/open",method=RequestMethod.POST)
	public ResultPOJO openCourse(@ApiParam(hidden=true) @RequestAttribute String tokenStr,@ApiParam(value="课程id",required=true) @RequestParam int courseId,@ApiParam(value="卡号",required=true) @RequestParam String card,@ApiParam(value="密码",required=true) @RequestParam String password) throws BussinessException {
		courseService.openCourse(tokenStr, courseId, card, password);
		return new ResultPOJO(200, "true", null);
		
	}
	
	
	
}
