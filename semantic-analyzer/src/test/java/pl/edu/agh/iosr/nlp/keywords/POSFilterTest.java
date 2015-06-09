package pl.edu.agh.iosr.nlp.keywords;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class POSFilterTest {
	public static void test(){
		String text = "Carl Nielsen was a Danish musician conductor and violinist widely recognized as his country's greatest composer Brought up by poor musically talented parents he attended the Royal Conservatory in Copenhagen from 1884 through 1886 and premiered his Op 1 Suite for Strings at the age of 23 The following year he began a 16-year stint as a second violinist in the Royal Danish Orchestra under the conductor Johan Svendsen and later taught at the Royal Danish Academy of Music from 1916 until his death While his symphonies concertos and choral music are now internationally acclaimed Nielsen's career and personal life were marked by many difficulties often reflected in his music";
		POSFilter posFilter = new POSFilter();
		List<String> list = posFilter.extractKeywords(text.split(" "));
		String expected = "Carl Nielsen was Danish musician conductor violinist widely recognized country's greatest composer Brought poor musically talented parents attended Royal Conservatory Copenhagenpremiered Suite Strings age following year began 16-year stint second violinist Royal Danish Orchestra under conductor Johan Svendsen later taught Royal Danish Academy Music death symphonies concertos choral music now internationally acclaimed Nielsen's career personal life were marked many difficulties often reflected music";
		List<String> expectedList = Arrays.asList(expected.split(" "));
		int correct = 0, bad = 0;
		
		for(String word : expectedList){
			if(list.contains(word)){
				correct++;
			}
		}
		for(String word : list){
			if(!expectedList.contains(word)){
				bad++;
			}
		}
		
		int expCorr = expectedList.size();
		
		System.out.println("POS Filter Test");
		System.out.println("correct:"+correct);
		System.out.println("bad:"+bad);
		System.out.println("expected correct:"+expCorr);
		
	}
	
	public static void main(String[] args){
		test();
	}
}
