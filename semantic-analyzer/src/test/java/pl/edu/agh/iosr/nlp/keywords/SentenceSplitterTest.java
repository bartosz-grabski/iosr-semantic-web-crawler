package pl.edu.agh.iosr.nlp.keywords;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import pl.edu.agh.iosr.nlp.opennlp.SentenceSplitter;

public class SentenceSplitterTest {
	@Test
	public void test(){
		String tested = "Carl Nielsen was a Danish musician conductor and violinist widely recognized as his country's greatest composer. Brought up by poor musically talented parents, he attended the Royal Conservatory in Copenhagen from 1884 through 1886, and premiered his Op 1, Suite for Strings at the age of 23. The following year, he began a 16-year stint as a second violinist in the Royal Danish Orchestra under the conductor Johan Svendsen, and later taught at the Royal Danish Academy of Music from 1916 until his death. While his symphonies, concertos and choral music are now internationally acclaimed, Nielsen's career and personal life were marked by many difficulties, often reflected in his music.";
		SentenceSplitter sentenceSplitter = new SentenceSplitter();
		List<String> splited = sentenceSplitter.splitSentences(tested);
		
		String[] sentences = new String[]{"Carl Nielsen was a Danish musician conductor and violinist widely recognized as his country's greatest composer.",
		"Brought up by poor musically talented parents, he attended the Royal Conservatory in Copenhagen from 1884 through 1886, and premiered his Op 1, Suite for Strings at the age of 23.",
		"The following year, he began a 16-year stint as a second violinist in the Royal Danish Orchestra under the conductor Johan Svendsen, and later taught at the Royal Danish Academy of Music from 1916 until his death.",
		"While his symphonies, concertos and choral music are now internationally acclaimed, Nielsen's career and personal life were marked by many difficulties, often reflected in his music."};
		double counter = 0;
		for(String sentence : sentences){
			if(splited.contains(sentence)){
				counter++;
			}
		}
		
		Assert.assertTrue(counter/sentences.length>0.75);
	}
	
}
