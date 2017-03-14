package com.fzc;

import com.fzc.domain.Person;
import com.fzc.domain.proto.PersonProto;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.*;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.protobuf.ProtoConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

import javax.net.SocketFactory;
import javax.net.ssl.*;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.security.KeyStore;
import java.security.SecureRandom;
import java.security.cert.Certificate;
import java.security.cert.CertificateException;
import java.security.cert.CertificateFactory;
import java.security.cert.X509Certificate;
import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProtocolBuffDemoApplicationTests {


    interface ApiService {
        @GET("/person/json")
        @Headers({"Content-Type: application/json"})
        public Call<Person> getPersonByJson();

        @GET("/person/proto")
        @Headers({"Content-Type: application/x-proto"})
        public Call<PersonProto> getPersonProto();
    }

    private static final String url = "http://127.0.0.1:9000";

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

    ApiService jsonService = retrofit.create(ApiService.class);

    ApiService protoService = new Retrofit.Builder()
            .baseUrl(url)
            .addConverterFactory(ProtoConverterFactory.create())
            .build()
            .create(ApiService.class);

    @BeforeClass
    public static void before() {
//        SpringApplication.run(ProtocolBuffDemoApplication.class);
    }

    @Test
    public void testJson() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);
        jsonService.getPersonByJson().enqueue(new Callback<Person>() {
            @Override
            public void onResponse(Call<Person> call, Response<Person> response) {
                System.out.println(response.raw());
                latch.countDown();
            }

            @Override
            public void onFailure(Call<Person> call, Throwable throwable) {
                latch.countDown();
            }
        });
        latch.await();
    }

    @Test
    public void testProto() throws Exception {
        CountDownLatch latch = new CountDownLatch(1);


        protoService.getPersonProto().enqueue(new Callback<PersonProto>() {
            @Override
            public void onResponse(Call<PersonProto> call, Response<PersonProto> response) {
                System.out.println(response);
                latch.countDown();

            }

            @Override
            public void onFailure(Call<PersonProto> call, Throwable throwable) {
                latch.countDown();
            }
        });
        latch.await();

        PersonProto personProto = PersonProto.newBuilder()
                .build();

    }

    @Test
    public void serializeAndDeserialize() throws Exception {
        PersonProto personProto = PersonProto.newBuilder()
                .setAge(4454)
                .setSecondName("asfdafda")
                .setFirstName("aaaa")
                .addPhoneNumbers(PersonProto.PhoneNumber.newBuilder()
                        .setPhoneNumber(4554)
                        .setAreaCode(4545)
                        .build())
                .build();

        byte[] bytes = personProto.toByteArray();

        PersonProto proto = PersonProto.parseFrom(bytes);
//        InputStream inputStream = null;
//        inputStream.
    }

    @Test
    public void testHttp2WithHttps() throws Exception {

        X509Certificate[] x509Certificates = new X509Certificate[1];

        SSLContext sslContext = SSLContext.getInstance("TLS");

        KeyStore keyStore = KeyStore.getInstance(KeyStore.getDefaultType());

        Certificate certificate = CertificateFactory.getInstance("X.509")
                .generateCertificate(loadCertificate());
        keyStore.load(null);
        keyStore.setCertificateEntry("mycer", certificate);

        x509Certificates[0] = (X509Certificate) certificate;

        TrustManagerFactory trustManagerFactory = TrustManagerFactory
                .getInstance(TrustManagerFactory.getDefaultAlgorithm());
        trustManagerFactory.init(keyStore);
        sslContext.init(null, trustManagerFactory.getTrustManagers(), new SecureRandom());


        OkHttpClient client = new OkHttpClient.Builder()
                .hostnameVerifier(new HostnameVerifier() {
                    @Override
                    public boolean verify(String s, SSLSession sslSession) {
                        return true;
                    }
                })
                .sslSocketFactory(sslContext.getSocketFactory(), new X509TrustManager() {
                    @Override
                    public void checkClientTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public void checkServerTrusted(X509Certificate[] x509Certificates, String s) throws CertificateException {

                    }

                    @Override
                    public X509Certificate[] getAcceptedIssuers() {
                        return x509Certificates;
                    }
                })
                .build();

        Request request = new Request.Builder()
                .url("https://127.0.0.1:9000/person/json")
                .build();

        okhttp3.Response response = client.newCall(request).execute();

        System.out.println(response.body().string());
    }


    public InputStream loadCertificate() throws Exception {
        ResourceLoader loader = new DefaultResourceLoader();
        Resource resource = loader.getResource("classpath:my.cer");
        boolean exit = resource.exists();

        ClassPathResource classPathResource = (ClassPathResource) resource;
        return classPathResource.getInputStream();
    }
}