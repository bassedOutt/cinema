package com.murmylo.epam.cinema.servlets.hashing;

import com.murmylo.epam.cinema.db.entity.User;
import org.apache.log4j.Logger;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Base64;

public class Hashing {

    private static final SecureRandom secureRandom = new SecureRandom();

    private static final Logger logger = Logger.getLogger(Hashing.class);
    private static final int saltLen = 10;

    public static String getEncryptedPassword(String password, byte[] salt) throws NoSuchAlgorithmException, InvalidKeySpecException {

        int iterations = 21241;
        int keyLen = 128;

        String algorithm = "PBKDF2WithHmacSHA1";

        KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, iterations, keyLen);
        SecretKeyFactory f = SecretKeyFactory.getInstance(algorithm);

        byte[] encBytes = f.generateSecret(spec).getEncoded();

        return Base64.getEncoder().encodeToString(salt) + " " + Base64.getEncoder().encodeToString(encBytes);
    }

    public static boolean authenticatePass(User user, String password) {

        //retrieving salt and hashed pass
        byte[] salt = Base64.getDecoder().decode(user.getPassword().split(" ")[0]);

        try {
            String encPass = getEncryptedPassword(password, salt);
            if (encPass.equals(user.getPassword()))
                return true;
        } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
            logger.error(e);
        }

        return false;
    }

    public static byte[] getNewSalt() {
        byte[] salt = new byte[saltLen];
        secureRandom.nextBytes(salt);

        return salt;
    }
}
