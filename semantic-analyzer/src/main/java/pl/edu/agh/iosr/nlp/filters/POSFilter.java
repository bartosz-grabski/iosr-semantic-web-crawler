package pl.edu.agh.iosr.nlp.filters;

import java.io.IOException;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
import pl.edu.agh.iosr.nlp.keywords.ModelLoader;

public class POSFilter implements IFilter{

	private POSModel model;
	
	private final static Set<String> allowedTags = new HashSet<String>();
	
	static{
		allowedTags.add("NN");
		allowedTags.add("VB");
		allowedTags.add("JJ");
		allowedTags.add("RB");
		
	}
	
	public POSFilter(){
		init();
	}
	
	private void init(){
		ModelLoader loader = new ModelLoader();
		try {
			model = loader.loadPosModel();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	public List<String> extractKeywords(String[] sentence){
		POSTaggerME tagger = new POSTaggerME(model);
		String[] tags = tagger.tag(sentence);
		List<String> keywords = new LinkedList<String>();
		for(int i = 0; i<sentence.length;i++){
			if(tags[i].length()>1 && allowedTags.contains(tags[i].subSequence(0, 2))){
				keywords.add(sentence[i]);
			}
		}
		return keywords;
	}
	
}
