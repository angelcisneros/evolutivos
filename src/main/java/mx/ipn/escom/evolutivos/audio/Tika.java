/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.evolutivos.audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import org.apache.tika.exception.TikaException;
import org.apache.tika.metadata.Metadata;
import org.apache.tika.parser.ParseContext;
import org.apache.tika.parser.mp3.LyricsHandler;
import org.apache.tika.parser.mp3.Mp3Parser;
import org.apache.tika.sax.BodyContentHandler;
import org.xml.sax.SAXException;

/**
 *
 * @author angel
 */
public class Tika {
    public static final String FILE = "C:\\Users\\angel\\Desktop\\mp3.mp3";
    public static final String BANDA = "C:\\Users\\angel\\Desktop\\banda.mp3";
    public static void main(final String[] args) throws Exception, IOException, SAXException, TikaException {

      //detecting the file type
      BodyContentHandler handler = new BodyContentHandler();
      Metadata metadata = new Metadata();
      FileInputStream inputstream = new FileInputStream(new File(FILE));
      ParseContext pcontext = new ParseContext();
      
      //Mp3 parser
      Mp3Parser  mp3Parser = new  Mp3Parser();
      
      mp3Parser.parse(inputstream, handler, metadata, pcontext);
      LyricsHandler lyrics = new LyricsHandler(inputstream,handler);
      
      while(lyrics.hasLyrics()) {
    	  System.out.println(lyrics.toString());
      }
      
      System.out.println("Contents of the document:" + handler.toString());
      System.out.println("Metadata of the document:");
      String[] metadataNames = metadata.names();
      
      

      for(String name : metadataNames) {		        
    	  System.out.println(name + ": " + metadata.get(name));
      }
   }
}
