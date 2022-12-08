package com.via.sep6.bestmovies;


import com.via.sep6.best.movies.movie.MovieServiceOuterClass.Movie;
import com.via.sep6.best.movies.user.UserService;
import com.via.sep6.best.movies.user.UserServiceOuterClass.GetTopMoviesRequest;
import com.via.sep6.best.movies.user.UserServiceOuterClass.GetTopMoviesResponse;
import com.via.sep6.bestmovies.repository.UserRepository;
import io.quarkus.grpc.GrpcService;
import io.smallrye.mutiny.Uni;
import javax.inject.Inject;
import java.util.List;

@GrpcService
public class UserServiceImpl implements UserService {

    @Inject
    UserRepository repository;

    @Override
    public Uni<GetTopMoviesResponse> getTopMovies(GetTopMoviesRequest request) {
        List<Movie> movies = repository.getTopMovies();
        GetTopMoviesResponse.Builder builder = GetTopMoviesResponse.newBuilder();

        for (Movie movie : movies) {
           builder.addMovie(ObjectMapper.movieToUserMovie(movie));
        }

        return Uni.createFrom().item(builder.build());
    }
}
