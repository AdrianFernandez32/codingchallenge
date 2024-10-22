package mx.edu.cetys.fernandez.adrian.codingchallenge.service;

import okhttp3.*;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class OpenAIService {

    private static final String OPENAI_API_URL = "https://api.openai.com/v1/completions";
    private final OkHttpClient client = new OkHttpClient();

    @Value("${spring.ai.openai.api-key}")
    private String apiKey;

    public String getSummaryFromOpenAI(String movieTitle) throws IOException {
        String prompt = "Provide a brief summary for the movie titled: " + movieTitle;

        JSONObject jsonBody = new JSONObject();
        jsonBody.put("model", "text-davinci-003");
        jsonBody.put("prompt", prompt);
        jsonBody.put("max_tokens", 100);
        jsonBody.put("temperature", 0.7);

        RequestBody body = RequestBody.create(jsonBody.toString(), MediaType.get("application/json; charset=utf-8"));

        Request request = new Request.Builder()
                .url(OPENAI_API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();

        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Error en la llamada a OpenAI API: " + response);
            }

            String responseBody = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseBody);
            String summary = jsonResponse.getJSONArray("choices").getJSONObject(0).getString("text").trim();

            return summary;
        }
    }
}
