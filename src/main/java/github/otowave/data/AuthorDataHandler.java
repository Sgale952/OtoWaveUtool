package github.otowave.data;

import com.google.gson.JsonObject;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import static github.otowave.data.DataHandler.BASE_URL;
import static github.otowave.data.DataHandler.client;

public class AuthorDataHandler {
    public static String uploadUser(String nickname, String email, String password) throws Exception {
        String userId = "";
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("access", 2);
        jsonObject.addProperty("nickname", nickname);
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("password", password);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(jsonObject.toString(), mediaType);

        Request request = new Request.Builder()
                .url(BASE_URL +"new-user")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful()) {
                userId = response.body().string();
                //TODO: update status
            }
        }

        return userId;
    }
}