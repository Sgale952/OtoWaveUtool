package github.otowave.otowaveutool;

import static github.otowave.settings.SettingsManager.getSetting;

public class CommonUtils {
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
