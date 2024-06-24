package actions;

import java.util.ArrayList;
import java.text.ParseException;

public interface IHelpers {
	 public String getTodayDateINSpecificFormat(String format);

	    public String getTodayDate(String format, String timeZone);

	    public String getAlphaNumericString(int n);

	    public String getNumericString(int n);

	    public String[] splitString(String text, String splitter);

	    public ArrayList<String> splitStringintoArrayList(String text, String splitter);

	    public String replaceSpliter(String text, String splitter, String newSplitter);

	    public String getDateInDifferentFormat(String oldformat, String newFormat, String date) throws ParseException;

}
