package com.via.sep6.bestmovies.repository;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class InitAuth {
    private final FirebaseApp app;

    public InitAuth(FirebaseApp app) {
        FileInputStream serviceAccount;{
            try {
                serviceAccount = new FileInputStream("home/unknown_user/Downloads/serviceaccount.json");
            } catch (FileNotFoundException e) {
                throw new RuntimeException(e);
            }
        }

        FirebaseOptions options = null;
        try {
            options = FirebaseOptions.builder()
                    .setCredentials(GoogleCredentials.getApplicationDefault())
                    .build();

            FirebaseApp.initializeApp(options);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        this.app = FirebaseApp.initializeApp(options);
    }
}
