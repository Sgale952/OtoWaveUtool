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

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        setThemeSticker(getSetting("theme"), ivSticker);
    }

    public void upload(ActionEvent actionEvent) {
        String nickname = tfNickname.getText();
        String email = tfEmail.getText();
        String Password = tfPassword.getText();

        uploadUser(nickname, email, Password);

        String avatarFile = tfAvatarPath.getText();
        String headerFile = tfAvatarPath.getText();
        String uploaderId = "";

        String avatarId = uploadImage("userAvatar", avatarFile, uploaderId);
        String headerId = uploadImage("userHeader", headerFile, uploaderId);
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
