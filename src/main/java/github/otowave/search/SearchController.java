package github.otowave.search;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.HashMap;

import static github.otowave.data.DataHandler.searchIds;

public class SearchController {
    @FXML
    private Text ttStatus;
    @FXML
    private TextField tfSearchPhrase, tfAuthorIds, tfMusicIds, tfAlbumIds;

    public void search(ActionEvent actionEvent) {
        StringBuilder userIds = new StringBuilder();
        StringBuilder musicIds = new StringBuilder();
        StringBuilder albumIds = new StringBuilder();
        String delimiter = ", ";
        try {
            HashMap<String, Integer> resultIds = searchIds(tfSearchPhrase.getText());

            for(HashMap.Entry<String, Integer> entry: resultIds.entrySet()) {
                String table = entry.getKey();

                switch (table) {
                    case "users":
                        userIds.append(entry.getValue()).append(delimiter);
                    case "music":
                        musicIds.append(entry.getValue()).append(delimiter);
                    case "albums":
                        albumIds.append(entry.getValue()).append(delimiter);
                }
            }
            tfAuthorIds.setText(userIds.toString());
            tfMusicIds.setText(musicIds.toString());
            tfAlbumIds.setText(albumIds.toString());
        }
        catch (Exception e) {

        }
    }
}
