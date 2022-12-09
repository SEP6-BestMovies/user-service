package com.via.sep6.bestmovies.service;

import com.via.sep6.best.movies.movie.MutinyMovieServiceGrpc;
import io.grpc.ManagedChannel;
import io.quarkus.grpc.runtime.config.GrpcServerConfiguration;
import io.quarkus.test.Mock;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ServiceTest {

    @Mock
    private MutinyMovieServiceGrpc.MovieServiceImplBase movieServiceMock;
    private ManagedChannel channel;
    private GrpcServerConfiguration grpcService;

    @BeforeEach
    public void setup() {

    }

    @Test
    public void testGetTopMovies() {

    }

    @Test
    public void testAddMoviesToFavourite() {

    }
}
