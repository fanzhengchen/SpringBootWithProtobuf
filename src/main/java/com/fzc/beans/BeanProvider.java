package com.fzc.beans;

import com.fzc.domain.Person;
import com.fzc.domain.PhoneNumber;
import com.fzc.domain.proto.PersonProto;
import io.undertow.Undertow;
import io.undertow.UndertowOptions;
import org.springframework.boot.context.embedded.jetty.JettyEmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.jetty.JettyServerCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowBuilderCustomizer;
import org.springframework.boot.context.embedded.undertow.UndertowEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.protobuf.ProtobufHttpMessageConverter;

import java.util.ArrayList;

/**
 * Created by mark on 17-3-7.
 */
@Configuration
public class BeanProvider {


    @Bean
    public UndertowEmbeddedServletContainerFactory embeddedServletContainerFactory() {
        UndertowEmbeddedServletContainerFactory factory = new UndertowEmbeddedServletContainerFactory();
        factory.addBuilderCustomizers(new UndertowBuilderCustomizer() {
            @Override
            public void customize(Undertow.Builder builder) {
                builder.setServerOption(UndertowOptions.ENABLE_HTTP2, true);
            }
        });
        return factory;
    }

    @Bean
    public ProtobufHttpMessageConverter protobufHttpMessageConverter() {
        return new ProtobufHttpMessageConverter();
    }


    @Bean
    public Person providePerson() {
        Person person = new Person();
        person.setAge(20);
        person.setFirstName("first");
        person.setSecondName("second");


        ArrayList<PhoneNumber> phoneNumbers = new ArrayList<>();
        PhoneNumber number = new PhoneNumber();
        number.setAreaCode(0571);
        number.setPhoneNumber(415454546l);


        phoneNumbers.add(number);


        person.setPhoneNumbers(phoneNumbers);

        return person;
    }

    @Bean
    public PersonProto providePersonProto() {
        return PersonProto.newBuilder()
                .setSecondName("first")
                .setSecondName("second")
                .setAge(20)
                .addPhoneNumbers(PersonProto.PhoneNumber
                        .newBuilder()
                        .setAreaCode(0571)
                        .setPhoneNumber(415454546l))
                .build();
    }
}
