package org.example;

import java.io.FileReader;
//required libraries
import java.io.IOException;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import java.io.BufferedReader;

import java.util.HashSet;
import java.util.Set;

public class WordChecker {
/*
	// Function to calculate minimum distance
	public static int minDistance(String firstWrd, String secondWrd) {

		// assigning length of both words to variables
		int x = firstWrd.length();
		int y = secondWrd.length();

		// assigning both lengths to an array
		int[][] editDist = new int[x + 1][y + 1];

		// Dynamic Programming algorithm
		for (int i = 0; i <= x; i++)
			editDist[i][0] = i;
		for (int i = 1; i <= y; i++)
			editDist[0][i] = i;

		for (int i = 0; i < x; i++) {
			for (int j = 0; j < y; j++) {
				if (firstWrd.charAt(i) == secondWrd.charAt(j))
					editDist[i + 1][j + 1] = editDist[i][j];
				else {
					int a = editDist[i][j];
					int b = editDist[i][j + 1];
					int c = editDist[i + 1][j];
					editDist[i + 1][j + 1] = a < b ? (a < c ? a : c) : (b < c ? b : c);
					editDist[i + 1][j + 1]++;
				}
			}
		}
		return editDist[x][y];
	}
*/
	
	// Function to parse HTML file and store in an Array
	static Set<String> htmlParse() {

		// fetching document
		String url = "http://www.citymayors.com/gratis/canadian_cities.html";

		Document doc;

		// adding try block to check for exceptions
		try {
			doc = Jsoup.connect(url).get();
			String body = doc.body().text();
			// adding words in an array


			String[] str = body.split("\\s+");
			Set<String> name = new HashSet<>();
			for (int i = 0; i < str.length; i++) {
				// deleting non alphanumeric characters
				name.add(str[i].replaceAll("[^\\w]", "").toLowerCase());
			}

			// System.out.println("Length of string is: "+ str.length);

			return name;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}
	
	
	
	
	
}
