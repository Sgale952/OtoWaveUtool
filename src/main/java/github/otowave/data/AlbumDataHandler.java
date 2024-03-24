package github.otowave.data;

import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static github.otowave.data.DataHandler.*;

public class AlbumDataHandler {
    public static String uploadAlbum(String authorId, String title) {
        String albumId = "";
        AlbumData albumData = new AlbumData(1, 1, title);
        String json = gson.toJson(albumData);

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(json, JSON);
        Request request = new Request.Builder()
                .url(baseUrl+authorId+"/new-playlist")
                .post(body)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                albumId = response.body().string();
                //TODO: update status
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return albumId;
    }
}
record AlbumData(int official, int access, String title) {}
