package github.otowave.album;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;

import static github.otowave.data.MusicDataHandler.uploadMusic;

public class AlbumManager {
    static String uploadMusicByMetadata(String authorId, String path) {
        File dir = new File(path);
        File[] files = dir.listFiles();

        try {
            for (File file : files) {
                AudioFile audio = AudioFileIO.read(file.getAbsoluteFile());
                Tag tag = audio.getTagOrCreateDefault();
                uploadMusic(authorId, tag.getFirst(FieldKey.TITLE), tag.getFirst(FieldKey.CUSTOM1), tag.getFirst(FieldKey.GENRE), file.getAbsoluteFile().toString());
            }
        }
        catch (CannotReadException | TagException | InvalidAudioFrameException | ReadOnlyFileException | IOException e) {
            e.printStackTrace();
        }

        return "";
    }
}
