package github.otowave.otowaveutool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

import static github.otowave.settings.SettingsManager.loadSettingsFile;

public class Main extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        loadSettingsFile();

        FXMLLoader menuLoader = new FXMLLoader(Main.class.getResource("menu.fxml"));
        BorderPane menuRoot = menuLoader.load();

        FXMLLoader welcomeLoader = new FXMLLoader(Main.class.getResource("welcome.fxml"));
        Parent welcomeRoot = welcomeLoader.load();

        menuRoot.setBottom(welcomeRoot);

        Scene scene = new Scene(menuRoot, 600, 365);
        stage.setResizable(false);
        stage.setTitle("OtoWaveUtool");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}