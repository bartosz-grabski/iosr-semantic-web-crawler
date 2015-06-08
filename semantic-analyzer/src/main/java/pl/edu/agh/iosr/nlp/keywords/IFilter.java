package pl.edu.agh.iosr.nlp.keywords;

import java.util.List;

public interface IFilter {
	List<String> extractKeywords(String[] words);
}
