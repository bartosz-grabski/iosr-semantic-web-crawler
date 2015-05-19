package pl.edu.agh.iosr.nlp.keywords;

public class Feeder {
	private KeywordAdder adder;
	private SentenceSplitter splitter;
	
	public Feeder(KeywordAdder adder){
		this.adder = adder;
		splitter = new SentenceSplitter();
	}
	
	public void feed(String feedText){
		String[] input = feedText.split(" ", 2);
		String category = input[0];
		String text = input[1];
		for(String sentence : splitter.splitSentences(text)){
			adder.addKeywords(category, sentence);
		}
	}
	
}
