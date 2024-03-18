package github.otowave.otowaveutool.settings;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static github.otowave.otowaveutool.settings.SettingsManager.*;

public class SettingsController implements Initializable {
    @FXML
    private Button btnSave;
    @FXML
    private Text txStatus;
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

        //theme
        chbEnableAnim.setSelected(Boolean.parseBoolean(getSetting("enableAnimation")));
    }

    public void saveSettings(ActionEvent actionEvent) {
        setSetting("useDefaultDir", String.valueOf(chbDefaultDir.isSelected()));
        setSetting("defaultDir", tfDefaultDir.getText());
        setSetting("databaseUrl", tfDbUrl.getText());
        setSetting("databaseUser", tfDbUser.getText());
        setSetting("databasePassword", tfDbPassword.getText());
        setSetting("theme", "default");
        setSetting("enableAnimation", String.valueOf(chbEnableAnim.isSelected()));

        saveSettingsFile();
    }
}
