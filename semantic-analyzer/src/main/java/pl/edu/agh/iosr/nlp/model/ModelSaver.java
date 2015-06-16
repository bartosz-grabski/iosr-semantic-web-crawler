package pl.edu.agh.iosr.nlp.model;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

public class ModelSaver {
	
	private void saveObject(Object obj, String path){
		ObjectOutputStream oos=null;
		FileOutputStream fos=null;
		try {
			fos = new FileOutputStream(path);
			oos = new ObjectOutputStream(fos);
			oos.writeObject(obj);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				oos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	public void saveKeywordMap(KeywordMap map, String path){
		saveObject(map, path);
	}
	
	public void saveTFIDFMap(TFIDFMap map, String path){
		saveObject(map, path);
	}
}
