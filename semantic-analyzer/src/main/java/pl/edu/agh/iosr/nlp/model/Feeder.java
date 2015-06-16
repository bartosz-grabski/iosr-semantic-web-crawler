package pl.edu.agh.iosr.nlp.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import pl.edu.agh.iosr.nlp.filters.IFilter;
import pl.edu.agh.iosr.nlp.filters.KeywordFilter;
import pl.edu.agh.iosr.nlp.opennlp.SentenceSplitter;

public class Feeder {
	
	private TFIDFBuilder tfidfBuilder;
	private KeywordMap keywordMap;
	private SentenceSplitter splitter;
	private IFilter filter;
	
	
	public Feeder(KeywordMap keywordMap,TFIDFBuilder tfidfBuilder){
		this.tfidfBuilder = tfidfBuilder;
		this.keywordMap = keywordMap;
		filter = new KeywordFilter();
		splitter = new SentenceSplitter();
	}
	
	public void feed(String feedText){
		String[] input = feedText.split(" ", 2);
		String category = input[0];
		String text = input[1];
		tfidfBuilder.newDoc();
		for(String sentence : splitter.splitSentences(text)){
			Set<String> wordSet = new HashSet<String>();
			String[] words = sentence.split("\\s+");
			List<String> filteredWords = filter.extractKeywords(words);
			for(String word : filteredWords){
				tfidfBuilder.wordOccured(word);
				wordSet.add(word);
			}
			keywordMap.addWords(category, wordSet);
		}
	}
	
}
