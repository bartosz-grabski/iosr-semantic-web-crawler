package pl.edu.agh.iosr.nlp.keywords;

import java.util.List;

public class Main {
	public static void main(String[] args){
		if(args.length<3){
			warn();
		}
		
		if(args[0].equals("add")){
			KeywordDocController kdc = new KeywordDocController();
			KeywordAdder adder = new KeywordAdder(kdc);
			adder.addKeywords(args[1], args[2]);
			System.exit(0);
		}else if(args[0].equals("get")){
			KeywordDocController kdc = new KeywordDocController();
			KeywordGetter getter = new KeywordGetter(kdc);
			List<String> list = getter.getKeywordsForSentenceAndCategory(args[1], args[2], 5);
			for(String word : list){
				System.out.println(word);
			}
			System.exit(0);
		}
		warn();
		
	}
	
	private static void warn(){
		System.out.printf("arguments: (add|get) <category> \"<sentence>\" ");
		System.exit(1);
	}
}
