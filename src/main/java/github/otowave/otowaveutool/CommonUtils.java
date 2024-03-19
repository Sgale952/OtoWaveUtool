package github.otowave.otowaveutool;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import static github.otowave.settings.SettingsManager.getSetting;

public class CommonUtils {
    public static void setThemeSticker(String theme, ImageView imageView) {
        Image sticker = new Image("github/otowave/stickers/"+theme+"/wait.png");
        imageView.setImage(sticker);
    }

    public static String getUploadPath(String path) {
        if (Boolean.parseBoolean(getSetting("useDefaultDir"))) {
            return getSetting("defaultDir")+path;
        }

        return path;
    }

    public static int convertBooleanToInt(Boolean value) {
        return value? 1:0;
    }
}
