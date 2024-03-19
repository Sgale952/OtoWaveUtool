package github.otowave.otowaveutool;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MenuController {
    @FXML
    private BorderPane menuRoot;

    public void showSceneAuthor(ActionEvent actionEvent) throws IOException {
        showScene("author.fxml");
    }


    public void showSceneMusic(ActionEvent actionEvent) throws IOException {
        showScene("music.fxml");
    }

    public void showSceneAlbum(ActionEvent actionEvent) throws IOException {
        showScene("album.fxml");
    }

    public void showSceneSearch(ActionEvent actionEvent) throws Exception {
        showScene("search.fxml");
    }

    public void showSceneSettings(ActionEvent actionEvent) throws Exception {
        showScene("settings.fxml");
    }

    private void showScene(String fxmlFile) throws IOException {
        FXMLLoader settingsLoader = new FXMLLoader(Main.class.getResource(fxmlFile));
        Parent settingsRoot = settingsLoader.load();

        menuRoot.setBottom(settingsRoot);
    }
}
