package interfaces;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpHeaders;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.concurrent.TimeUnit;

import org.w3c.dom.Entity;

public class whisher_api {
	private static String apiKey = "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";

	public static void send_request(String url) {
		String filePath = url;

		HttpClient httpClient = HttpClient.newBuilder().connectTimeout(null).build();

		// Crear la solicitud HTTP POST
        HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
                .uri(URI.create("https://api.openai.com/v1/audio/transcriptions"))
                .header("Authorization", "Bearer " + apiKey)
                .header("Content-Type", "multipart/form-data");
        // Leer el archivo MP3
        try {
            byte[] fileBytes = Files.readAllBytes(Paths.get(filePath));

            // Crear el cuerpo de la solicitud multipart/form-data
            HttpRequest.BodyPublisher requestBody = HttpRequest.BodyPublishers.ofByteArray(fileBytes);




            requestBuilder.POST(requestBody);
        } catch (IOException e) {
            e.printStackTrace();
        }


        try {
            HttpRequest request = requestBuilder.build();
            HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());


            HttpHeaders headers = response.headers();
            headers.map().forEach((key, value) -> System.out.println(key + ": " + value));


            String responseBody = response.body();
            System.out.println(responseBody);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        
	}
}
