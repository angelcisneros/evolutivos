/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.evolutivos.audio;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import static mx.ipn.escom.evolutivos.audio.Tika.FILE;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.audio.mp3.MP3File;
import org.jaudiotagger.tag.TagException;
import org.jaudiotagger.tag.datatype.Artwork;
import org.jaudiotagger.test.MergeID3AndMP3Files;
import org.openimaj.audio.AudioStream;

/**
 *
 * @author angel
 */
public class JAudio {

    public JAudio() {
        try {
            
             AudioFile file = AudioFileIO.read(new File(FILE));
             System.out.println(file.displayStructureAsPlainText());
             System.out.println("*************+");
             System.out.println(file.displayStructureAsXML());
             for(Artwork a: file.getTag().getArtworkList()){
                 System.out.println();
             }
             
            MP3File a = new MP3File(FILE);
            
            Map<String, Object> m = a.getID3v2Tag().encryptedFrameMap;
            for (Map.Entry<String, Object> entrySet : m.entrySet()) {
                String key = entrySet.getKey();
                Object value = entrySet.getValue();
                System.out.println(key);
            }
        } catch (IOException ex) {
            Logger.getLogger(JAudio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TagException ex) {
            Logger.getLogger(JAudio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ReadOnlyFileException ex) {
            Logger.getLogger(JAudio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InvalidAudioFrameException ex) {
            Logger.getLogger(JAudio.class.getName()).log(Level.SEVERE, null, ex);
        } catch (CannotReadException ex) {
            Logger.getLogger(JAudio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static void main(String[] args) {
        new JAudio();
    }
}
