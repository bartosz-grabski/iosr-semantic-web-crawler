package pl.edu.agh.iosr.nlp.keywords;

import java.util.List;

public class KeywordFilter implements IFilter {

	private POSFilter posFilter = new POSFilter();
	private StopwordsFilter stopwordsFilter = new StopwordsFilter();
	
	@Override
	public List<String> extractKeywords(String[] words) {
		return posFilter.extractKeywords(stopwordsFilter.extractKeywords(words).toArray(new String[]{}));
	}

}
