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
import static github.otowave.data.ImageDataHandler.uploadImage;
import static github.otowave.otowaveutool.StatusUpdater.*;

public class AlbumController implements Initializable {
    @FXML
    private ImageView ivSticker;
    @FXML
    private Text ttStatus;
    @FXML
    private TextField tfTitle, tfAuthor, tfAlbumDir, tfCoverPath;
    private String albumId;
    private String imageId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setWaitStatus(ttStatus, ivSticker);
    }

    public void upload(ActionEvent actionEvent) {
        String authorId = tfAuthor.getText();
        String title = tfTitle.getText();
        String albumDir = tfAlbumDir.getText();
        String coverPath = tfCoverPath.getText();

        try {
            albumId = uploadAlbum(authorId, title);
            imageId = uploadImage(authorId, albumId, "playlistCover", coverPath);
            uploadMusicInAlbum(imageId, albumId, authorId, albumDir);

            setSuccessStatus(albumId, ttStatus, ivSticker);
        }
        catch (Exception e) {
            setErrorStatus(e.getMessage(), ttStatus, ivSticker);
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
