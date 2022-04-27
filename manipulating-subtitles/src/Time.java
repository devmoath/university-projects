public class Time {

	private int hour, minute, second, milliSecond;

	public Time(){
		hour = minute = second = milliSecond = 0;
	}

	public Time(int hour, int minute, int second, int milliSecond) {
		setHH(hour);
		setMM(minute);
		setSS(second);
		setMS(milliSecond);
		fixTime();
	}	

//	we use it if we want to increasing the time
	public void increaseTime(int milliSecond){
		this.milliSecond += milliSecond;
		fixTime();
	}

//	we use it if we want to decreasing the time
	public void decreaseTime(int milliSecond){
		int oldTime = toMiliSec();
		hour = minute = second = this.milliSecond = 0;
		oldTime -= milliSecond;
		this.milliSecond = oldTime;
		fixTime();
	}

//	fix time if it's not like his format hh-mm-ss-mss
	public void fixTime(){
		second += milliSecond / 1000;
		milliSecond %= 1000;
		minute += second / 60;
		second %= 60;
		hour += minute / 60;
		minute %= 60;
	}

//	Confirm the time from this format hh-mm-ss-mss to milliSecond
	public int toMiliSec(){
		int miliSecond = (hour * 3600000) + (minute * 60000) + (second * 1000) + milliSecond;
		return miliSecond;
	}

//	display the time look like this format hh-mm-ss-mss
	public void display(){
		if(hour <= 9)
			System.out.print("0");
		System.out.print(hour + ":");
		if(minute <= 9)
			System.out.print("0");
		System.out.print(minute + ":");
		if(second <= 9)
			System.out.print("0");
		System.out.print(second + ",");
		if(milliSecond <= 99)
			System.out.print("0");
		if(milliSecond <= 9)
			System.out.print("0");
		System.out.print(milliSecond);
	}

	public int getHH(){
		return hour;
	}

	public int getMM(){
		return minute;
	}

	public int getSS(){
		return second;
	}

	public int getMS(){
		return milliSecond;
	}

	public void setHH(int hh){
		this.hour = hh;
		fixTime();
	}

	public void setMM(int mm){
		this.minute = mm;
		fixTime();
	}

	public void setSS(int ss){
		this.second = ss;
		fixTime();
	}

	public void setMS(int ms){
		this.milliSecond = ms;
		fixTime();
	}
}