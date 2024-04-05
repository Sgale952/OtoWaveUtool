package github.otowave.settings;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class SettingsManager {

    private static final String SETTINGS_FILE = "uTool.properties";
    private static final Properties properties = new Properties();

    public static void loadSettingsFile() {
        try (FileInputStream in = new FileInputStream(SETTINGS_FILE)) {
            properties.load(in);
        }
        catch (IOException e) {
            if (!Files.exists(Paths.get(SETTINGS_FILE))) {
                setDefaultSettings();
            }
            else {
                e.printStackTrace();
            }
        }
    }

    public static void saveSettingsFile() {
        try (FileOutputStream out = new FileOutputStream(SETTINGS_FILE)) {
            properties.store(out, "OtoWave upload tool's config file\nChange only in the application!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setDefaultSettings() {
        setSetting("useDefaultDir", "false");
        setSetting("defaultDir", "");
        setSetting("email", "");
        setSetting("password", "");
        setSetting("theme", "default");
        setSetting("enableTooltips", "true");
        saveSettingsFile();
    }

    public static String getSetting(String key) {
        return properties.getProperty(key);
    }

    public static void setSetting(String key, String value) {
        if (value != null) {
            properties.setProperty(key, value);
        }
    }
}

