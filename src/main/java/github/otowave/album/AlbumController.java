package github.otowave.album;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static github.otowave.album.AlbumManager.uploadMusicInAlbum;
import static github.otowave.data.AlbumDataHandler.deleteAlbum;
import static github.otowave.data.AlbumDataHandler.uploadAlbum;
import static github.otowave.data.ImageDataHandler.uploadImage;
import static github.otowave.otowaveutool.CommonUtils.disableTooltips;
import static github.otowave.otowaveutool.StatusUpdater.*;
import static github.otowave.settings.SettingsManager.getSetting;

public class AlbumController implements Initializable {
    @FXML
    private ImageView ivSticker;
    @FXML
    private Text ttStatus;
    @FXML
    private Tooltip ttipDeleteLast, ttipAuthor, ttipAlbumDir, ttipCover;
    @FXML
    private TextField tfTitle, tfAuthor, tfAlbumDir, tfCoverPath;
    private Boolean isUseDefaultDir;
    private boolean isUseTooltips;
    private String albumId;
    private String imageId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isUseDefaultDir = Boolean.parseBoolean(getSetting("useDefaultDir"));
        isUseTooltips = Boolean.parseBoolean(getSetting("enableTooltips"));
        if(!isUseTooltips) {
            ArrayList<Tooltip> tooltips = new ArrayList<>(){{
                add(ttipAuthor); add(ttipAlbumDir); add(ttipCover);
            }};
            disableTooltips(tooltips);
        }

        setWaitStatus(ttStatus, ivSticker);
    }

    public void upload(ActionEvent actionEvent) {
        String authorId = tfAuthor.getText();
        String title = tfTitle.getText();
        String albumDir = tfAlbumDir.getText();
        String coverPath = tfCoverPath.getText();

        if(isUseDefaultDir) {
            coverPath = getSetting("defaultDir") + coverPath;
        }

        try {
            albumId = uploadAlbum(authorId, title);
            imageId = uploadImage(authorId, albumId, "playlistCover", coverPath);
            uploadMusicInAlbum(imageId, albumId, authorId, albumDir);

            ttipDeleteLast.setText("AlbumID = "+albumId+"\nImageID = "+imageId);
            setSuccessStatus(albumId, ttStatus, ivSticker);
        }
        catch (Exception e) {
            setErrorStatus(e.getMessage(), ttStatus, ivSticker);
        }
    }

    public void deleteLastUploaded(ActionEvent actionEvent) {
        try {
            deleteAlbum(albumId);
            setSuccessStatus(albumId, ttStatus, ivSticker);
        }
        catch (Exception e) {
            setErrorStatus(e.getMessage(), ttStatus, ivSticker);
        }
    }

    public void clearValues(ActionEvent actionEvent) {
        tfTitle.setText("");
        tfAuthor.setText("");
        tfAlbumDir.setText("");
        tfCoverPath.setText("");
    }
}
