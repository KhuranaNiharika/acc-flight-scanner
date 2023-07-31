package org.example;

import org.jsoup.nodes.Document;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class FScanner {
	private static Scanner myObj;
	private static Scanner sc;

	public static void main(String[] args) throws Exception {
		String blueColor = "\u001B[34m";
		String boldText = "\u001B[1m";
		String redColor = "\u001B[31m";
		String greenColor = "\u001B[32m";
		String yellowColor = "\u001B[33m";
		String magentaColor = "\u001B[35m";
		String cyanColor = "\u001B[36m";
		String whiteColor = "\u001B[37m";
        String resetColor = "\u001B[0m";

        String bgBlackColor = "\u001B[40m";
        String bgRedColor = "\u001B[41m";
        String bgGreenColor = "\u001B[42m";
        String bgYellowColor = "\u001B[43m";
        String bgBlueColor = "\u001B[44m";
        String bgMagentaColor = "\u001B[45m";
        String bgCyanColor = "\u001B[46m";
        String bgWhiteColor = "\u001B[47m";
        
        String underlineText = "\u001B[4m";
        String invertedText = "\u001B[7m";
        String reset = "\u001B[0m";

		File frequencyfile = new File("./src/search_frequency.txt");

		// Deleting all generated .html and .txt files
		StringFileManipulation.DeleteMultipleFiles(CommonConstants.webPagesPath + "webPage\\", ".html");
		StringFileManipulation.DeleteMultipleFiles(CommonConstants.webPagesPath + "textFile\\", ".txt");

		// Checking search_frequency file exists
		if (frequencyfile.exists()) {

			List<String> words = new ArrayList<String>();

			Scanner myReader = new Scanner(frequencyfile);
			while (myReader.hasNextLine()) {
				words.add(myReader.nextLine());
			}
			myReader.close();

			// Calculating occurence of each words in the search_frequency text file
			Map<String, Integer> wordfreq = new HashMap<>();
			for (String word : words) {
				Integer integer = wordfreq.get(word);
				if (integer == null)
					wordfreq.put(word, 1);
				else {
					wordfreq.put(word, integer + 1);
				}
			}
			
	        System.out.println(blueColor +boldText+ " Most Searched Destinations of 2023 " + resetColor);
	        System.out.println("");
			//System.out.println("This Year's Most Searched Destinations:");

			for (String key : wordfreq.keySet()) {
				System.out.println(key.toUpperCase() + " ==> " + wordfreq.get(key) + " times");
			}
	        System.out.println("");

			//System.out.println("=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=-=");
	        System.out.println("");

		} else {
			frequencyfile.createNewFile();
		}

		myObj = new Scanner(System.in);

		// Reading the data from the Airports.csv file and storing it into data variable
		File airports = new File("Airports.csv");
		Scanner myReader = new Scanner(airports);
		String data = " ";
		while (myReader.hasNextLine()) {
			data = data + myReader.nextLine();

		}

		// Spliting data by ";" and "," and storing it into citiesCode hashmap as key value pairs
		String[] airport = data.split(";");
		Map<String, String> citiesCodes = new HashMap<String, String>();
		for (int i = 0; i < airport.length; i++) {
			String[] airportData = airport[i].split(",");
			citiesCodes.put(airportData[1], airportData[0]);
		}

		// Creating cities list from citiesCode hashmap
		ArrayList<String> cities = new ArrayList<>();
		int index = 1;
		for (String e : citiesCodes.keySet()) {
			cities.add(e);
		}

		// Displaying list of cities with an index
		System.out.println(greenColor +boldText +"Please pick one of the following options for source and destination: "+reset);
		System.out.println();
		System.out.println("........................................");
		for (String e : cities) {
			System.out.println(index + ". " + e);
			index++;
		}

		System.out.println("........................................");
		System.out.println();

		// Obtaining the name of the source city from the user
		System.out.println(magentaColor +underlineText +"Now enter your source city"+reset);
		String src = myObj.nextLine();

		// Validation for city name
		while (!src.matches("[a-zA-Z_]+")) {
			System.out.println(redColor +" OOPS!! , Its an invalid city . Please select from the list ."+reset);
			System.out.println();
			src = myObj.nextLine();
		}

		src = src.toLowerCase();
		String fileword = src + "\n";
		src = StringFileManipulation.capitalize(src).trim();
		String source = citiesCodes.get(src);

		if (source == null) {
			source = SuggestedWords.wordCheck(src);
			if (source == null || !citiesCodes.values().contains(source)) {
				System.out.println(redColor +" OOPS!! , Its an invalid city . Please select from the list ."+reset);
				src = myObj.nextLine();
				System.out.println();
			} else {
				fileword = source + "\n";
				System.out.println(fileword);
				source = StringFileManipulation.capitalize(source).trim();
				source = citiesCodes.get(source).trim();
			}
		}

		// Adding source city name into search_frequency text file
		Files.write(Paths.get("src/search_frequency.txt"), fileword.getBytes(), StandardOpenOption.APPEND);

		// Implemented Djikstra algorithm for source city
		//NearestToSource.djikstra(src);

		System.out.println("");

		// Obtaining the name of the destination city from the user
		System.out.println(magentaColor +underlineText +"Enter your destination city"+reset);
		String dest = myObj.nextLine();

		// Validation for city name
		while (!dest.matches("[a-zA-Z_]+")) {
			System.out.println(redColor +" OOPS!! , Its an invalid city . Please select from the list ."+reset);
			dest = myObj.nextLine();
		}

		dest = dest.toLowerCase();
		fileword = dest + "\n";
		dest = StringFileManipulation.capitalize(dest).trim();
		String destination = citiesCodes.get(dest);

		if (destination == null) {
			destination = SuggestedWords.wordCheck(dest);
			if (destination == null || !(citiesCodes.values().contains(destination))) {
				System.out.println(redColor +" OOPS!! , Its an invalid city . Please select from the list ."+reset);
				src = myObj.nextLine();
				System.out.println();
			} else {
				fileword = destination + "\n";
				System.out.println(fileword);
				destination = StringFileManipulation.capitalize(destination).trim();
				destination = citiesCodes.get(destination).trim();
			}

		}

		// Adding destination city name into search_frequency text file
		Files.write(Paths.get("src/search_frequency.txt"), fileword.getBytes(), StandardOpenOption.APPEND);

		System.out.println("");

		// Obtaining the date of departure from the user in yyyymmdd format
		System.out.println(cyanColor + boldText +"When you want to fly  ?  (Enter : yyyymmdd)"+reset);
		String departureDate = myObj.nextLine();

		// Date validation
		while (!(DateValidation.isDateValid(departureDate) && Integer.parseInt(departureDate) > Integer
				.parseInt(java.time.LocalDate.now().toString().replaceAll("-", "")))) {
			System.out.println(redColor +"Please enter the Correct departure date"+reset);
			departureDate = myObj.nextLine();
		}

		System.out.println("");

		// Getting information from the user on the total number of passengers
		System.out.println(greenColor+"Enter the number of passengers"+reset);
		String count = myObj.nextLine();

		// Count validation
		while (Integer.parseInt(count) <= 0) {
			System.out.println(redColor+"Please enter valid number of passengers"+reset);
			count = myObj.nextLine();
		}

		// Url for cheapflights website with all the parameters
		String url = "https://www.cheapflights.ca/flight-search/" + source.trim() + "-" + destination.trim() + "/"
				+ DateValidation.dateFormatterf(departureDate) + "/" + count +"adults?sort=price_a";

				//"adults?sort=bestflight_a";
		System.out.println();
		System.out.println("************************************");
		System.out.println(yellowColor+"Just a moment! Fetching budget-friendly flight options for you!"+reset);

		System.out.println("************************************");


		// url crawling
		Document document = GetDataOfFlights.request(url);

		// Creating text files from the html files
		document.text();
		String newTitle = source + destination;
		BufferedWriter writer = new BufferedWriter(new FileWriter(CommonConstants.webPagesPath + newTitle + ".txt"));
		writer.write(document.text().toLowerCase());
		writer.close();

		// Displaying each word's overall frequency in ascending order
		System.out.println(magentaColor+"\n\n======================== Frequency Count ========================\n\n"+reset);
		frequencyCounter(newTitle);
		
		System.out.println(greenColor+invertedText+boldText+"Thank you!! Please visit again for cheap flight search "+reset);
	}

	// Method for frequency count of each words in text file
	public static void frequencyCounter(String newTitle) {
		String[] strArr = WordFrequency.htmlParse(CommonConstants.webPagesPath + newTitle + ".txt");
		WordFrequency.hashTable(strArr);
	}

	

}
