package github.otowave.search;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.util.ArrayList;
import java.util.HashMap;

import static github.otowave.data.DataHandler.searchIds;
import static github.otowave.search.SearchHandler.formatArrayList;

public class SearchController {
    @FXML
    private Text ttStatus;
    @FXML
    private TextField tfSearchPhrase, tfAuthorIds, tfMusicIds, tfAlbumIds;

    public void search(ActionEvent actionEvent) {
        try {
            HashMap<String, ArrayList<Integer>> resultIds = searchIds(tfSearchPhrase.getText());

            tfAuthorIds.setText(formatArrayList(resultIds.get("users")));
            tfMusicIds.setText(formatArrayList(resultIds.get("music")));
            tfAlbumIds.setText(formatArrayList(resultIds.get("playlists")));

            ttStatus.setText("Success");
        }
        catch (Exception e) {
            ttStatus.setText(e.getMessage());
        }
    }
}
