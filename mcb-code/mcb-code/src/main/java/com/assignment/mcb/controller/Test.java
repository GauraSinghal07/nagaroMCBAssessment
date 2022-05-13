package com.assignment.mcb.controller;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Test {
public static void main(String[] args) {
	Long datetime = System.currentTimeMillis();
	Timestamp timestamp = new Timestamp(datetime);
	DateFormat dateFormat = new SimpleDateFormat ("yyyyMMdd");
	String timestp = dateFormat.format(timestamp);
	System.out.println(timestp);
	

}
}
