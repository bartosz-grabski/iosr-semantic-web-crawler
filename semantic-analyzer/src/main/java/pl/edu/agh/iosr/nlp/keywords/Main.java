package pl.edu.agh.iosr.nlp.keywords;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class Main {
	public static void main(String[] args){
		if(args.length!=2 && args.length!=3){
			warn();
		}
		
		if(args[0].equals("query")){
			String query = args[1];
			
			
			KeywordDocController kdc = new KeywordDocController();
			KeywordGetter getter = new KeywordGetter(kdc);
			List<String> list = getter.getKeywordsForSentenceAndCategory(args[1], args[2], 3);
			for(String word : list){
				System.out.println(word);
			}
			System.exit(0);
		}else if(args[0].equals("feed")){
			KeywordDocController kdc = new KeywordDocController();
			KeywordAdder adder = new KeywordAdder(kdc);
			Feeder feeder = new Feeder(adder);
			BufferedReader br = null;
			try {
				br = new BufferedReader(new FileReader(args[1]));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
			String line;
			try {
				while((line=br.readLine())!=null){
					feeder.feed(line);
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
			
			System.exit(0);
		}
		warn();
		
	}
	
	private static void warn(){
		System.out.printf("bad args");
		System.exit(1);
	}
}
