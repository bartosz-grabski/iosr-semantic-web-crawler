package pl.edu.agh.iosr.nlp.model;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class TFIDFBuilder {
	private long N = 0;
	
	private Set<String> docWords = new HashSet<String>();
	private Map<String, Metrics> metricsMap = new HashMap<String, TFIDFBuilder.Metrics>();
	
	
	private class Metrics{
		long tf = 0;
		long df = 0;
	}
	
	public void wordOccured(String word){
		if(!metricsMap.containsKey(word)){
			metricsMap.put(word, new Metrics());
		}
		Metrics metrics = metricsMap.get(word);
		if(!docWords.contains(word)){
			docWords.add(word);
			metrics.df++;
		}
		metrics.tf++;
	}
	
	public void newDoc(){
		N++;
		docWords.clear();
	}

	public TFIDFMap build(){
		TFIDFMap map = new TFIDFMap();
		for(Entry<String, Metrics> entry:metricsMap.entrySet()){
			Metrics metrics = entry.getValue();
			Double computedMetric = metrics.tf * Math.log(N/metrics.df);
			map.addWord(entry.getKey(), computedMetric);
		}
		return map;
	}
}
