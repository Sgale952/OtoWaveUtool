package github.otowave.data;

import com.google.gson.Gson;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class DataHandler {
    private static final OkHttpClient client = new OkHttpClient();
    private static final Gson gson = new Gson();
    private static final String baseUrl = "http://0.0.0.0:4567";


    public static HashMap<Integer, String> getGenres() {
        HashMap<Integer, String> data = new HashMap<>();
        Request request = new Request.Builder()
                .url(baseUrl + "/navigator/genres")
                .build();

        try(Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            data = gson.fromJson(json, HashMap.class);
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return data;
    }

    public static int uploadMusic() {
        Request request = new Request.Builder()
                .url(baseUrl + "/navigator/genres")
                .build();

        return 0;
    }

    public static HashMap<String, Integer> searchIds(String phrase) {
        HashMap<String, Integer> data = new HashMap<>();
        Request request = new Request.Builder()
                .url(baseUrl + "/search?phrase=" + phrase)
                .build();

        try(Response response = client.newCall(request).execute()) {
            String json = response.body().string();
            data = gson.fromJson(json, HashMap.class);
        }
        catch(IOException e) {
            e.printStackTrace();
        }

        return data;
    }
}