package github.otowave.settings;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class SettingsManager {

    private static final String SETTINGS_FILE = "Utool.properties";
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
            properties.store(out, "!Change only in the application!");
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void setDefaultSettings() {
        setSetting("useDefaultDir", "false");
        setSetting("defaultDir", "");
        setSetting("databaseUrl", "");
        setSetting("databaseUser", "");
        setSetting("databasePassword", "");
        setSetting("theme", "default");
        setSetting("enableAnimation", "true");
        saveSettingsFile();
    }

    public static String getSetting(String key) {
        return properties.getProperty(key);
    }

    public static void setSetting(String key, String value) {
        properties.setProperty(key, value);
    }
}

