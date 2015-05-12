package pl.edu.agh.iosr.nlp;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.doccat.DocumentCategorizer;
import opennlp.tools.doccat.DocumentCategorizerME;
import opennlp.tools.util.InvalidFormatException;

public class Categorizer {

	public static void main(String[] args) throws InvalidFormatException, IOException{
		InputStream is = new FileInputStream("resources/en-doccat.bin");
		DoccatModel model = new DoccatModel(is);
		String input = "car engine";
		DocumentCategorizerME categorizer = new DocumentCategorizerME(model);
		double[] outcomes = categorizer.categorize(input);
		String category = categorizer.getBestCategory(outcomes);
		System.out.println(category);
	}
	
}
