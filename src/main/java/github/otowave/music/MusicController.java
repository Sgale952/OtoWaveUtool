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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.ResourceBundle;

import static github.otowave.data.AlbumDataHandler.fillPlaylist;
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
    private Tooltip ttipDeleteLast, ttipAuthor, ttipMusicFile, ttipCoverFile, ttipSetMetadata, ttipAlbum;
    @FXML
    private MenuButton btnGenreMenu;
    @FXML
    private TextField tfTitle, tfAuthor, tfAlbum, tfMusicPath, tfCoverPath;
    @FXML
    private CheckBox chbEcontent;
    private final ToggleGroup genreToggleGroup = new ToggleGroup();
    private Boolean isUseDefaultDir;
    private Boolean isUseTooltips;
    private String musicId;
    private String userId;
    private String albumId;
    private String imageId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isUseDefaultDir = Boolean.parseBoolean(getSetting("useDefaultDir"));
        isUseTooltips = Boolean.parseBoolean(getSetting("enableTooltips"));
        if(!isUseTooltips) {
            ArrayList<Tooltip> tooltips = new ArrayList<>(){{
                add(ttipAuthor); add(ttipMusicFile); add(ttipCoverFile); add(ttipSetMetadata); add(ttipAlbum);
            }};
            disableTooltips(tooltips);
        }

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
            userId = tfAuthor.getText();
            albumId = tfAlbum.getText();
            String title = tfTitle.getText();
            String eContent = chbEcontent.isSelected()? "1" : "0";
            String genre = getToggledGenre(genreToggleGroup);
            String audioFilePath = tfMusicPath.getText();
            String imageFilePath = tfCoverPath.getText();

            if(isUseDefaultDir) {
                audioFilePath = getSetting("defaultDir") + audioFilePath;
                imageFilePath = getSetting("defaultDir") + imageFilePath;
            }

            musicId = uploadMusic(userId, title, eContent, genre, audioFilePath);
            imageId = uploadImage(userId, musicId, "musicCover", imageFilePath);

            if(!Objects.equals(albumId, "")) {
                fillPlaylist(albumId, musicId);
            }

            ttipDeleteLast.setText("MusicID = "+ musicId + "AlbumID = "+ albumId + "\nUserID = "+ userId +"\nImageID = "+ imageId);
            setSuccessStatus(musicId, ttStatus, ivSticker);
        }
        catch (Exception e) {
            setErrorStatus(e.getMessage(), ttStatus, ivSticker);
        }
    }

    public void deleteLastUploaded(ActionEvent actionEvent) {
        try {
            deleteMusic(musicId, userId);
            setSuccessStatus(musicId, ttStatus, ivSticker);
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
