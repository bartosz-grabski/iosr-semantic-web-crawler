package pl.edu.agh.iosr.nlp.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

public class TFIDFMap implements Serializable{
	private static final long serialVersionUID = -1465489540450539844L;
	private Map<String, Double> tfidfMap = new HashMap<String, Double>();
	
	public void addWord(String word, Double metric){
		tfidfMap.put(word, metric);
	}
	
	public Double getMetric(String word){
		return tfidfMap.get(word);
	}
}
