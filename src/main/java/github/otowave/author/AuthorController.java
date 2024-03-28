package github.otowave.author;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

import static github.otowave.data.AuthorDataHandler.uploadUser;
import static github.otowave.data.ImageDataHandler.applyImage;
import static github.otowave.data.ImageDataHandler.uploadImage;
import static github.otowave.otowaveutool.CommonUtils.setThemeSticker;
import static github.otowave.settings.SettingsManager.getSetting;

public class AuthorController implements Initializable {
    @FXML
    private Text ttStatus;
    @FXML
    private ImageView ivSticker;
    @FXML
    private TextField tfNickname, tfEmail, tfAvatarPath, tfHeaderPath, tfPassword;
    private Boolean isUseDefaultDir;
    private String avatarId;
    private String headerId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isUseDefaultDir = Boolean.parseBoolean(getSetting("useDefaultDir"));
        setThemeSticker(getSetting("theme"), ivSticker);
    }

    public void upload(ActionEvent actionEvent) {
        String nickname = tfNickname.getText();
        String email = tfEmail.getText();
        String password = tfPassword.getText();
        String avatarFile = tfAvatarPath.getText();
        String headerFile = tfAvatarPath.getText();
        if(isUseDefaultDir) {
            avatarFile = getSetting("defaultDir") + avatarFile;
            headerFile = getSetting("defaultDir") + headerFile;
        }

        try {
            String uploaderId = uploadUser(nickname, email, password);

            avatarId = uploadImage(uploaderId, avatarFile);
            applyImage(uploaderId, avatarId, uploaderId, "userAvatar");

            headerId = uploadImage(uploaderId, headerFile);
            applyImage(uploaderId, headerId, uploaderId, "userHeader");
        }
        catch (Exception e) {

        }
    }

    public void clearValues(ActionEvent actionEvent) {
        tfNickname.setText("");
        tfEmail.setText("");
        tfAvatarPath.setText("");
        tfHeaderPath.setText("");
        tfPassword.setText("");
    }

    public void deleteLastUploaded(ActionEvent actionEvent) {
    }
}
