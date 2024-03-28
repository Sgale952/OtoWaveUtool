package github.otowave.data;

import com.google.gson.Gson;
import okhttp3.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;

public class DataHandler {
    static final OkHttpClient client = new OkHttpClient();
    static final Gson gson = new Gson();
    static final String BASE_URL = "http://0.0.0.0:4567/";

    public static void uploadAuthor() {
    }

    public static int uploadMusic() {
        Request request = new Request.Builder()
                .url(BASE_URL + "navigator/genres")
                .build();

        return 0;
    }

    public static HashMap<String, Integer> searchIds(String phrase) throws IOException {
        HashMap<String, Integer> data = new HashMap<>();
        Request request = new Request.Builder()
                .url(BASE_URL + "search?phrase=" + phrase)
                .build();

        try(Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            data = gson.fromJson(json, HashMap.class);
        }

        return data;
    }

    public static HashMap<Integer, String> getGenres() {
        HashMap<Integer, String> data = new HashMap<>();
        Request request = new Request.Builder()
                .url(BASE_URL + "navigator/genres")
                .build();

        try(Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            data = gson.fromJson(json, HashMap.class);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    static String getFileName(String filePath) {
        Path path = Paths.get(filePath);
        String file = path.getFileName().toString();
        int lastIndex = file.lastIndexOf('.');

        return file.substring(0, lastIndex);
    }

    static String getFileExtension(String fileName) {
        String fileExtension = "";
        int lastIndex = fileName.lastIndexOf('.');
        if (lastIndex > 0) {
            fileExtension = fileName.substring(lastIndex+1);
        }

        return fileExtension;
    }
}