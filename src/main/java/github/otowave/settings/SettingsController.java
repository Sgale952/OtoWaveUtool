package github.otowave.settings;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static github.otowave.otowaveutool.Main.updateSceneTheme;
import static github.otowave.settings.SettingsManager.*;
import static github.otowave.settings.SettingsManager.getSetting;

public class SettingsController implements Initializable {
    @FXML
    private ToggleGroup themes;
    @FXML
    private Text ttStatus;
    @FXML
    private TextField tfDefaultDir;
    @FXML
    private CheckBox chbDefaultDir, chbEnableAnim;
    @FXML
    private TextField tfDbUrl, tfDbUser, tfDbPassword;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chbDefaultDir.setSelected(Boolean.parseBoolean(getSetting("useDefaultDir")));
        tfDefaultDir.setText(getSetting("defaultDir"));

        tfDbUrl.setText(getSetting("databaseUrl"));
        tfDbUser.setText(getSetting("databaseUser"));
        tfDbPassword.setText(getSetting("databasePassword"));

        setToggledTheme();
        chbEnableAnim.setSelected(Boolean.parseBoolean(getSetting("enableAnimation")));
    }

    public void saveSettings(ActionEvent actionEvent) {
        RadioMenuItem selectedTheme = (RadioMenuItem) themes.getSelectedToggle();

        setSetting("useDefaultDir", String.valueOf(chbDefaultDir.isSelected()));
        setSetting("defaultDir", tfDefaultDir.getText());
        setSetting("databaseUrl", tfDbUrl.getText());
        setSetting("databaseUser", tfDbUser.getText());
        setSetting("databasePassword", tfDbPassword.getText());
        setSetting("theme", selectedTheme.getText());
        setSetting("enableAnimation", String.valueOf(chbEnableAnim.isSelected()));
        saveSettingsFile();

        updateSceneTheme();
    }

    private void setToggledTheme() {
        for(Toggle toggle : themes.getToggles()) {
            RadioMenuItem item = (RadioMenuItem) toggle;

            if (item.getText().equals(getSetting("theme"))) {
                toggle.setSelected(true);
                return;
            }
        }
    }
}
