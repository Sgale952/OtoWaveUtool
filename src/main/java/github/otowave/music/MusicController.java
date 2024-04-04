package github.otowave.music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;
import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.tag.FieldKey;
import org.jaudiotagger.tag.Tag;

import java.io.File;
import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

import static github.otowave.data.DataHandler.getGenres;
import static github.otowave.data.ImageDataHandler.uploadImage;
import static github.otowave.data.MusicDataHandler.deleteMusic;
import static github.otowave.data.MusicDataHandler.uploadMusic;
import static github.otowave.music.MusicManager.getToggledGenre;
import static github.otowave.otowaveutool.CommonUtils.*;
import static github.otowave.otowaveutool.StatusUpdater.*;
import static github.otowave.settings.SettingsManager.getSetting;

public class MusicController implements Initializable {
    @FXML
    private ImageView ivSticker;
    @FXML
    private Text ttStatus;
    @FXML
    private Tooltip ttipDeleteLast;
    @FXML
    private MenuButton btnGenreMenu;
    @FXML
    private TextField tfTitle, tfAuthor, tfMusicPath, tfCoverPath;
    @FXML
    private CheckBox chbEcontent;

    private final ToggleGroup genreToggleGroup = new ToggleGroup();
    private Boolean isUseDefaultDir;
    private String lastMusicId;
    private String lastUserId;
    private String lastImageId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isUseDefaultDir = Boolean.parseBoolean(getSetting("useDefaultDir"));
        setWaitStatus(ttStatus, ivSticker);

        try {
            HashMap<Integer, String> genresMap = getGenres();
            for (HashMap.Entry<Integer, String> entry : genresMap.entrySet()) {
                RadioMenuItem radioMenuItem = new RadioMenuItem(entry.getValue());
                radioMenuItem.setToggleGroup(genreToggleGroup);
                btnGenreMenu.getItems().add(radioMenuItem);
            }
        }
        catch (Exception e) {
            setErrorStatus(e.getMessage(), ttStatus, ivSticker);
        }
    }

    public void upload(ActionEvent actionEvent) {
        try {
            lastUserId = tfAuthor.getText();
            String title = tfTitle.getText();
            String eContent = chbEcontent.isSelected()? "1" : "0";
            String genre = getToggledGenre(genreToggleGroup);
            String audioFilePath = tfMusicPath.getText();
            String imageFilePath = tfCoverPath.getText();

            if(isUseDefaultDir) {
                audioFilePath = getSetting("defaultDir") + audioFilePath;
                imageFilePath = getSetting("defaultDir") + imageFilePath;
            }

            lastMusicId = uploadMusic(lastUserId, title, eContent, genre, audioFilePath);
            lastImageId = uploadImage(lastUserId, lastMusicId, "musicCover", imageFilePath);

            ttipDeleteLast.setText("MusicID = "+lastMusicId+"\nUserID = "+lastUserId+"\nImageID = "+lastImageId);
            setSuccessStatus(lastMusicId, ttStatus, ivSticker);
        }
        catch (Exception e) {
            setErrorStatus(e.getMessage(), ttStatus, ivSticker);
        }
    }

    public void deleteLastUploaded(ActionEvent actionEvent) {
        try {
            deleteMusic(lastMusicId, lastUserId);
            setSuccessStatus(lastMusicId, ttStatus, ivSticker);
        }
        catch (Exception e) {
            setErrorStatus(e.getMessage(), ttStatus, ivSticker);
        }
    }

    public void clearValues(ActionEvent actionEvent) {
        tfTitle.setText("");
        tfAuthor.setText("");
        tfMusicPath.setText("");
        tfCoverPath.setText("");
        chbEcontent.setSelected(false);
    }

    public void setMetadata(ActionEvent actionEvent) {
        try {
            File audioFile = new File(getUploadPath(tfMusicPath.getText()));

            AudioFile audio = AudioFileIO.read(audioFile);

            Tag tag = audio.getTagOrCreateDefault();
            tag.setField(FieldKey.TITLE, tfTitle.getText());
            tag.setField(FieldKey.ARTIST, tfAuthor.getText());
            tag.setField(FieldKey.GENRE, getToggledGenre(genreToggleGroup));
            tag.setField(FieldKey.CUSTOM1, String.valueOf(convertBooleanToInt(chbEcontent.isSelected())));
            audio.commit();
        }
        catch (Exception e) {
            setErrorStatus(e.getMessage(), ttStatus, ivSticker);
        }

        setSuccessStatus("", ttStatus, ivSticker);
    }
}
