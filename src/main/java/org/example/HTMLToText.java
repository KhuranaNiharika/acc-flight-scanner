package org.example;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class HTMLToText {

	// Method for crawling link using Jsoup and creating HTML files of that links
	public static void DynamicHTMLtoTextConverter(ArrayList<String> linkList) throws Exception {

		System.out.println("-----------------------------------------------------");
		System.out.println("Links Crawling: \n");
		int inc = 0;
		for (String s : linkList) {

			inc++;
			Document docNewLink = Jsoup.connect(s).get();
			String html = docNewLink.html();

			String regex = "[a-zA-Z0-9]+";
			Pattern p2 = Pattern.compile(regex);
			Matcher m2 = p2.matcher(s);
			StringBuffer sb = new StringBuffer();
			while (m2.find()) {
				sb.append(m2.group(0));
			}

			String linkAdress = sb.substring(0);
			System.out.println("link --- " + linkAdress);

			// Creating HTML files
			PrintWriter out = new PrintWriter(CommonConstants.webPagesPath + "\\webPage\\" + linkAdress + ".html");
			out.println(html);
			out.close();

			if (inc == 20) {
				break;
			}
		}

		File myfile = new File(CommonConstants.webPagesPath + "webPage\\");
		String[] fileList = myfile.list();

		for (int i = 0; i < fileList.length; i++) {
			ConvertHtmlToText(fileList[i], "crawled");
		}
	}

	// Method for converting HTML to Text file
	public static void ConvertHtmlToText(String file, String type) throws Exception {
		String folderToFetch;
		if (type.equals("fixed")) {
			folderToFetch = CommonConstants.webPagesPath + "webPage\\";
		} else {
			folderToFetch = CommonConstants.webPagesPath + "webPage\\";
		}
		File f1 = new File(folderToFetch + "\\" + file);

		// Parse the file using JSoup
		Document doc = Jsoup.parse(f1, "UTF-8");

		// Convert the file to text
		String str = doc.text();

		PrintWriter pw = new PrintWriter(
				CommonConstants.webPagesPath + "\\textFile\\" + file.replaceAll(".html", ".txt"));
		pw.println(str);
		pw.close();
	}
}
