package mx.edu.cetys.fernandez.adrian.codingchallenge;

import mx.edu.cetys.fernandez.adrian.codingchallenge.service.OpenAIService;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class OpenAIServiceTest {

    private static final Logger logger = LoggerFactory.getLogger(OpenAIServiceTest.class);

    private final OpenAIService openAIService = new OpenAIService();

    @Test
    public void testGetSummaryFromOpenAI() throws IOException {
        logger.info("Iniciando la prueba de OpenAIService con la API real");

        String movieTitle = "Inception";
        String summary = openAIService.getSummaryFromOpenAI(movieTitle);

        logger.info("Resumen recibido para la película {}: {}", movieTitle, summary);

        assertTrue(summary.startsWith("Esta película es:"),
                "El resumen no comienza con las palabras esperadas. Resumen: " + summary);
    }
}
