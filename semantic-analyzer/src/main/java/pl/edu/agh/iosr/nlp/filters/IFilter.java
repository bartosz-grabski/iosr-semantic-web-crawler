package pl.edu.agh.iosr.nlp.filters;

import java.util.List;

public interface IFilter {
	List<String> extractKeywords(String[] words);
}
