package github.otowave.settings;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static github.otowave.otowaveutool.CommonUtils.disableTooltips;
import static github.otowave.otowaveutool.Main.updateSceneTheme;
import static github.otowave.settings.SettingsManager.*;
import static github.otowave.settings.SettingsManager.getSetting;

public class SettingsController implements Initializable {
    @FXML
    private ToggleGroup themes;
    @FXML
    private Text ttStatus;
    @FXML
    private Tooltip ttipDefauitDir, ttipEmail, ttipPassword;
    @FXML
    private TextField tfDefaultDir;
    @FXML
    private CheckBox chbDefaultDir, chbEnableAnim;
    @FXML
    private TextField tfEmail, tfPassword;
    private boolean isUseTooltips;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        chbDefaultDir.setSelected(Boolean.parseBoolean(getSetting("useDefaultDir")));
        tfDefaultDir.setText(getSetting("defaultDir"));
        isUseTooltips = Boolean.parseBoolean(getSetting("enableTooltips"));
        if(!isUseTooltips) {
            ArrayList<Tooltip> tooltips = new ArrayList<>(){{
                add(ttipDefauitDir); add(ttipEmail); add(ttipPassword);
            }};
            disableTooltips(tooltips);
        }

        tfEmail.setText(getSetting("databaseUser"));
        tfPassword.setText(getSetting("databasePassword"));

        setToggledTheme();
        chbEnableAnim.setSelected(Boolean.parseBoolean(getSetting("enableTooltips")));
    }

    public void saveSettings(ActionEvent actionEvent) {
        RadioMenuItem selectedTheme = (RadioMenuItem) themes.getSelectedToggle();

        setSetting("useDefaultDir", String.valueOf(chbDefaultDir.isSelected()));
        setSetting("defaultDir", tfDefaultDir.getText());
        setSetting("email", tfEmail.getText());
        setSetting("password", tfPassword.getText());
        setSetting("theme", selectedTheme.getText());
        setSetting("enableTooltips", String.valueOf(chbEnableAnim.isSelected()));
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
