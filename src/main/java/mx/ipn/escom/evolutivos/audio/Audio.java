/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.ipn.escom.evolutivos.audio;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFileFormat;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.UnsupportedAudioFileException;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.mp3.MP3File;

/**
 *
 * @author angel
 */
public class Audio {

    public Audio() {
        try {
            InputStream audio = new FileInputStream("C:\\Users\\angel\\Desktop\\mp3.mp3");
            AudioFileFormat baseFileFormat;
            baseFileFormat = AudioSystem.getAudioFileFormat(audio);
            Map<String, Object> properties = baseFileFormat.properties();
            Long duration = (Long) properties.get("duration");
            for (Map.Entry<String, Object> entrySet : properties.entrySet()) {
                String key = entrySet.getKey();
                Object value = entrySet.getValue();
                System.out.println(key);
            }
        } catch (UnsupportedAudioFileException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, "Unsoported", ex);
        } catch (IOException ex) {
            Logger.getLogger(Audio.class.getName()).log(Level.SEVERE, "IO", ex);
        }

    }

    public static void main(String[] args) {
        Audio audio = new Audio();
    }
}
