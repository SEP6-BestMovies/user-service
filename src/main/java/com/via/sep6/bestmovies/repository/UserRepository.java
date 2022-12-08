package com.via.sep6.bestmovies.repository;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.cloud.FirestoreClient;
import com.via.sep6.best.movies.movie.MovieService;
import com.via.sep6.best.movies.movie.MovieServiceOuterClass;
import io.quarkus.grpc.GrpcClient;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class UserRepository {

    @GrpcClient
    MovieService movieService;

    public UserRepository() throws IOException {
        InputStream serviceAccount = new FileInputStream("serviceaccount.json");
        GoogleCredentials credentials = GoogleCredentials.fromStream(serviceAccount);
        FirebaseOptions options = new FirebaseOptions.Builder()
                .setCredentials(credentials)
                .build();
        FirebaseApp.initializeApp(options);

        Firestore db = FirestoreClient.getFirestore();
    }

    Firestore db = FirestoreClient.getFirestore();

    public List<MovieServiceOuterClass.Movie> getTopMovies() {
        MovieServiceOuterClass.GetMoviesRequest request = MovieServiceOuterClass.GetMoviesRequest.newBuilder().build();
        MovieServiceOuterClass.GetMoviesResponse response = movieService.getMovies(request).await().atMost(Duration.ofSeconds(5));
        return response.getMoviesList();
    }

    public void addMovieToFavourite(String username, int movieId) {
        DocumentReference docRef = db.collection("users").document(username);
        Map<String, Integer> data = new HashMap<>();
        data.put("movie id", movieId);

        ApiFuture<WriteResult> result = docRef.set(data);
    }
}
