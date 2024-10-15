package mx.edu.cetys.fernandez.adrian.coding_challenge;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class MovieService {
    private final String API_KEY = "88295e9002394ab72b2199f25ff94ce7";
}
