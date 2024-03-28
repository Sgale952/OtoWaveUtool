package github.otowave.otowaveutool;

import javafx.animation.PauseTransition;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.util.Duration;

import static github.otowave.settings.SettingsManager.getSetting;

public class StatusUpdater {
    public static void setWaitStatus(Text status, ImageView imageView) {
        String currentTheme = getSetting("theme");
        Image sticker = new Image("github/otowave/stickers/"+currentTheme+"/wait.png");
        imageView.setImage(sticker);

        status.setFill(Color.BLACK);
    }

    public static void setSuccessStatus(String generatedId, Text status, ImageView imageView) {
        String currentTheme = getSetting("theme");
        Image sticker = new Image("github/otowave/stickers/"+currentTheme+"/ok.png");
        imageView.setImage(sticker);

        status.setFill(Color.GREEN);
        status.setText(generatedId);

        suspendResetStatus(3, status, imageView);
    }

    public static void setErrorStatus(String errorMsg, Text status, ImageView imageView) {
        String currentTheme = getSetting("theme");
        Image sticker = new Image("github/otowave/stickers/"+currentTheme+"/error.png");
        imageView.setImage(sticker);

        status.setFill(Color.RED);
        status.setText(errorMsg);

        suspendResetStatus(3, status, imageView);
    }

    private static void suspendResetStatus(int seconds, Text status, ImageView imageView) {
        PauseTransition delay = new PauseTransition(Duration.seconds(seconds));
        delay.setOnFinished(event -> setWaitStatus(status, imageView));
        delay.play();
    }
}
