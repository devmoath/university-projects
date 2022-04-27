public class Subtitle
{
	private Time startTime, endTime;
	private String text;

	public Subtitle(){
		startTime = endTime = null;
		text = null;
	}
	public Subtitle(Time startTime, Time endTime, String text){
		this.startTime = new Time();
		this.startTime = startTime;
		this.endTime = endTime;
		this.text = text;
	}
	public void display(){
		((Time) startTime).display();
		System.out.print(" --> ");
		((Time) endTime).display();
		System.out.println("\n" + text);
	}
	// Return the startTime time of the Subtitle.
	public Time getStartTime(){
		return startTime;
	}	
	// Return the endTime time of the Subtitle.
	public Time getEndTime(){
		return endTime;
	}
	// Return the subtitle text.
	public String getText(){
		return text;
	}
	// Set the startTime time of the Subtitle.
	public void setStartTime(Time startTime){
		this.startTime = startTime;
	}	
	// Set the endTime time of the Subtitle.
	public void setEndTime(Time endTime){
		this.endTime = endTime;
	}
	// Set the subtitle text.
	public void setText(String text){
		this.text = text;
	}
}