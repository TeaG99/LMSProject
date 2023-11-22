package hr.tvz.java.aplikacija.service;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.HttpTransport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.security.GeneralSecurityException;

@Service
public class BookService {

    @Value("${google.books.api.key}")
    private String apiKey;

    public String searchBooks(String query) {
        try {
            HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
            HttpRequestFactory requestFactory = httpTransport.createRequestFactory();

            GenericUrl url = new GenericUrl("https://www.googleapis.com/books/v1/volumes");
            url.put("q", query);
            url.put("key", apiKey);

            HttpRequest request = requestFactory.buildGetRequest(url);
            HttpResponse response = request.execute();

            if (response.isSuccessStatusCode()) {
                return response.parseAsString();
            } else {
                // Handle unsuccessful response (log or throw an exception)
                return "Failed to fetch books. Status code: " + response.getStatusCode();
            }
        } catch (GeneralSecurityException | IOException e) {
            // Handle exceptions
            e.printStackTrace(); // Logging or appropriate error handling
            return "An error occurred while fetching books.";
        }
    }
}
