package com.via.sep6.bestmovies;

import com.via.sep6.best.movies.movie.MovieServiceOuterClass;
import com.via.sep6.best.movies.user.UserServiceOuterClass.Movie;

public class ObjectMapper {

    public static Movie movieToUserMovie(MovieServiceOuterClass.Movie movie) {
        return Movie.newBuilder().setId(movie.getId()).setTitle(movie.getTitle()).setReleaseYear(movie.getReleaseYear()).build();
    }
}
