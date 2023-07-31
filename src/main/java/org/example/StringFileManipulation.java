package org.example;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;

import org.jsoup.Jsoup;

public class StringFileManipulation {
    public static String capitalize(String str) {
		if (str == null || str.length() == 0)
			return str;
		return str.substring(0, 1).toUpperCase() + str.substring(1);
	}

	public static String makeURL(String source, String destination, String departureDate, String count) {
		return "https://www.cheapflights.ca/flight-search/" + source.trim() + "-" + destination.trim() + "/"
				+ DateValidation.dateFormatterf(departureDate) + "/" + count + "adults?sort=bestflight_a";
	}
	
	public static boolean IsNumAlpha(String str) {
		return str != null && str.matches("^[a-zA-Z0-9]*$");
	}
	
	public static void writeFile(String name, String content) throws Exception {
		File f = new File("./src/" + name + ".txt");
		if (f.exists())
			f.delete();
		f.createNewFile();
		BufferedWriter bw = new BufferedWriter(new FileWriter(f));
		bw.write(content);
		bw.close();
	}

    public static String[] readFile(String filePath) {
		try {
			if (new File(filePath).isFile())
				return Jsoup.parse(new File(filePath), "utf-8").body().text().split("\\s+");
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return null;
	}

	public static void DeleteMultipleFiles(String folderPath, String extensionToDelete){
		File folder = new File(folderPath);
        
        if (folder.isDirectory()) {
            File[] files = folder.listFiles();
            
            if (files != null) {
                for (File file : files) {
                    if (file.isFile() && file.getName().endsWith(extensionToDelete)) {
                        boolean deleted = file.delete();
                        if (deleted) {
                            System.out.println("Deleted: " + file.getName());
                        } else {
                            System.out.println("Failed to delete: " + file.getName());
                        }
                    }
                }
            }
        } else {
            System.out.println("The given path is not a directory.");
        }

	}

}
