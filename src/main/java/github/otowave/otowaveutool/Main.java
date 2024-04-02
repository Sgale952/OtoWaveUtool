package github.otowave.otowaveutool;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

import java.io.IOException;

import static github.otowave.settings.SettingsManager.getSetting;
import static github.otowave.settings.SettingsManager.loadSettingsFile;

public class Main extends Application {
    private static Scene scene;
    private static final String themeDir = "github/otowave/themes/";

    @Override
    public void start(Stage stage) throws IOException {
        loadSettingsFile();

        FXMLLoader menuLoader = new FXMLLoader(Main.class.getResource("menu.fxml"));
        BorderPane menuRoot = menuLoader.load();

        FXMLLoader welcomeLoader = new FXMLLoader(Main.class.getResource("welcome.fxml"));
        Parent welcomeRoot = welcomeLoader.load();

        menuRoot.setBottom(welcomeRoot);

        scene = new Scene(menuRoot, 600, 365);
        updateSceneTheme();

        stage.setResizable(false);
        stage.setTitle("OtoWave uTool");
        stage.getIcons().add(new Image("github/otowave/otowaveutool/logo.png"));
        stage.setScene(scene);
        stage.show();
    }

    public static void updateSceneTheme() {
        String theme = getSetting("theme");
        scene.getStylesheets().clear();
        scene.getStylesheets().add(themeDir+theme+".css");
    }

    public static void main(String[] args) {
        launch();
    }
}