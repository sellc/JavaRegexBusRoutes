import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

//Chris Sellers
//1/26/2018
//Assignment 1 - Java Regex Bus Routes

public class Reader {

	private URLConnection connection;
	private BufferedReader in;
	private String inputLine;
	private String text;

	//Constructor
	public Reader() {
		connection = null;
		in = null;
		inputLine = text = "";
	}

	//Read in the html from the provided link
	public String readInHTML(String url) {
		//set URL and open connection first
		setURL(url);
		try {
			//Read in while input left
			in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			while ((inputLine = in.readLine()) != null) {
				text += inputLine + "\n";
			}
			in.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return text;
	}

	//Set the url to the provided link
	private void setURL(String link) {
		try {
			connection = new URL(link).openConnection();
			connection.setRequestProperty("user-Agent",
					"Mozilla/5.0 (Windows NT 6.1; WOW64) AppleWebKit/537.11 (KHTML, like Gecko) Chrome/23.0.1271.95 Safari/537.11");
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
