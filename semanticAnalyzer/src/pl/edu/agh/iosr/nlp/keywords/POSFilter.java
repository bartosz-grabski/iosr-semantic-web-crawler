package pl.edu.agh.iosr.nlp.keywords;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTagger;
import opennlp.tools.postag.POSTaggerME;
import opennlp.tools.sentdetect.SentenceModel;

public class POSFilter {

	private POSModel model;
	
	private final static Set<String> allowedTags = new HashSet<String>();
	
	static{
		allowedTags.add("NN");
		allowedTags.add("VB");
		
	}
	
	public POSFilter(){
		init();
	}
	
	private void init(){
		InputStream modelIn=null;
		try {
		  modelIn = new FileInputStream("resources/en-pos-perceptron.bin");
		  model = new POSModel(modelIn);
		}
		catch (IOException e) {
		  e.printStackTrace();
		}
		finally {
		  if (modelIn != null) {
		    try {
		      modelIn.close();
		    }
		    catch (IOException e) {
		    }
		  }
		}
	}
	
	public List<String> extractKeywords(String sentence){
		POSTaggerME tagger = new POSTaggerME(model);
		String[] words = sentence.split("\\s+");
		String[] tags = tagger.tag(words);
		List<String> keywords = new LinkedList<String>();
		for(int i = 0; i<words.length;i++){
			if(tags[i].length()>1 && allowedTags.contains(tags[i].subSequence(0, 2))){
				keywords.add(words[i]);
			}
		}
		return keywords;
	}
	
}
