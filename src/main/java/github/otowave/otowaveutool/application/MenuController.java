package github.otowave.otowaveutool.application;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

public class MenuController {
    public Button btnAuthor;
    public Button btnSettings;
    public Button btnMusic;
    public Button btnAlbum;
    public BorderPane menuRoot;

    public void showSceneAuthor(ActionEvent actionEvent) throws IOException {
        showScene("author.fxml");
    }

    public void showSceneSettings(ActionEvent actionEvent) throws Exception {
        showScene("settings.fxml");
    }

    public void showSceneMusic(ActionEvent actionEvent) throws IOException {
        showScene("music.fxml");
    }

    public void showSceneAlbum(ActionEvent actionEvent) throws IOException {
        showScene("album.fxml");
    }

    private void showScene(String fxmlFile) throws IOException {
        FXMLLoader settingsLoader = new FXMLLoader(Main.class.getResource(fxmlFile));
        Parent settingsRoot = settingsLoader.load();

        menuRoot.setBottom(settingsRoot);
    }
}
