package application;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Main { 

	public static void main(String[] args) {
		DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE_TIME;
		LocalDateTime ldt = LocalDateTime.now();
		ZonedDateTime zdt = ZonedDateTime.parse("2024-04-07T14:22:40Z");
		System.out.println(ldt);
		System.out.println(zdt);
	}

}
