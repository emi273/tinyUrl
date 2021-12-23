package com.example.tinyUrl.service;

import org.springframework.beans.factory.annotation.Autowired;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

public class HashService {

    private String url;
    @Autowired
    public HashService(String url) {this.url=url;}

    public String hash(){
        byte[] hash = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            hash = md.digest(url.getBytes());

        } catch (NoSuchAlgorithmException e) { e.printStackTrace(); }
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i <  hash.length; ++i) {
            String hex = Integer.toHexString(hash[i]);
            if (hex.length() == 1) {
                sb.append(0);
                sb.append(hex.charAt(hex.length() - 1));
            } else {
                sb.append(hex.substring(hex.length() - 2));
            }
        }
        return sb.toString();
    }


}
