import java.io.*;

public class SubtitleSeqFactory {

	public static SubtitleSequence getSubtitleSeq(){
		return new SubtitleSequence();
	}

	public static SubtitleSequence loadSubtitleSeq(String fileName) {

		SubtitleSequence subtitleSequence = new SubtitleSequence();
		Subtitle subtitle = new Subtitle();

		try{
			FileReader reader = new FileReader(fileName);
			BufferedReader bufferedReader = new BufferedReader(reader);
		    String line = bufferedReader.readLine();
		    int number = 1;
		    while(line != null) 
		    {
		    	if(Integer.parseInt(line) != number)
		    		return null;

		    	number++;

		    	line = bufferedReader.readLine();

		    	if(!line.contains("-->"))
		    	    return null;

		    	// Start Time
		    	String s = ""+line.charAt(0) + line.charAt(1);
		    	int hh = Integer.parseInt(s);
		    	s = ""+line.charAt(3) + line.charAt(4);
		    	int mm = Integer.parseInt(s);
		    	s = ""+line.charAt(6) + line.charAt(7);
		    	int ss = Integer.parseInt(s);
		    	s = ""+line.charAt(9) + line.charAt(10) + line.charAt(11);
		    	int ms = Integer.parseInt(s);

		    	Time startTime = new Time(hh, mm, ss, ms);

		    	subtitle.setStartTime(startTime);

		    	// End Time
		    	s = ""+line.charAt(17) + line.charAt(18);
		    	hh = Integer.parseInt(s);
		    	s = ""+line.charAt(20) + line.charAt(21);
		    	mm = Integer.parseInt(s);
		    	s = ""+line.charAt(23) + line.charAt(24);
		    	ss = Integer.parseInt(s);
		    	s = ""+line.charAt(26) + line.charAt(27) + line.charAt(28);
		    	ms = Integer.parseInt(s);

		    	Time endTime = new Time(hh, mm, ss, ms);

		    	subtitle.setEndTime(endTime);

		    	line = bufferedReader.readLine();

		    	if(line.isEmpty())
		    	   return null;

		    	String text = "";

		    	for(int i = 0 ; i < line.length(); i++)
		    		text += line.charAt(i);

		    	subtitleSequence.addSubtitle(subtitle);

		    	line = bufferedReader.readLine();
		    }

		    bufferedReader.close();
		}
		catch(Exception e) 
		{
			return null;
		}
		return subtitleSequence;
	}
}