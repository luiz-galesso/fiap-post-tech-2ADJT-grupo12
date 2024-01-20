package com.fase2.techchallenge.fiap.estacionamento.gestaoestacionamento.retrofit;

import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@Component
public class ServiceGenerator {

    private static String BASE_URL_STATIC ;

    @Value("${name}")
    public void loadOnelinkConfig(@Value("${name}") String baseUrl) {
        System.out.println("teste"+baseUrl);
        BASE_URL_STATIC = "http://localhost/";
    }
    //private static String BASE_URL;

    private static Retrofit.Builder builder
            = new Retrofit.Builder()
            .baseUrl("http://localhost/")
            .addConverterFactory(GsonConverterFactory.create());

    private static Retrofit retrofit = builder.build();

    private static OkHttpClient.Builder httpClient
            = new OkHttpClient.Builder();

    public static <S> S createService(Class<S> serviceClass) {
        return retrofit.create(serviceClass);
    }
}