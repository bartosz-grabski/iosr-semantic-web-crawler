package pl.edu.agh.iosr.nlp.keywords;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import pl.edu.agh.iosr.nlp.Categorizer;
import pl.edu.agh.iosr.nlp.Categorizer.Result;

public class Main {
	public static void main(String[] args){
		if(args.length!=2 && args.length!=3){
			warn();
		}
		
		if(args[0].equals("query")){
			Categorizer categorizer = new Categorizer();
			Result res = categorizer.categorize(args[1]);
			System.out.println(res.category);
			List<String> keywords = getSimiliarKeywords(args[1], res.category, 5);
			for(String word : keywords){
				System.out.println(word);
			}
			System.exit(0);
		}else if(args[0].equals("feed")){
			feedDB(args[1]);
			
			System.exit(0);
		}
		warn();
		
	}
	
	public static List<String> getSimiliarKeywords(String query, String category, int amount){
		KeywordDocController kdc = new KeywordDocController();
		KeywordGetter getter = new KeywordGetter(kdc);
		System.out.println("!");
		List<String> list = getter.getKeywordsForSentenceAndCategory(category,query, amount);
		return list;
	}
	
	private static void feedDB(String path){
		KeywordDocController kdc = new KeywordDocController();
		KeywordAdder adder = new KeywordAdder(kdc);
		Feeder feeder = new Feeder(adder);
		BufferedReader br = null;
		try {
			br = new BufferedReader(new FileReader(path));
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
	}
	
	private static void warn(){
		System.out.printf("bad args");
		System.exit(1);
	}
}
