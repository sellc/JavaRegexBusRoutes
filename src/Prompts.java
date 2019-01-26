import java.util.Scanner;

//Chris Sellers
//1/26/2018
//Assignment 1 - Java Regex Bus Routes

public class Prompts {

	private Scanner input;
	private char destinationLetter;
	private String routeID;

	public Prompts() {
		input = new Scanner(System.in);
		destinationLetter = '\0';
		routeID = "";
	}

	// Close the scanner from outside this class
	public void closeScanner() {
		input.close();
	}

	// Print the destination prompt and retrieve the input letter
	public void destinationLetterPrompt() {
		System.out.println("Please enter the letter that your destination starts with: ");
		destinationLetter = input.nextLine().charAt(0);
	}

	// Get the destination letter
	public char getDestinationLetter() {
		return destinationLetter;
	}

	// Print the route id prompt and retrieve the routeID
	public void routeIDPrompt() {
		System.out.println("\nPlease enter a route ID: ");
		routeID = input.nextLine().replaceAll("/", "-");
		routeID = routeID.replaceAll("/", "-");
	}

	// Get the route ID
	public String getRouteID() {
		return routeID;
	}

	// Print the destination in the proper format
	public void displayDestination(String destination) {
		System.out.println("++++++++++++++++++++++");
		System.out.println("Destination: " + destination);
	}

	// Print the bus number in the proper format
	public void displayBusNumber(String busNumber) {
		System.out.println("Bus Number: " + busNumber);
	}

	// Print the stop number in the proper format
	public void displayStop(String stopNumber, String location) {
		System.out.println("Stop Number " + stopNumber + ": " + location);
	}

	// Print the link
	public void displayLink() {
		System.out.println("The link for your route is: " + getLink());
	}

	// Get the route link
	public String getLink() {
		return "https://www.communitytransit.org/busservice/schedules/route/" + routeID;
	}

}
