package application.utils;

public enum Countries {
	Brazil("Brazil", -3);
	private String name;
	private int timeZone;
	
	private Countries(String name, int timeZone) {
		this.name = name;
		this.timeZone = timeZone;
	}

	public int getTimeZone() {
		return timeZone;
	}

	public void setTimeZone(byte timeZone) {
		this.timeZone = timeZone;
	}
}
