package github.otowave.author;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

import static github.otowave.data.AuthorDataHandler.deleteUser;
import static github.otowave.data.AuthorDataHandler.uploadUser;
import static github.otowave.data.ImageDataHandler.uploadImage;
import static github.otowave.otowaveutool.CommonUtils.disableTooltips;
import static github.otowave.otowaveutool.StatusUpdater.*;
import static github.otowave.settings.SettingsManager.getSetting;

public class AuthorController implements Initializable {
    @FXML
    private Text ttStatus;
    @FXML
    private ImageView ivSticker;
    @FXML
    private Tooltip ttipDeleteLast, ttipEmail, ttipAvatarFile, ttipHeaderFile;
    @FXML
    private TextField tfNickname, tfEmail, tfAvatarPath, tfHeaderPath, tfPassword;
    private boolean isUseDefaultDir;
    private boolean isUseTooltips;
    private String authorId;
    private String avatarId;
    private String headerId;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        isUseDefaultDir = Boolean.parseBoolean(getSetting("useDefaultDir"));
        isUseTooltips = Boolean.parseBoolean(getSetting("enableTooltips"));
        if(!isUseTooltips) {
            ArrayList<Tooltip> tooltips = new ArrayList<>(){{
                add(ttipEmail); add(ttipAvatarFile); add(ttipHeaderFile);
            }};
            disableTooltips(tooltips);
        }

        setWaitStatus(ttStatus, ivSticker);
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
            authorId = uploadUser(nickname, email, password);
            avatarId = uploadImage(authorId, authorId, "userAvatar", avatarFile);
            headerId = uploadImage(authorId, authorId, "userHeader", headerFile);

            ttipDeleteLast.setText("AuthorID = "+ authorId + "\nAvatarID = "+ avatarId + "\nHeaderID = "+ headerId);
            setSuccessStatus(authorId, ttStatus, ivSticker);
        }
        catch (Exception e) {
            setErrorStatus(e.getMessage(), ttStatus, ivSticker);
        }
    }

    public void deleteLastUploaded(ActionEvent actionEvent) {
        try {
            deleteUser(authorId);
            setSuccessStatus(authorId, ttStatus, ivSticker);
        }
        catch (Exception e) {
            setErrorStatus(e.getMessage(), ttStatus, ivSticker);
        }
    }

    public void clearValues(ActionEvent actionEvent) {
        tfNickname.setText("");
        tfEmail.setText("");
        tfAvatarPath.setText("");
        tfHeaderPath.setText("");
        tfPassword.setText("");
    }
}
