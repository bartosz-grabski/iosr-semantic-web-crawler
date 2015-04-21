package pl.edu.agh.iosr.nlp;

import java.io.FileInputStream;
import java.io.InputStream;
import java.io.IOException;
 

import opennlp.tools.postag.POSModel;
import opennlp.tools.postag.POSTaggerME;
 
public class TokenTagger {
  private POSModel model;
 
  public TokenTagger( InputStream data ) throws IOException {
    setModel( new POSModel( data ) );
  }
 
  public void run( String sentence ) {
    POSTaggerME tagger = new POSTaggerME( getModel() );

    String[] words = sentence.split( "\\s+" );
    String[] tags = tagger.tag( words );
    double[] probs = tagger.probs();
 
    for( int i = 0; i < tags.length; i++ ) {
      System.out.println( words[i] + " => " + tags[i] + " @ " + probs[i] );
    }
  }
 
  private void setModel( POSModel model ) {
    this.model = model;
  }
 
  private POSModel getModel() {
    return this.model;
  }
 
  public static void main( String args[] ) throws IOException {
    if( args.length < 2 ) {
      System.out.println( "HelloWord <file> \"sentence to tag\"" );
      return;
    }
 
    InputStream is = new FileInputStream( args[0] );
    TokenTagger hw = new TokenTagger( is );
    is.close();
 
    hw.run( args[1] );
  }
}