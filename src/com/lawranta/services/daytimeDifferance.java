package com.lawranta.services;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class daytimeDifferance {

	/*
	 * public static void main(String[] args) {
	 * 
	 * 
	 * calculate("1986-04-08 12:30:55","2023-08-31 14:31:14"); }
	 */

	public static String calculate(String d1, String d2) {

		LocalDateTime date1 = LocalDateTime.parse(d1, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		LocalDateTime date2 = LocalDateTime.parse(d2, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		System.out.println(date1 + " to " + date2);

		/*
		 * LocalDateTime date1 = LocalDateTime.parse(d1,dtf); LocalDateTime date2 =
		 * LocalDateTime.parse(d2,dtf);
		 */

		float seconds = (((ChronoUnit.SECONDS.between(date1, date2))));

		float minutes = seconds / 60;
		float hours = minutes / 60;
		System.out.println(
				seconds + " seconds is equal to " + minutes + " minutes which is equal to " + hours + " hours! ");

		System.out.println("returning subtotal of: " + hours);

		// this seems inefficient there's probably a better way to do this but let's
		// convert from decimal hours to HH:mm:SS

		return parseTotal(hours);

	}

	public static String parseTotal(float totalHours) {
		// format decimals to hh:mm:ss
		{

			int hour = (int) totalHours;

			int min = (int) ((totalHours - hour) * 60);

			int sec = (int) (((totalHours - hour) * 60 - min) * 60);

			String formattedHours = String.format("%02d", hour);
			String formattedMinutes = String.format("%02d", min);
			String formattedSeconds = String.format("%02d", sec);

			return formattedHours + ":" + formattedMinutes + ":" + formattedSeconds;
		}

	}

}
