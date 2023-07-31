package org.example;

import java.io.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WordFrequency {

	// main method
	// public static void main(String[] args) {
	// String[] strArr = htmlParse();
	// hashTable(strArr);
	// }

	// creating separate method to parse from HTML

	static String[] htmlParse(String file_Name) {

		// fetching document
		String fileName = file_Name;

		Document doc;

		// adding try block to check for exceptions
		try {
			doc = Jsoup.parse(new File(fileName), "utf-8");
			String body = doc.body().text();

			// adding words in an array
			String[] str = body.split("\\s+");
			for (int i = 0; i < str.length; i++) {
				// deleting non alphanumeric characters
				str[i] = str[i].replaceAll("[^\\w]", "");
			}

			// System.out.println("Length of string is: "+ str.length);

			return str;

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return null;

	}

	// inserting words in hash table

	static void hashTable(String[] s) {
		// initiating
		HashMap<String, Integer> frequency = new HashMap<String, Integer>();
		// counting word frequency
		for (int i = 0; i < s.length; i++) {
			if (s[i].equals("")) {
				continue;
			}
			if (frequency.get(s[i].toLowerCase()) != null) {

				int n = frequency.get(s[i].toLowerCase());
				frequency.put(s[i].toLowerCase(), n + 1);
			} else {
				frequency.put(s[i].toLowerCase(), 1);
			}
		}
		System.out.println("Word frequency as follows");
		// printing key and value pair
		List<Map.Entry<String, Integer>> entryList = new ArrayList<>(frequency.entrySet());
        Collections.sort(entryList, new Comparator<Map.Entry<String, Integer>>() {
            public int compare(Map.Entry<String, Integer> o1, Map.Entry<String, Integer> o2) {
                return o1.getValue().compareTo(o2.getValue());
            }
        });

        for (Map.Entry<String, Integer> entry : entryList) {
            System.out.println(entry.getKey() + " "+ entry.getValue());
        }
	}
}
