package pl.edu.agh.iosr.nlp.opennlp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizer;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.util.InvalidFormatException;

public class Categorizer {
	private DocumentCategorizer categorizer;
	
	public Categorizer(DoccatModel model) {
		categorizer = new DocumentCategorizerME(model);
	}
	
	public Categorizer(){
		FileInputStream fis = null;
		try {
			fis = new FileInputStream("resources/en-doccat.bin");
			DoccatModel model = new DoccatModel(fis);
			categorizer = new DocumentCategorizerME(model);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Result categorize(String text){
		double[] outcomes = categorizer.categorize(text);
		String category = categorizer.getBestCategory(outcomes);
		double probability = outcomes[categorizer.getIndex(category)];
		return new Result(category, probability);
	}
	
	
	public static class Result{
		public final String category;
		public final double probability;
		
		public Result(String category, double probability) {
			this.category = category;
			this.probability = probability;
		}
	}
	
}
