package org.english.operation.model.dto;

import java.util.List;

import org.english.operation.model.po.Course;

public class CourseTreeDto {
	List<Course> parentCourse;
	List<Course> secondLevelCourse;
	List<Course> childrenCourse;
	public List<Course> getParentCourse() {
		return parentCourse;
	}
	public void setParentCourse(List<Course> parentCourse) {
		this.parentCourse = parentCourse;
	}
	public List<Course> getSecondLevelCourse() {
		return secondLevelCourse;
	}
	public void setSecondLevelCourse(List<Course> secondLevelCourse) {
		this.secondLevelCourse = secondLevelCourse;
	}
	public List<Course> getChildrenCourse() {
		return childrenCourse;
	}
	public void setChildrenCourse(List<Course> childrenCourse) {
		this.childrenCourse = childrenCourse;
	}
	
	
	
}
