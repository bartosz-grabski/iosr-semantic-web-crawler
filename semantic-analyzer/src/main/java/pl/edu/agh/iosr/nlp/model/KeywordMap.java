package pl.edu.agh.iosr.nlp.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class KeywordMap implements Serializable{
	private static final long serialVersionUID = -5797913120131281685L;
	private Map<String, List<Set<String>>> keywords = new HashMap<String, List<Set<String>>>();
	
	public void addWords(String category, Set<String> words){
		if(!keywords.containsKey(category)){
			keywords.put(category, new LinkedList<Set<String>>());
		}
		List<Set<String>> categoryWords = keywords.get(category);
		categoryWords.add(words);
	}
	
	public List<Set<String>> getCategoryWords(String category){
		return keywords.get(category);
	}
}
