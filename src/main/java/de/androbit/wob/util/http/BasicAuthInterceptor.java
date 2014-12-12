package de.androbit.wob.util.http;

import retrofit.RequestInterceptor;

import java.io.UnsupportedEncodingException;
import java.util.Base64;

import static java.lang.String.format;

public class BasicAuthInterceptor implements RequestInterceptor {
    String userName;
    String password;

    public BasicAuthInterceptor(String userName, String password) {
        this.userName = userName;
        this.password = password;
    }

    @Override
    public void intercept(RequestFacade request) {
        request.addHeader("Authorization", authorizationHeaderValue());
    }

    private String authorizationHeaderValue() {
        return format("Basic %s", base64(format("%s:%s", userName, password)));
    }

    private String base64(String input) {
        try {
            return Base64.getEncoder().encodeToString(input.getBytes("UTF-8"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}