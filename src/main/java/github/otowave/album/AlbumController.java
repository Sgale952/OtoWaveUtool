package github.otowave.album;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static github.otowave.data.DataHandler.uploadMusic;
import static github.otowave.otowaveutool.CommonUtils.setThemeSticker;
import static github.otowave.settings.SettingsManager.getSetting;

public class AlbumController implements Initializable {
    @FXML
    private ImageView ivSticker;
    @FXML
    private Text ttStatus;
    @FXML
    private TextField tfTitle, tfAuthor, tfAlbumDir, tfCoverPath;
    List<Integer> musicIds = new ArrayList<>();
    private String lastAlbumId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setThemeSticker(getSetting("theme"), ivSticker);
    }

    public void saveAlbum(ActionEvent actionEvent) {

    }

    public void deleteLastUploaded(ActionEvent actionEvent) {
    }

    public void clearValues(ActionEvent actionEvent) {
        tfTitle.setText("");
        tfAuthor.setText("");
        tfAlbumDir.setText("");
        tfCoverPath.setText("");
    }

    private void saveAllMusic() {
        uploadMusic();
    }
}
