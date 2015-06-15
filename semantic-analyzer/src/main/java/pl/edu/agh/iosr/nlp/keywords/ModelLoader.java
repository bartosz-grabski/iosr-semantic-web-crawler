package pl.edu.agh.iosr.nlp.keywords;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.util.Properties;

import opennlp.tools.doccat.DoccatModel;
import opennlp.tools.postag.POSModel;
import opennlp.tools.util.InvalidFormatException;

public class ModelLoader {
	Properties properties;
	
	public ModelLoader() {
		this("resources/config.properties");
	}
	
	public ModelLoader(String propertiesPath) {
		properties = new Properties();
		try {
			InputStream in = new FileInputStream(propertiesPath);
			properties.load(in);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public DoccatModel loadDoccatModel() throws InvalidFormatException, IOException{
		FileInputStream in = new FileInputStream(properties.getProperty("doccatmodel"));
		DoccatModel doccatModel = new DoccatModel(in);
		
		return doccatModel;
	}
	
	public POSModel loadPosModel() throws InvalidFormatException, IOException{
		FileInputStream in = new FileInputStream(properties.getProperty("posmodel"));
		POSModel model = new POSModel(in);
		return model;
	}
	
	public TFIDFMap loadTFIDFMap(){
		TFIDFMap map = null;
		FileInputStream in = null;
		ObjectInputStream ois = null;
		try {
			in = new FileInputStream(properties.getProperty("tfidfmap"));
			ois = new ObjectInputStream(in);
			map = (TFIDFMap)ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ois.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return map;
	}
	
	public KeywordMap loadKeywordMap(){
		KeywordMap map = null;
		FileInputStream in = null;
		ObjectInputStream ois = null;
		try {
			in = new FileInputStream(properties.getProperty("keywordmap"));
			ois = new ObjectInputStream(in);
			map = (KeywordMap)ois.readObject();
		} catch (ClassNotFoundException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				ois.close();
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return map;
	}
}
