package org.example;

import java.util.ArrayList;
import java.util.Set;

public class SuggestedWords {

	public static String wordCheck(String indexWord) {

		// using array list to store the resulting list
		ArrayList<String> resultsArray = new ArrayList<String>();

		// converting to lower-case to have uniform results
		indexWord = indexWord.toLowerCase();
		int minDistance = Integer.MAX_VALUE;

		// Parsed Array stored in Set
		Set<String> wordsArray = WordChecker.htmlParse();
		
      
		for (String word : wordsArray) {
			
			int distance =MinDistance.minDistance(indexWord, word);
			if (distance <= minDistance) {
				minDistance = distance;
			}
			// condition for being similar words is having edit distance of 2
			if (distance <= 2) {
				resultsArray.add(word);
			}
		}
		// Printing list of similar words
		System.out.println("Did you mean " + indexWord + ":");
		System.out.println(resultsArray);
		String word = resultsArray.get(0);
		//System.out.println(word);

		return word;
	}
}
