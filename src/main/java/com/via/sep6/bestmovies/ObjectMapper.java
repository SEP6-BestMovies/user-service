package com.via.sep6.bestmovies;

import com.via.sep6.best.movies.MovieServiceOuterClass;
import com.via.sep6.best.movies.user.UserServiceOuterClass;

public class ObjectMapper {

    public static UserServiceOuterClass.Movie movieToUserMovie(MovieServiceOuterClass.Movie movie) {
        return UserServiceOuterClass.Movie.newBuilder().setId(movie.getId()).setTitle(movie.getTitle()).setReleaseYear(movie.getReleaseYear()).build();
    }
}
