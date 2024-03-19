package github.otowave.user;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

import static github.otowave.otowaveutool.CommonUtils.setThemeSticker;
import static github.otowave.settings.SettingsManager.getSetting;

public class UserController implements Initializable {
    @FXML
    private ImageView ivSticker;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setThemeSticker(getSetting("theme"), ivSticker);
    }
}
