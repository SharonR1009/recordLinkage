package recordLinkage;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;

public class f1Score {
	public static void main(String args[]) throws Exception{
		String[] truth = readTruth("groundtruth.csv");
		float result[] = readResult("test.csv", truth);
		float recall = (result[0]-1)/112;
		float precision = (result[0]-1)/(result[1] - 1);
		float f1 = 2* (precision*recall)/(precision + recall);
		System.out.println("true positive: " + (result[0] - 1));
		System.out.println("total linkage founded: " + (result[1] - 1));
		System.out.println("precision: " + precision);
		System.out.println("recall: " + recall);
		System.out.println("f1: " + f1);
	}
	public static float[] readResult(String path, String[] turth) throws Exception{
		File file = new File(path);
		BufferedReader reader = new BufferedReader(new FileReader(file));
		String line = null;	
		int flag = 0;
		int count = 1;
		int total = 1;
		float[] result = new float[2];
		line = reader.readLine();
		while((line = reader.readLine()) != null){
			total++;
			String num1 = line.split(",")[0].trim().replaceAll("\"", "");
			String name1 = line.split(",")[1].trim().replaceAll("\"", "");
			String addr1 = line.split(",")[2].trim().replaceAll("\"", "");
			String phone1 = line.split(",")[3].trim().replace("/", "-").replaceAll("\"", "");
			String country1 = line.split(",")[4].trim().replaceAll("\"", "");
			String num2 = line.split(",")[5].trim().replaceAll("\"", "");
			String name2 = line.split(",")[6].trim().replaceAll("\"", "");
			String addr2 = line.split(",")[7].trim().replaceAll("\"", "");
			String phone2 = line.split(",")[8].trim().replaceAll("\"", "");
			String country2 = line.split(",")[9].trim().replaceAll("\"", "");
			for(int i = 0; i < 224; i++){
				if(turth[i].trim().contains(name1) && turth[i].trim().contains(addr1) && turth[i].trim().contains(phone1) && turth[i].trim().contains(country1)){
					flag++;
					//System.out.println(i + ": " + turth[i]);
					//System.out.println("d1" + ": " + num1 + " " + name1 + " " + addr1 + " " + phone1 + " " + country1);
				}
				if(turth[i].trim().contains(name2) && turth[i].trim().contains(addr2) && turth[i].trim().trim().contains(phone2) && turth[i].trim().contains(country2)){
					//System.out.println(i + ": " + turth[i]);
					//System.out.println("d2" + ": " + num2 + " " + name2 + " " + addr2 + " " + phone2 + " " + country2);
					flag++;
				}
			}
			if(flag >= 2){
				System.out.println(count + ": " + num2 + "," + num1);
				//System.out.println("1" + ": " + num1 + " " + name1 + " " + addr1 + " " + phone1 + " " + country1);
				//System.out.println("2" + ": " + num2 + " " + name2 + " " + addr2 + " " + phone2 + " " + country2);
				count++;
			}
			flag = 0;
		}
		result[0] = count;
		result[1] = total;
		return result;
		
	}
	public static String[] readTruth(String path) throws Exception{
		File file = new File(path);
		BufferedReader truthReader = new BufferedReader(new FileReader("groundtruth.csv"));
		String truthLine = null;
		int i = 0;
		String[] result = new String[224];
		while((truthLine = truthReader.readLine()) != null){
			result[i] = truthLine;
			//System.out.println(result[i]);
			i++;
			
		}
		return result;
	}
}
