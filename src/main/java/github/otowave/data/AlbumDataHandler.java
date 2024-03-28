package github.otowave.data;

import com.google.gson.JsonObject;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static github.otowave.data.DataHandler.*;

public class AlbumDataHandler {
    public static String uploadAlbum(String authorId, String title) throws Exception {
        String albumId = "";
        AlbumData albumData = new AlbumData(1, 1, title);
        String json = gson.toJson(albumData);

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(BASE_URL +authorId+"/new-playlist")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                albumId = response.body().string();
                //TODO: update status
            }
        }

        return albumId;
    }

    public static void fillPlaylist(String playlistId, String musicId) throws Exception {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("musicId", musicId);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(jsonObject.toString(), mediaType);

        Request request = new Request.Builder()
                .url(BASE_URL + playlistId + "/fill-playlist")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                //TODO: update status
            }
        }
    }
}
record AlbumData(int official, int access, String title) {}
