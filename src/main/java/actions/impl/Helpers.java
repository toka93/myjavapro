package actions.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;
import java.text.ParseException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import actions.IHelpers;

/**
 * A helper class that provides various utility methods.
 * 
 * @author Toka.Ashraf
 *
 */

public class Helpers implements IHelpers {

	static Logger log = LogManager.getLogger(Helpers.class);

	/**
	 * Method to get the current date in a specific format.
	 * 
	 * @param format The format of the date string.
	 * @return A string representing the current date in the specified format.
	 */
	public String getTodayDateINSpecificFormat(String format) {

		 Date d = Calendar.getInstance().getTime();

		SimpleDateFormat sdf = new SimpleDateFormat(format, Locale.US);
		sdf.setTimeZone(TimeZone.getTimeZone("UTC"));
		sdf.format(Calendar.getInstance().getTime());
		return sdf.format(d);

	}

	/**
	 * Method to get the current date in a specific format and timezone.
	 * 
	 * @param format   The format of the date string.
	 * @param timeZone The timezone for the date.
	 * @return A string representing the current date in the specified format and
	 *         timezone.
	 */
	public String getTodayDate(String format, String timeZone) {
		Date datenow = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		sdf.setTimeZone(TimeZone.getTimeZone(timeZone));
		 
		return sdf.format(datenow);

	}

	/**
	 * Method to generate a random alphanumeric string of a specified length.
	 * 
	 * @param n The length of the string to generate.
	 * @return A random alphanumeric string of the specified length.
	 */
	public String getAlphaNumericString(int n) {

		String alphaNumericString = "ABCDEFGHIJKLMNOPQRSTUVWXYZ" + "0123456789" + "abcdefghijklmnopqrstuvxyz";

		StringBuilder sb = new StringBuilder(n);

		for (int i = 0; i < n; i++) {

			int index = (int) (alphaNumericString.length() * Math.random());

			sb.append(alphaNumericString.charAt(index));
		}

		return sb.toString();
	}

	/**
	 * Method to generate a random string of digits of a specified length.
	 * 
	 * @param n The length of the string to generate.
	 * @return A random string of digits of the specified length.
	 */
	public String getNumericString(int n) {

		String generatedString = RandomStringUtils.randomNumeric(n);

		log.info(generatedString);
		return generatedString;
	}

	/**
	 * Method to split a string into an array of substrings using the provided
	 * splitter string.
	 * 
	 * @param text     The text to split.
	 * @param splitter The splitter string to use.
	 * @return An array of substrings.
	 */
	public String[] splitString(String text, String splitter)

	{

		log.info("Splitting string using : %s{0}  For Text : %s{1}" , splitter , text);
		String[] words = text.split(splitter);
		for (String gen : words) {
			log.info("get each part after splitting : {}" , gen);
			log.info("each part of the list after split :{}" , gen);
		}
		log.info("get array of strings including all parts after splitting");
		return words;

	}

	/**
	 * Method to split a string into an ArrayList of substrings using the provided
	 * splitter string.
	 * 
	 * @param text     The text to split.
	 * @param splitter The splitter string to use.
	 * @return An ArrayList of substrings.
	 */
	public ArrayList<String> splitStringintoArrayList(String text, String splitter)

	{

		log.info("Splitting string using : %s{0}  For Text : %s{1}" , splitter , text);
		ArrayList<String> values = new ArrayList<>();

		String[] words = text.split(splitter);
		for (String gen : words) {
			values.add(gen);
			log.info("get each part after splitting : {}" , gen);
		}

		return values;

	}

	/**
	 * Method to replace a splitter string in a text with a new splitter string.
	 * 
	 * @param text        The text to modify.
	 * @param splitter    The splitter string to replace.
	 * @param newSplitter The new splitter string to use.
	 * @return The modified text.
	 */
	public String replaceSpliter(String text, String splitter, String newSplitter)

	{
		log.info("Splitting stringusing : {}" , splitter);

		return text.trim().replaceAll(splitter, newSplitter);

		

	}

	/**
	 * Method to format a date string from one format to another.
	 * 
	 * @param oldformat The format of the original date string.
	 * @param newFormat The desired format of the new date string.
	 * @param date      The original date string to format.
	 * @return The formatted date string.
	 * @throws ParseException If the provided date string cannot be parsed.
	 */
	public String getDateInDifferentFormat(String oldformat, String newFormat, String date) throws ParseException {
		String newDateString;

		SimpleDateFormat sdf = new SimpleDateFormat(oldformat);
		Date d = sdf.parse(date);
		sdf.applyPattern(newFormat);
		newDateString = sdf.format(d);

		return newDateString;
	}
}