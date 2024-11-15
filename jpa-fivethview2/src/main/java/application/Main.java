package application;

import java.time.ZoneId;

public class Main { 

	public static void main(String[] args) {		
		for (String string : ZoneId.getAvailableZoneIds()) {
			System.out.println(string);
		}
	}

}
