package github.otowave.otowaveutool;

import javafx.scene.control.Tooltip;
import javafx.util.Duration;

import java.util.ArrayList;

import static github.otowave.settings.SettingsManager.getSetting;

public class CommonUtils {
    public static void disableTooltips(ArrayList<Tooltip> tooltips) {
        for(Tooltip ttip : tooltips) {
            ttip.setShowDuration(Duration.ZERO);
        }
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
