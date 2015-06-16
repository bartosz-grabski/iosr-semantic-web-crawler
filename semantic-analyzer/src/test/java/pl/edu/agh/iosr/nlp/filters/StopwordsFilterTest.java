package pl.edu.agh.iosr.nlp.filters;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import pl.edu.agh.iosr.nlp.filters.StopwordsFilter;

public class StopwordsFilterTest {
	StopwordsFilter filter = new StopwordsFilter();
	
	@Test
	public void test1(){
		String text = "Carl Nielsen was a Danish musician conductor and violinist widely recognized as his country's greatest composer";
		List<String> filtered = filter.extractKeywords(text.split(" "));
		String[] filteredArray = filtered.toArray(new String[]{});
		String expected = "Carl Nielsen Danish musician conductor violinist widely recognized country's greatest composer";
		String[] expArray = expected.split(" ");
		Assert.assertArrayEquals(expArray, filteredArray);
	}
	
	@Test
	public void test2(){
		String text = "Brought up by poor musically talented parents he attended the Royal Conservatory in Copenhagen from 1884 through 1886 and premiered his Op 1 Suite for Strings at the age of 23";
		List<String> filtered = filter.extractKeywords(text.split(" "));
		String[] filteredArray = filtered.toArray(new String[]{});
		String expected = "Brought poor musically talented parents attended Royal Conservatory Copenhagen 1884 1886 premiered Op 1 Suite Strings age 23";
		String[] expArray = expected.split(" ");
		Assert.assertArrayEquals(expArray, filteredArray);
	}
	
	@Test
	public void test3(){
		String text = "The following year he began a 16-year stint as a second violinist in the Royal Danish Orchestra under the conductor Johan Svendsen and later taught at the Royal Danish Academy of Music from 1916 until his death";
		List<String> filtered = filter.extractKeywords(text.split(" "));
		String[] filteredArray = filtered.toArray(new String[]{});
		String expected = "year began 16-year stint violinist Royal Danish Orchestra conductor Johan Svendsen taught Royal Danish Academy Music 1916 death";
		String[] expArray = expected.split(" ");
		Assert.assertArrayEquals(expArray, filteredArray);
	}
	
	@Test
	public void test4(){
		String text = "While his symphonies concertos and choral music are now internationally acclaimed Nielsen's career and personal life were marked by many difficulties often reflected in his music";
		List<String> filtered = filter.extractKeywords(text.split(" "));
		String[] filteredArray = filtered.toArray(new String[]{});
		String expected = "symphonies concertos choral music internationally acclaimed Nielsen's career personal life marked difficulties reflected music";
		String[] expArray = expected.split(" ");
		Assert.assertArrayEquals(expArray, filteredArray);
	}
}