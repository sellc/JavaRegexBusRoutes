import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

//Chris Sellers
//1/26/2018
//Assignment 1 - Java Regex Bus Routes

public class Matchers {
	
	private Matcher matcher;
	private ArrayList<String> cities;
	private ArrayList<String> routes;

	private Prompts prompts;
	
	//Constructor
	public Matchers() {
		matcher = null;
		cities = new ArrayList<String>();
		routes = new ArrayList<String>();
		prompts = new Prompts();
	}
	
	//Match all cities within the provided text
	public void matchCities(String text) {
		Pattern cityPattern = Pattern.compile("<h3>(.*)</h3>");
		matcher = cityPattern.matcher(text);
		while (matcher.find()) {
			cities.add(matcher.group(1));
		}
		matchBusses(text);
	}
	
	//Match all bus numbers within the provided text
	private void matchBusses(String text) {
		int index = 0;
		int lastEndIndex = 0;
		Pattern busPattern = Pattern.compile(
				"\\s*<div class=\"row Community\">" + "\\s*.*\\s.*" + "\\s*<strong>.*\\shref=\"(.*)\"\\s*.*>(.*)</a>.*"
						+ "\\s*.*" + "\\s*.*\\s.*\\s.*>(.*).*" + "\\s*</div>");
		matcher = busPattern.matcher(text);
		
		//Must prompt for city letter before trying to match
		prompts.destinationLetterPrompt();
		while (matcher.find()) {
			//New city indicator
			if (matcher.start() - lastEndIndex > 0) {
				//Check if first letter of city matches 
				if (cities.get(index).charAt(0) == prompts.getDestinationLetter()) {
					prompts.displayDestination(cities.get(index));
				}
				index++;
			}
			//Bus numbers of the last matched city are displayed
			if (cities.get(index - 1).charAt(0) == prompts.getDestinationLetter()) {
				prompts.displayBusNumber(matcher.group(2));
			}
			lastEndIndex = matcher.end();
		}
	}
	
	//Match all routes from within the specified link
	public void matchRoutes(Reader htmlReader) {
		//Indicate the start and stop of the match
		int start = 0;
		int stop = 0;
		
		//Must prompt for ID first before reading html
		prompts.routeIDPrompt();
		String text = htmlReader.readInHTML(prompts.getLink());
		Pattern routePattern = Pattern.compile("<h2>Weekday<small>(.*)</small></h2>");
		matcher = routePattern.matcher(text);
		while (matcher.find()) {
			routes.add(matcher.group(1));
			if(start == 0) {
				start = matcher.start();
			}
			if(stop < matcher.end()) {
				stop = matcher.end();
			}
		}
		prompts.displayLink();
		matchStops(text, start, stop);
	}
	
	//Match all stops on the specified route for the weekdays only
	private void matchStops(String text, int start, int stop) {
		int index = 0;
		int lastEndIndex = 0;
		Pattern stopsPattern = Pattern.compile("<th class=\"text-center\">" + "\\s*<span class=\"fa-stack\">"
				+ "\\s*<i class=\"fa fa-circle-thin fa-stack-2x\"></i>" + "\\s*.*\\s.*\\s.*>(.*)</strong>"
				+ "\\s*.*" + "\\s*<p>(.*)</p>" + "\\s*</th>");
		matcher = stopsPattern.matcher(text.substring(start, stop+(stop-start)));
		while (matcher.find()) {
			//Checks if the stops for the next route are being matched
			if (matcher.start() - lastEndIndex > 49) {
				prompts.displayDestination(routes.get(index));
				index++;
			}
			prompts.displayStop(matcher.group(1), matcher.group(2));
			lastEndIndex = matcher.end();
		}
		prompts.closeScanner();
	}

}
