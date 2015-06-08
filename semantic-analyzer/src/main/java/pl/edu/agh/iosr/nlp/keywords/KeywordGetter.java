package pl.edu.agh.iosr.nlp.keywords;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class KeywordGetter {
	private KeywordDocController controller;
	private IFilter filter;

	public KeywordGetter(KeywordDocController controller) {
		this.filter = new KeywordFilter();
		this.controller = controller;
	}

	public List<String> getKeywordsForSentenceAndCategory(String category,
			String sentence, int n) {
		String[] words = sentence.split("\\s+");
		List<String> filteredWords = filter.extractKeywords(words);
		List<List<String>> filteredLists = getFilteredLists(category,
				filteredWords);
		System.out.println(filteredWords);
		Map<String, Integer> countMap = getCountMap(filteredLists);
		ArrayList<Entry<String, Integer>> array = new ArrayList<Map.Entry<String, Integer>>(
				countMap.entrySet());
		Collections.sort(new ArrayList<>(countMap.entrySet()),
				new EntryComparator());
		List<String> finalList = new LinkedList<>();
		for (int i = 0; i < n && i < array.size(); i++) {
			finalList.add(array.get(i).getKey());
		}
		return finalList;

	}

	private List<List<String>> getFilteredLists(String category,
			List<String> words) {
		Iterator<List<String>> it = controller
				.getCategoryKeywordsIterator(category);
		List<List<String>> list = new LinkedList<>();
		while (it.hasNext()) {
			List<String> coll = it.next();
			if (isAllInCollection(words, coll)) {
				list.add(coll);
			}
		}
		return list;
	}

	private Map<String, Integer> getCountMap(List<List<String>> keywords) {
		Map<String, Integer> map = new HashMap<>();
		for (List<String> set : keywords) {
			for (String word : set) {
				Integer count = map.getOrDefault(word, 0);
				count++;
				map.put(word, count);
			}
		}
		return map;
	}

	private boolean isAllInCollection(List<String> words, List<String> coll) {
		
		Iterator<String> it = words.iterator();
		while (it.hasNext()) {
			if (!coll.contains(it.next())) {
				return false;
			}
		}
		return true;
	}

	private class EntryComparator implements Comparator<Entry<String, Integer>> {

		@Override
		public int compare(Entry<String, Integer> o1, Entry<String, Integer> o2) {
			return o2.getValue().compareTo(o1.getValue());
		}

	}
}
