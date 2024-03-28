package github.otowave.album;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static github.otowave.album.AlbumManager.uploadMusicInAlbum;
import static github.otowave.data.AlbumDataHandler.uploadAlbum;
import static github.otowave.data.ImageDataHandler.applyImage;
import static github.otowave.data.ImageDataHandler.uploadImage;
import static github.otowave.otowaveutool.CommonUtils.setThemeSticker;
import static github.otowave.settings.SettingsManager.getSetting;

public class AlbumController implements Initializable {
    @FXML
    private ImageView ivSticker;
    @FXML
    private Text ttStatus;
    @FXML
    private TextField tfTitle, tfAuthor, tfAlbumDir, tfCoverPath;
    private String lastAlbumId;
    private String lastImageId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setThemeSticker(getSetting("theme"), ivSticker);
    }

    public void upload(ActionEvent actionEvent) {
        String authorId = tfAuthor.getText();
        String title = tfTitle.getText();
        String albumDir = tfAlbumDir.getText();
        String coverPath = tfCoverPath.getText();

        try {
            lastImageId = uploadImage(authorId, coverPath);
            lastAlbumId = uploadAlbum(authorId, title);
            uploadMusicInAlbum(lastImageId, lastAlbumId, authorId, albumDir);
            applyImage(authorId, lastImageId, lastAlbumId, "playlistCover");
        }
        catch (Exception e) {

        }
    }

    public void deleteLastUploaded(ActionEvent actionEvent) {
    }

    public void clearValues(ActionEvent actionEvent) {
        tfTitle.setText("");
        tfAuthor.setText("");
        tfAlbumDir.setText("");
        tfCoverPath.setText("");
    }
}
