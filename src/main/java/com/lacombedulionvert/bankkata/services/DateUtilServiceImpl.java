package com.lacombedulionvert.bankkata.services;

import java.time.LocalDateTime;

public class DateUtilServiceImpl implements DateUtilService{

	public LocalDateTime getCurrentDateTime() {
		return LocalDateTime.now();
	}
}
