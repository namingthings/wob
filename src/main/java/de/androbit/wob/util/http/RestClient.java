package de.androbit.wob.util.http;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.squareup.okhttp.OkHttpClient;
import retrofit.RequestInterceptor;
import retrofit.RestAdapter;
import retrofit.client.OkClient;
import retrofit.converter.JacksonConverter;

import javax.net.ssl.*;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

public class RestClient {

    JacksonConverter jacksonConverter = new JacksonConverter(new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false));

    RestAdapter.Builder restAdapterBuilder = new RestAdapter.Builder()
            .setConverter(jacksonConverter);

    OkHttpClient okHttpClient = new OkHttpClient();

    public <T> T api(Class<T> apiType) {
        return restAdapterBuilder
                .setClient(createClient())
                .build()
                .create(apiType);
    }

    protected OkClient createClient() {
        return new OkClient(okHttpClient);
    }

    public RestClient allowAllCertificates() {
        okHttpClient.setHostnameVerifier( (name, session) -> true );
        okHttpClient.setSslSocketFactory(createSslSocketFacktory());
        return this;
    }

    private SSLSocketFactory createSslSocketFacktory() {
        TrustManager[] certs = new TrustManager[] { new X509TrustManager() {
            @Override
            public X509Certificate[] getAcceptedIssuers() {
                return null;
            }

            @Override
            public void checkServerTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }

            @Override
            public void checkClientTrusted(X509Certificate[] chain,
                                           String authType) throws CertificateException {
            }
        } };
        SSLContext ctx = null;
        try {
            ctx = SSLContext.getInstance("TLS");
            ctx.init(null, certs, new SecureRandom());
        } catch (java.security.GeneralSecurityException ex) {
        }
        return ctx.getSocketFactory();
    }

    public RestClient withEndPoint(String url) {
        restAdapterBuilder.setEndpoint(url);
        return this;
    }

    public RestClient withBasicAuth(String userName, String password) {
        restAdapterBuilder.setRequestInterceptor(new BasicAuthInterceptor(userName, password));
        return this;
    }

    public RestClient withInterceptor(RequestInterceptor interceptor) {
        restAdapterBuilder.setRequestInterceptor(interceptor);
        return this;
    }

}
