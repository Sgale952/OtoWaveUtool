package github.otowave.data;

import okhttp3.*;

import java.io.File;

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
                .url(DataHandler.BASE_URL +authorId+"/new-song")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("error upload music");
            }

            musicId = response.body().string();
        }

        return musicId;
    }
}