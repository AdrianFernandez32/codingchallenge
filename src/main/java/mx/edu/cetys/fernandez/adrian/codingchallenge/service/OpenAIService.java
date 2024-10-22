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

    // Inyectar la clave API desde el archivo application.properties
    @Value("${openai.api-key}")
    private String apiKey;

    public String getSummaryFromOpenAI(String movieTitle) throws IOException {
        // Construir el prompt
        String prompt = "Provide a brief summary for the movie titled: " + movieTitle;

        // Crear el cuerpo JSON para la solicitud
        JSONObject jsonBody = new JSONObject();
        jsonBody.put("model", "text-davinci-003");  // Modelo de OpenAI (puedes ajustar según tus necesidades)
        jsonBody.put("prompt", prompt);
        jsonBody.put("max_tokens", 100);  // Limita la cantidad de tokens generados
        jsonBody.put("temperature", 0.7); // Ajusta la creatividad

        // Crear el cuerpo de la solicitud HTTP
        RequestBody body = RequestBody.create(jsonBody.toString(), MediaType.get("application/json; charset=utf-8"));

        // Crear la solicitud HTTP con la API key en el header
        Request request = new Request.Builder()
                .url(OPENAI_API_URL)
                .post(body)
                .addHeader("Authorization", "Bearer " + apiKey)
                .build();

        // Ejecutar la solicitud y manejar la respuesta
        try (Response response = client.newCall(request).execute()) {
            if (!response.isSuccessful()) {
                throw new IOException("Error en la llamada a OpenAI API: " + response);
            }

            // Procesar la respuesta JSON para obtener el resumen
            String responseBody = response.body().string();
            JSONObject jsonResponse = new JSONObject(responseBody);
            String summary = jsonResponse.getJSONArray("choices").getJSONObject(0).getString("text").trim();

            return summary;
        }
    }
}
