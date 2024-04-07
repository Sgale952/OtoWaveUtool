package github.otowave.data;

import com.google.gson.JsonObject;
import okhttp3.*;

import java.io.File;

import static github.otowave.data.DataHandler.BASE_URL;
import static github.otowave.data.DataHandler.client;

public class MusicDataHandler {
    public static String uploadMusic(String authorId, String title, String eContent, String genre, String filePath) throws Exception {
        String musicId;
        File audioFile = new File(filePath);
        String fileExtension = DataHandler.getFileExtension(filePath);
        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("title", title)
                .addFormDataPart("eContent", eContent)
                .addFormDataPart("genre", genre)
                .addFormDataPart("audio", "audio."+fileExtension,
                        RequestBody.create(audioFile, MediaType.parse("audio/"+fileExtension)))
                .build();

        Request request = new Request.Builder()
                .url(DataHandler.BASE_URL +authorId+"/new-music")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("Error upload music");
            }

            musicId = response.body().string();
        }

        return musicId;
    }

    public static void deleteMusic(String musicId, String userId) throws Exception {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("musicId", musicId);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(jsonObject.toString(), mediaType);

        Request request = new Request.Builder()
                .url(BASE_URL + userId + "/" + musicId + "/delete-music")
                .delete(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("Error delete music");
            }
        }
    }
}