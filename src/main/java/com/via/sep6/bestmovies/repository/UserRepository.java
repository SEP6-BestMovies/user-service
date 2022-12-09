package com.via.sep6.bestmovies.repository;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.firestore.DocumentReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.FirestoreOptions;
import com.via.sep6.best.movies.movie.MovieService;
import com.via.sep6.best.movies.movie.MovieServiceOuterClass;
import io.quarkus.grpc.GrpcClient;
import io.smallrye.mutiny.Uni;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

@Path("/")
@Produces(MediaType.APPLICATION_JSON)
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

    @GET
    @Path("/mutiny")
    public Uni<List<MovieServiceOuterClass.Movie>> getTopMovies() {
        MovieServiceOuterClass.GetMoviesRequest request = MovieServiceOuterClass.GetMoviesRequest.newBuilder().build();
        return movieService.getMovies(request)
                .onItem()
                .transform(MovieServiceOuterClass.GetMoviesResponse::getMoviesList);
    }

    public void addMovieToFavourite(String username, int movieId) {
        DocumentReference docRef = db.collection("users").document(username);
        Map<String, Integer> data = new HashMap<>();
        data.put("movie id", movieId);
        docRef.set(data);
    }
}
