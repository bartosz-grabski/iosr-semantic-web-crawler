package pl.edu.agh.iosr.nlp.keywords;

import java.util.List;

public class KeywordAdder {
	private KeywordDocController controller;
	private IFilter filter;
	
	public KeywordAdder(KeywordDocController controller) {
		this.controller = controller;
		filter = new KeywordFilter();
	}
	
	public void addKeywords(String category, String sentence){
		String[] words = sentence.split("\\s+");
		List<String> keywords = filter.extractKeywords(words);
		controller.addNewKeywords(keywords, category);
	}
}
