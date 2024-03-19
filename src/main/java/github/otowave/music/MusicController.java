package github.otowave.music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.CannotWriteException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;
import org.jaudiotagger.tag.TagException;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import static github.otowave.data.DataHandler.getGenres;
import static github.otowave.otowaveutool.CommonUtils.*;
import static github.otowave.settings.SettingsManager.getSetting;

public class MusicController implements Initializable {
    @FXML
    private ImageView ivSticker;
    @FXML
    private MenuButton btnGenreMenu;
    @FXML
    private Text ttStatus;
    @FXML
    private TextField tfTitle, tfAuthor, tfMusicPath, tfCoverPath;
    @FXML
    private CheckBox chbEcontent;
    private String lastMusicId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ToggleGroup genreToggleGroup = new ToggleGroup();
        HashMap<Integer, String> genresMap = getGenres();

        for (HashMap.Entry<Integer, String> entry : genresMap.entrySet()) {
            RadioMenuItem radioMenuItem = new RadioMenuItem(entry.getValue());
            radioMenuItem.setToggleGroup(genreToggleGroup);
            btnGenreMenu.getItems().add(radioMenuItem);
        }

        setThemeSticker(getSetting("theme"), ivSticker);
    }

    public void upload(ActionEvent actionEvent) {
        //MusicData musicData = new MusicData();
    }

    public void deleteLastUploaded(ActionEvent actionEvent) {

    }

    public void clearValues(ActionEvent actionEvent) {
        tfTitle.setText("");
        tfAuthor.setText("");
        tfMusicPath.setText("");
        tfCoverPath.setText("");
        chbEcontent.setSelected(false);
    }

    public void setMetadata(ActionEvent actionEvent) {
        File audioFile = new File(getUploadPath(tfMusicPath.getText()));

        try {
            AudioFile audio = AudioFileIO.read(audioFile);

            Tag tag = audio.getTagOrCreateDefault();
            tag.setField(FieldKey.TITLE, tfTitle.getText());
            tag.setField(FieldKey.ARTIST, tfAuthor.getText());
            tag.setField(FieldKey.GENRE, "genre");
            tag.setField(FieldKey.CUSTOM1, String.valueOf(convertBooleanToInt(chbEcontent.isSelected())));
            audio.commit();
        }
        catch (CannotReadException | TagException | InvalidAudioFrameException | ReadOnlyFileException | IOException | CannotWriteException e) {
            e.printStackTrace();
        }
    }
}
