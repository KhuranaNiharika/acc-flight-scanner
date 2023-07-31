package org.example;

public class MinDistance {

	// Method for finding min distance between two words
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
}
