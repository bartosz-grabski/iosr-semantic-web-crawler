package pl.edu.agh.iosr.nlp.keywords;

import java.util.List;

public class KeywordAdder {
	private KeywordDocController controller;
	private POSFilter filter;
	
	public KeywordAdder(KeywordDocController controller) {
		this.controller = controller;
		filter = new POSFilter();
	}
	
	public void addKeywords(String category, String sentence){
		List<String> keywords = filter.extractKeywords(sentence);
		controller.addNewKeywords(keywords, category);
	}
}
