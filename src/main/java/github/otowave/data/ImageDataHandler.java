package github.otowave.data;

import com.google.gson.JsonObject;
import okhttp3.*;

import java.io.File;
import java.io.IOException;

import static github.otowave.data.DataHandler.BASE_URL;
import static github.otowave.data.DataHandler.client;

public class ImageDataHandler {
    public static String uploadImage(String uploaderId, String filePath) throws IOException {
        String imageId = "";
        File imageFile = new File(filePath);
        String fileExtension = DataHandler.getFileExtension(filePath);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("image", "image."+fileExtension,
                        RequestBody.create(imageFile, MediaType.parse("image/"+fileExtension)))
                .build();

        Request request = new Request.Builder()
                .url(DataHandler.BASE_URL + uploaderId + "/new-image")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                imageId = response.body().string();
                //TODO: update status
            }
        }

        return imageId;
    }

    public static void applyImage(String uploaderId, String imageId, String sourceId, String imageType) throws Exception {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("imageType", imageType);
        jsonObject.addProperty("imageId", imageId);
        jsonObject.addProperty("sourceId", sourceId);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(jsonObject.toString(), mediaType);

        Request request = new Request.Builder()
                .url(BASE_URL + uploaderId + "/apply-image")
                .patch(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                //TODO: update status
            }
        }
    }
}
