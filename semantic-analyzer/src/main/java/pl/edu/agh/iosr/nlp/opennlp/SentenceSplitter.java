package pl.edu.agh.iosr.nlp.opennlp;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import opennlp.tools.sentdetect.SentenceDetectorME;
import opennlp.tools.sentdetect.SentenceModel;

public class SentenceSplitter {
	private SentenceModel model;
	
	public SentenceSplitter() {
		init();
	}
	
	private void init(){
		InputStream modelIn=null;
		try {
		  modelIn = new FileInputStream("resources/en-sent.bin");
		  model = new SentenceModel(modelIn);
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
	
	public List<String> splitSentences(String text){
		SentenceDetectorME detector = new SentenceDetectorME(model);
		List<String> sentences = Arrays.asList(detector.sentDetect(text));
		return sentences;
	}
}
