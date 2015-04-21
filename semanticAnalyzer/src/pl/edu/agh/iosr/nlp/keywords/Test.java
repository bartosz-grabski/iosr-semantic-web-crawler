package pl.edu.agh.iosr.nlp.keywords;

public class Test {

	public static void main(String[] args) {
		SentenceSplitter splitter = new SentenceSplitter();
		POSFilter posFilter = new POSFilter();
		String input = "Lycoming Creek and the Sheshequin Path played an important role in the early history of Lewis Township. The path was a major Native American trail in that ran between two Native American villages: French Margaret's Town on the West Branch Susquehanna River (part of modern day Williamsport in Lycoming County) and Sheshequin on the North Branch of the Susquehanna River (modern day Ulster Township, in Bradford County). The path ran north and east along Lycoming Creek in Lycoming County and followed much of Towanda Creek in Bradford County. It was a shortcut between the two main branches of the Susquehanna River and was used by early settlers as well as Native Americans.";
		for (String sentence : splitter.splitSentences(input)){
			System.out.println(posFilter.extractKeywords(sentence));
		}
	}

}
