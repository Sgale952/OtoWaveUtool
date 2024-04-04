package github.otowave.data;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import okhttp3.*;

import java.lang.reflect.Type;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

public class DataHandler {
    static final String BASE_URL = "http://0.0.0.0:4567/";
    static final Gson gson = new Gson();
    static final OkHttpClient client = new OkHttpClient.Builder()
            .readTimeout(0, TimeUnit.SECONDS)
            .writeTimeout(0, TimeUnit.SECONDS)
            .build();

    public static HashMap<String, ArrayList<Integer>> searchIds(String phrase) throws Exception {
        HashMap<String, ArrayList<Integer>> data;
        Request request = new Request.Builder()
                .url(BASE_URL + "search?phrase=" + phrase)
                .build();

        try(Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("error search id");
            }

            String json = response.body().string();
            Type dataType = new TypeToken<HashMap<String, ArrayList<Integer>>>() {}.getType();
            data = gson.fromJson(json, dataType);
        }

        return data;
    }

    public static HashMap<Integer, String> getGenres() throws Exception {
        HashMap<Integer, String> data;
        Request request = new Request.Builder()
                .url(BASE_URL + "navigator/genres")
                .build();

        try(Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("error get genres");
            }
            String json = response.body().string();
            data = gson.fromJson(json, HashMap.class);
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