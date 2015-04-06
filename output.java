package recordLinkage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;

public class output {
	public static void main(String args[]) throws Exception{
		File file = new File("test.csv");
		BufferedReader reader = new BufferedReader(new FileReader(file));
		BufferedWriter writer = new BufferedWriter(new FileWriter("results.csv"));
		String line = null;
		int i = 0;
		line = reader.readLine();
		while((line = reader.readLine()) != null){
			String num2 = line.split(",")[5].replaceAll("\"", "");
			String num1 = line.split(",")[0].replaceAll("\"", "");
			System.out.println(i + ": " + num2 + "," + num1);
			writer.write(num2 + "," + num1 + "\r\n");
			i++;
		}
		reader.close();
		writer.close();
	}
}
