package github.otowave.data;

import com.google.gson.JsonObject;
import okhttp3.MediaType;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import static github.otowave.data.DataHandler.BASE_URL;
import static github.otowave.data.DataHandler.client;

public class AuthorDataHandler {
    public static String uploadUser(String nickname, String email, String password) throws Exception {
        String encryptedPassword = Encryptor.sha256Encrypt(password);
        String userId;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("access", 2);
        jsonObject.addProperty("nickname", nickname);
        jsonObject.addProperty("email", email);
        jsonObject.addProperty("password", encryptedPassword);

        MediaType mediaType = MediaType.parse("application/json");
        RequestBody requestBody = RequestBody.create(jsonObject.toString(), mediaType);

        Request request = new Request.Builder()
                .url(BASE_URL + "register")
                .post(requestBody)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("error upload user");
            }

            userId = response.body().string();
        }

        return userId;
    }

    public static void deleteUser(String userId) throws Exception {
        Request request = new Request.Builder()
                .url(BASE_URL + userId + "/delete")
                .delete()
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new Exception("Error delete user");
            }
        }
    }
}

class Encryptor {
    static String sha256Encrypt(String password) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] hash = digest.digest(password.getBytes("UTF-8"));

        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) hexString.append('0');
            hexString.append(hex);
        }

        password = hexString.toString();
        return password;
    }
}