package pl.edu.agh.iosr.nlp.opennlp;

import java.util.LinkedList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

public class CategorizerTest {

	private static List<String[]> testCases;
	private Categorizer categorizer;
	
	@BeforeClass
	public static void initSuite(){
		testCases = new LinkedList<String[]>();
		testCases.add(new String[]{"alt.atheism","Ex nihilo concept pros and cons"});
		testCases.add(new String[]{"comp.graphics","corel draw features"});
		testCases.add(new String[]{"comp.os.ms-windows.misc","microsoft windows help"});
		testCases.add(new String[]{"comp.sys.ibm.pc.hardware","motherboard ram limit"});
		testCases.add(new String[]{"comp.windows.x","ms windows menu start layout"});
		testCases.add(new String[]{"misc.forsale","trading and selling goods"});
		testCases.add(new String[]{"rec.autos","car engine specialists"});
		testCases.add(new String[]{"rec.motorcycles","chopper or sport bike"});
		testCases.add(new String[]{"rec.sport.baseball","best pitcher in mlb"});
		testCases.add(new String[]{"rec.sport.hockey","puck standar weight"});
		testCases.add(new String[]{"sci.crypt","md5 and blowfish differences"});
		testCases.add(new String[]{"sci.electronics","dvd recorded problems"});
		testCases.add(new String[]{"sci.med","cure for cancer"});
		testCases.add(new String[]{"sci.space","what type of galaxy is milky way"});
		testCases.add(new String[]{"soc.religion.christian","mass in church"});
		testCases.add(new String[]{"talk.politics.guns","is glock viable pistol"});
		testCases.add(new String[]{"talk.politics.mideast","iran  and iraq war"});
		testCases.add(new String[]{"talk.politics.misc","republicans or democrats"});
		testCases.add(new String[]{"talk.religion.misc","orthodox jews habits"});
	}
	
	@Test
	public void test(){
		categorizer = new Categorizer();
		double counter=0, correct=0;
		for(String[] testCase : testCases){
			String category = categorizer.categorize(testCase[1]).category;
			if(category.equals(testCase[0])){
				correct++;
			}
			counter++;
		}
		Assert.assertTrue(correct/counter>0.75);
	}
	
}
