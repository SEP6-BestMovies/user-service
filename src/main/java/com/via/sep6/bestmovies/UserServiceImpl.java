package com.via.sep6.bestmovies;


import com.via.sep6.best.movies.MovieServiceOuterClass;
import com.via.sep6.best.movies.user.UserService;
import com.via.sep6.best.movies.user.UserServiceOuterClass;
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
    public Uni<UserServiceOuterClass.GetTopMoviesResponse> getTopMovies(UserServiceOuterClass.GetTopMoviesRequest request) {
        List<MovieServiceOuterClass.Movie> movies = repository.getTopMovies();
        UserServiceOuterClass.GetTopMoviesResponse.Builder builder = UserServiceOuterClass.GetTopMoviesResponse.newBuilder();

        for (MovieServiceOuterClass.Movie movie : movies) {
           builder.addMovie(ObjectMapper.movieToUserMovie(movie));
        }

        return Uni.createFrom().item(builder.build());
    }
}
