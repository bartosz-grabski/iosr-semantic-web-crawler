package pl.edu.agh.iosr.nlp.filters;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class StopwordsFilter implements IFilter {

	private Set<String> stopwords;
	
	public StopwordsFilter() {
		stopwords = new HashSet<String>();
		init();
	}
	
	private void init() {
		try {
			FileReader fr = new FileReader("resources/stoplist.txt");
			BufferedReader br = new BufferedReader(fr);
			String word;
			while((word=br.readLine())!=null){
				stopwords.add(word);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public List<String> extractKeywords(String[] words) {
		List<String> filteredList = new LinkedList<String>();
		for(String word : words){
			if(!stopwords.contains(word.toLowerCase())){
				filteredList.add(word);
			}
		}
		return filteredList;
	}

}
