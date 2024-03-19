package github.otowave.album;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.List;

import static github.otowave.data.DataHandler.uploadMusic;

public class AlbumController {
    @FXML
    private Text ttStatus;
    @FXML
    private TextField tfTitle, tfAuthor, tfAlbumDir, tfCoverPath;
    List<Integer> musicIds = new ArrayList<>();
    private String lastAlbumId;

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
