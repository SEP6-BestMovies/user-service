./mvnw clean package -Pnative -Dquarkus.native.container-build=true
@REM docker build -f src/main/docker/Dockerfile.native -t com/via/sep6/bestmovies/userservice:dev .