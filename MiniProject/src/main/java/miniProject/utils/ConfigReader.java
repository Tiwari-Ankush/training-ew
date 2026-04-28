package miniProject.utils;


import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigReader {
    private static final Properties props = new Properties();

    static {
        try (InputStream is = ConfigReader.class.getClassLoader()
                .getResourceAsStream("config/config.properties")) {
            if (is != null) props.load(is);
        } catch (IOException e) {
            // ignore, will fallback to defaults or -D
        }
    }

    public static String get(String key, String defaultVal) {
        String sys = System.getProperty(key);
        if (sys != null && !sys.isBlank()) return sys;
        String val = props.getProperty(key);
        return (val == null || val.isBlank()) ? defaultVal : val.trim();
    }

    public static int getInt(String key, int defaultVal) {
        try {
            return Integer.parseInt(get(key, String.valueOf(defaultVal)));
        } catch (Exception e) {
            return defaultVal;
        }
    }

    public static boolean getBool(String key, boolean defaultVal) {
        return Boolean.parseBoolean(get(key, String.valueOf(defaultVal)));
    }
}