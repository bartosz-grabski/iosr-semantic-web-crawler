package pl.edu.agh.iosr.nlp.keywords;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import pl.edu.agh.iosr.nlp.opennlp.Categorizer;
import pl.edu.agh.iosr.nlp.opennlp.Categorizer.Result;

public class Main {
	public static void main(String[] args){
		if(args.length!=2 && args.length!=3){
			warn();
		}
		
		if(args[0].equals("query")){
			
			List<String> keywords = getSimiliarKeywords(args[1], 5);
			for(String word : keywords){
				System.out.println(word);
			}
			System.exit(0);
		}else if(args[0].equals("feed")){
			createModel(args[1]);
			
			System.exit(0);
		}
		warn();
		
	}
	
	public static List<String> getSimiliarKeywords(String query, int amount){
		Categorizer categorizer = new Categorizer();
		Result res = categorizer.categorize(query);
		System.out.println(res.category);
		ModelLoader loader = new ModelLoader();
		KeywordGetter getter = new KeywordGetter(loader);
		List<String> list = getter.getKeywordsForSentenceAndCategory(res.category,query, amount);
		return list;
	}
	
	private static void createModel(String path){
		KeywordMap keywordMap = new KeywordMap();
		TFIDFBuilder tfidfBuilder = new TFIDFBuilder();
		Feeder feeder = new Feeder(keywordMap,tfidfBuilder);
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
		ModelSaver saver = new ModelSaver();
		saver.saveKeywordMap(keywordMap, "resources/kmap.dat");
		saver.saveTFIDFMap(tfidfBuilder.build(), "resources/tfidf.dat");
	}
	
	private static void warn(){
		System.out.printf("bad args");
		System.exit(1);
	}
}
