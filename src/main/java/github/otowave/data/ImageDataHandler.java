package github.otowave.data;

import okhttp3.*;

import java.io.File;
import java.io.IOException;

import static github.otowave.data.DataHandler.client;

public class ImageDataHandler {
    public static String uploadImage(String imageType, String filePath, String uploaderId) {
        String imageId = "";
        File imageFile = new File(filePath);
        String fileExtension = DataHandler.getFileExtension(filePath);

        RequestBody requestBody = new MultipartBody.Builder()
                .setType(MultipartBody.FORM)
                .addFormDataPart("imageType", imageType)
                .addFormDataPart("sourceId", uploaderId)
                .addFormDataPart("image", "image."+fileExtension,
                        RequestBody.create(imageFile, MediaType.parse("image/"+fileExtension)))
                .build();

        Request request = new Request.Builder()
                .url(DataHandler.baseUrl+uploaderId+"/new-image")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                imageId = response.body().string();
                //TODO: update status
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        return imageId;
    }

    public static void applyImage() {
    }
}
