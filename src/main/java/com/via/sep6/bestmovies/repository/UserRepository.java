package com.via.sep6.bestmovies.repository;

import com.google.api.core.ApiFuture;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.google.cloud.firestore.WriteResult;
import com.via.sep6.best.movies.movie.MovieService;
import com.via.sep6.best.movies.movie.MovieServiceOuterClass;
import io.quarkus.grpc.GrpcClient;
import io.quarkus.runtime.Startup;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@ApplicationScoped
public class UserRepository {

    @Inject
    @GrpcClient("MovieService")
    MovieService movieService;

    Firestore db;

    public UserRepository() {
    }

    @PostConstruct
    public void init() throws IOException{
            FirestoreOptions firestoreOptions =
                    FirestoreOptions.getDefaultInstance().toBuilder()
                            .setProjectId("bestmovies-e13f7")
                            .setCredentials(GoogleCredentials.getApplicationDefault())
                            .build();
            db = firestoreOptions.getService();
    }

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
