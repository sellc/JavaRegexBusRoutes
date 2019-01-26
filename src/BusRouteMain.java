import java.io.IOException;
import java.net.MalformedURLException;

//Chris Sellers
//1/26/2018
//Assignment 1 - Java Regex Bus Routes

public class BusRouteMain {

	public static void main(String[] args) throws MalformedURLException, IOException {
		//Reads in all html from the specified link
		Reader htmlReader = new Reader();
		
		//Contains all matchers
		Matchers matchers = new Matchers();
		
		String scheduleLink = "https://www.communitytransit.org/busservice/schedules/";

		matchers.matchCities(htmlReader.readInHTML(scheduleLink));
		matchers.matchRoutes(htmlReader);

	}

}