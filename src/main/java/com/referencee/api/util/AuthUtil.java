package com.referencee.api.util;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.referencee.api.util.exception.UtilException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

public class AuthUtil {
    private static final class SingletonHolder {
        private static final AuthUtil instance = new AuthUtil();
    }

    private FirebaseAuth firebaseAuth;
    private Logger logger = LogManager.getLogger(getClass());
    private final String SA_PATH = "referencee-sa.json";

    private AuthUtil() {
        try (InputStream stream = Objects.requireNonNull(AuthUtil.class.getClassLoader().getResourceAsStream(SA_PATH))) {
            FirebaseOptions options = new FirebaseOptions
                    .Builder()
                    .setCredentials(GoogleCredentials.fromStream(stream))
                    .build();
            FirebaseApp firebaseApp = FirebaseApp.initializeApp(options);
            firebaseAuth = FirebaseAuth.getInstance(firebaseApp);
        } catch (IOException e) {
            logger.error(e.getMessage());
        }
    }

    public static AuthUtil getInstance() {
        return SingletonHolder.instance;
    }

    public boolean verifyIdToken(String token) {
        try {
            firebaseAuth.verifyIdToken(token);
            return true;
        } catch (FirebaseAuthException e) {
            logger.warn(e.getMessage());
            return false;
        }
    }

    public String retrieveEmail(String token) throws UtilException {
        try {
            return firebaseAuth.verifyIdToken(token).getEmail();
        } catch (FirebaseAuthException e) {
            logger.warn(e.getMessage());
            throw new UtilException(e.getMessage());
        }
    }



}
