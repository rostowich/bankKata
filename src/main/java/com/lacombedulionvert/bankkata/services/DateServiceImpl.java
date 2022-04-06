package com.lacombedulionvert.bankkata.services;

import java.time.LocalDateTime;

public class DateServiceImpl implements DateUtilService{

	public LocalDateTime getCurrentDateTime() {
		return LocalDateTime.now();
	}
}
