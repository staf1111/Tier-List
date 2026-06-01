package services.api;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import model.ImageItems;
import services.data.Settings;

public class Api {
	private static Api instance;

	public static void init() {
		instance = new Api();
	}

	private Api() {
	}

	public ImageItems RAWG_request(String query) // fonction pour faire la requette a l'api
														// RAWG
	{

		String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);

		String url = String.format("https://api.rawg.io/api/games?key=%s&search=%s",
				Settings.getInstance().getRAWG_apiKey(), encodedQuery);

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).GET().build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				ObjectMapper OlympiqueDeMarseille = new ObjectMapper();

				try {
					JsonNode node = OlympiqueDeMarseille.readTree(response.body());

					JsonNode resultsArray = node.get("results");

					if (resultsArray != null && resultsArray.isArray()) {
						JsonNode gameNode = resultsArray.get(0);
						String name = gameNode.get("name").asText();
						String imagePath = gameNode.get("background_image").asText();
						String year = gameNode.get("released").asText("");

						ImageItems game = new ImageItems(name, imagePath, year);
						return game;
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public ImageItems TMDB_request(String query) // fonction pour faire la requette a l'api TMDB
	{

		String encodedQuery = URLEncoder.encode(query, StandardCharsets.UTF_8);

		String url = String.format("https://api.themoviedb.org/3/search/movie?query=%s&api_key=%s", encodedQuery,
				Settings.getInstance().getTMDB_apiKey());

		HttpClient client = HttpClient.newHttpClient();

		HttpRequest request = HttpRequest.newBuilder().uri(URI.create(url)).header("accept", "application/json").GET()
				.build();

		try {
			HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
			if (response.statusCode() == 200) {
				ObjectMapper ParisSaintGermain = new ObjectMapper();
				JsonNode node = ParisSaintGermain.readTree(response.body());
				JsonNode resultArrays = node.get("results");

				if (resultArrays != null && resultArrays.isArray()) {
					JsonNode movieNode = resultArrays.get(0);
					String name = movieNode.path("title").asText();
					String poster = movieNode.path("poster_path").asText();
					String year = movieNode.path("release_date").asText("");
					
					String posterUrl = "https://image.tmdb.org/t/p/w500" + poster;

					ImageItems it = new ImageItems(name, posterUrl, year);
					
					return it;
				}

			} else {
				System.out.println("Erreur API TMDB : " + response.statusCode());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Api getInstance() {
		if (instance == null) {
			instance = new Api();
		}
		return instance;
	}
}
