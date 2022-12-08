package com.via.sep6.bestmovies.repository;

import com.via.sep6.best.movies.movie.MovieService;
import com.via.sep6.best.movies.movie.MovieServiceOuterClass;
import io.quarkus.grpc.GrpcClient;

import javax.enterprise.context.ApplicationScoped;
import java.time.Duration;
import java.util.List;

@ApplicationScoped
public class UserRepository {

    @GrpcClient
    MovieService movieService;

    public List<MovieServiceOuterClass.Movie> getTopMovies() {
        MovieServiceOuterClass.GetMoviesRequest request = MovieServiceOuterClass.GetMoviesRequest.newBuilder().build();
        MovieServiceOuterClass.GetMoviesResponse response = movieService.getMovies(request).await().atMost(Duration.ofSeconds(5));
        return response.getMoviesList();
    }
}
