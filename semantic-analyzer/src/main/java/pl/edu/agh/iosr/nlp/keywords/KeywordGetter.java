package pl.edu.agh.iosr.nlp.keywords;

import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;

import pl.edu.agh.iosr.nlp.filters.KeywordFilter;

public class KeywordGetter {

	private TFIDFMap tfidfMap;
	private KeywordMap keywordMap;
	private KeywordFilter keywordFilter;
	
	
	public KeywordGetter(ModelLoader loader) {
		tfidfMap = loader.loadTFIDFMap();
		keywordMap = loader.loadKeywordMap();
		keywordFilter = new KeywordFilter();
	}
	
	public List<String> getKeywordsForSentenceAndCategory(String category,
			String query, int amount) {
		List<Set<String>> keywords = keywordMap.getCategoryWords(category);
		List<String> splitedQuery = keywordFilter.extractKeywords(query.split(" "));
		SortedSet<String> keywordsSet = new TreeSet<String>(new KeywordComparator());
		for(Set<String> sentence : keywords){
			if(sentence.containsAll(splitedQuery)){
				keywordsSet.addAll(sentence);
			}
		}
		Iterator<String> it = keywordsSet.iterator();
		int i = 0;
		List<String> retval = new LinkedList<String>();
		while(it.hasNext() && i < amount){
			retval.add(it.next());
			i++;
		}
		Collections.reverse(retval);
		return retval;
	}
	
	private class KeywordComparator implements Comparator<String>{

		@Override
		public int compare(String o1, String o2) {
			return tfidfMap.getMetric(o2).compareTo(tfidfMap.getMetric(o1));
		}
		
	}
	
	
}
