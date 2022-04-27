import java.util.List;

public class SubtitleSequence
{
	private LinkedList<Subtitle> subtitles;
		
	public SubtitleSequence(){
		subtitles = new LinkedList<Subtitle>();
	}
	public void addSubtitle(Subtitle st){
		if(subtitles.empty())
			subtitles.insert(st);
		else{
			subtitles.findFirst();
			while(!subtitles.last()){
				if(((Time)st.getStartTime()).toMiliSec() < ((Time) subtitles.retrieve().getStartTime()).toMiliSec()){
					Subtitle tmp = subtitles.retrieve();
					subtitles.update(st);
					subtitles.insert(tmp);
					return;
				}
				subtitles.findNext();
			}
			if(((Time)st.getStartTime()).toMiliSec() < ((Time) subtitles.retrieve().getStartTime()).toMiliSec()){
				Subtitle tmp = subtitles.retrieve();
				subtitles.update(st);
				subtitles.insert(tmp);
				return;
			}
			subtitles.insert(st);
		}
	}
	public List<Subtitle> getSubtitles(){
		return subtitles;
	}
	public Subtitle getSubtitle(Time time){
		if(subtitles.empty())
			return null;
		else
			return search(time);
	}
	private Subtitle search(Time time){
		subtitles.findFirst();
		while(!subtitles.last()){
			if(((Time)time).toMiliSec() == ((Time) subtitles.retrieve().getStartTime()).toMiliSec())
				return subtitles.retrieve();
			subtitles.findNext();
		}
		if(((Time)time).toMiliSec() == ((Time) subtitles.retrieve().getStartTime()).toMiliSec())
			return subtitles.retrieve();
		return null;		
	}
	private int search1(Time time){
		int counter = 0;
		subtitles.findFirst();
		while(!subtitles.last()){
			if(((Time)time).toMiliSec() == ((Time) subtitles.retrieve().getStartTime()).toMiliSec())
				return counter;
			subtitles.findNext();
			counter++;
		}
		if(((Time)time).toMiliSec() == ((Time) subtitles.retrieve().getStartTime()).toMiliSec())
			return ++counter;
		return -1;		
	}
	public List<Subtitle> getSubtitles(Time startTime, Time endTime){
		if(subtitles.empty())
			return null;
		LinkedList<Subtitle> k = new LinkedList<Subtitle>();
		subtitles.findFirst();
		while(!subtitles.last()){
			if(((Time)startTime).toMiliSec() <= ((Time) subtitles.retrieve().getStartTime()).toMiliSec() && ((Time)endTime).toMiliSec() >= ((Time) subtitles.retrieve().getEndTime()).toMiliSec())
				k.insert(subtitles.retrieve());
			subtitles.findNext();
		}
		if(((Time)startTime).toMiliSec() <= ((Time) subtitles.retrieve().getStartTime()).toMiliSec() && ((Time)endTime).toMiliSec() >= ((Time) subtitles.retrieve().getEndTime()).toMiliSec())
			k.insert(subtitles.retrieve());
		return k;
	}
	public List<Subtitle> getSubtitles(String str){
		if(subtitles.empty())
			return null;
		LinkedList<Subtitle> k = new LinkedList<Subtitle>();	
		subtitles.findFirst();
		while(!subtitles.last()){
			if(subtitles.retrieve().getText().contains(str))
				k.insert(subtitles.retrieve());
			subtitles.findNext();
		}
		if(subtitles.retrieve().getText().contains(str))
			k.insert(subtitles.retrieve());
		return k;
	}
	public void remove(String str){
		if(subtitles.empty())
			return;
		subtitles.findFirst();
		while(!subtitles.last()){
			if(subtitles.retrieve().getText().contains(str))
				subtitles.remove();
			subtitles.findNext();
		}
		if(subtitles.retrieve().getText().contains(str))
			subtitles.remove();
	}	
	public void replace(String str1, String str2){
		if(subtitles.empty())
			return;
		subtitles.findFirst();
		while(!subtitles.last()){
			if(subtitles.retrieve().getText().equals(str1))
				subtitles.retrieve().setText(str2);
			subtitles.findNext();
		}
		if(subtitles.retrieve().getText().equals(str1))
			subtitles.retrieve().setText(str2);
	}
	public void shift(int offset){
		if(subtitles.empty())
			return;
		if(offset < 0)
			offset = 0;
		subtitles.findFirst();
		while(!subtitles.last()){
			((Time) subtitles.retrieve().getStartTime()).increaseTime(offset);
			((Time) subtitles.retrieve().getEndTime()).increaseTime(offset);
			if(((Time) subtitles.retrieve().getEndTime()).toMiliSec() == 0)
				subtitles.remove();
			subtitles.findNext();
		}
		((Time) subtitles.retrieve().getStartTime()).increaseTime(offset);
		((Time) subtitles.retrieve().getEndTime()).increaseTime(offset);
		if(((Time) subtitles.retrieve().getEndTime()).toMiliSec() == 0)
			subtitles.remove();
	}
	public void cut(Time startTime, Time endTime){
		if(subtitles.empty())
			return;
		int remaining = ((Time)endTime).toMiliSec() - ((Time)startTime).toMiliSec();
		subtitles.findFirst();
		while(!subtitles.last()){
			if((((Time)startTime).toMiliSec() <= ((Time) subtitles.retrieve().getStartTime()).toMiliSec() || ((Time)startTime).toMiliSec() <= ((Time) subtitles.retrieve().getEndTime()).toMiliSec()) && (((Time)endTime).toMiliSec() >= ((Time) subtitles.retrieve().getEndTime()).toMiliSec() || ((Time)endTime).toMiliSec() >= ((Time) subtitles.retrieve().getStartTime()).toMiliSec()))
				subtitles.remove();
			subtitles.findNext();
		}
		if((((Time)startTime).toMiliSec() <= ((Time) subtitles.retrieve().getStartTime()).toMiliSec() || ((Time)startTime).toMiliSec() <= ((Time) subtitles.retrieve().getEndTime()).toMiliSec()) && (((Time)endTime).toMiliSec() >= ((Time) subtitles.retrieve().getEndTime()).toMiliSec() || ((Time)endTime).toMiliSec() >= ((Time) subtitles.retrieve().getStartTime()).toMiliSec()))
			subtitles.remove();
		if(subtitles.empty())
			return;
		subtitles.findFirst();
		while(!subtitles.last()){
			if(((Time)endTime).toMiliSec() <= ((Time) subtitles.retrieve().getStartTime()).toMiliSec()){
				((Time) subtitles.retrieve().getStartTime()).decreaseTime(remaining);
				((Time) subtitles.retrieve().getEndTime()).decreaseTime(remaining);
			}
			subtitles.findNext();
		}
		if(((Time)endTime).toMiliSec() <= ((Time) subtitles.retrieve().getStartTime()).toMiliSec())	{
			((Time) subtitles.retrieve().getStartTime()).decreaseTime(remaining);
			((Time) subtitles.retrieve().getEndTime()).decreaseTime(remaining);
		}
	}
	public void display(){
		subtitles.findFirst();
		while(!subtitles.last())
		{
			((Subtitle) subtitles.retrieve()).display();
			subtitles.findNext();
		}
		if(!subtitles.empty())
		((Subtitle) subtitles.retrieve()).display();
	}
}