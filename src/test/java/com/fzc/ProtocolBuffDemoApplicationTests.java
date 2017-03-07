package com.fzc;

import com.fzc.domain.Person;
import com.fzc.domain.proto.PersonProto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.converter.protobuf.ProtoConverterFactory;
import retrofit2.http.GET;
import retrofit2.http.Headers;

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
    }
}
