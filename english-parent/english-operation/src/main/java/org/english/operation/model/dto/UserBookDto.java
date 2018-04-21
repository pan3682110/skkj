package org.english.operation.model.dto;

import org.english.operation.model.po.UserBook;

public class UserBookDto {
	private UserBook userBook;
	private int day;
	public UserBook getUserBook() {
		return userBook;
	}
	public void setUserBook(UserBook userBook) {
		this.userBook = userBook;
	}
	public int getDay() {
		return day;
	}
	public void setDay(int day) {
		this.day = day;
	}

}
