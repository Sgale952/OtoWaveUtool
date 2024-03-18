package github.otowave.otowaveutool.settings;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Properties;

public class SettingsManager {

    private static final String SETTINGS_FILE = "settings.properties";
    private static final Properties properties = new Properties();

    public static void loadSettingsFile() {
        try (FileInputStream in = new FileInputStream(SETTINGS_FILE)) {
            properties.load(in);
        }
        catch (IOException e) {
            if (!Files.exists(Paths.get(SETTINGS_FILE))) {
                saveSettingsFile();
                setDefaultSettings();
            }
            else {
                e.printStackTrace();
            }
        }
    }

    public static void saveSettingsFile() {
        try (FileOutputStream out = new FileOutputStream(SETTINGS_FILE)) {
            properties.store(out, "Application Settings");
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
    }

    public static String getSetting(String key) {
        return properties.getProperty(key);
    }

    public static void setSetting(String key, String value) {
        properties.setProperty(key, value);
    }
}

