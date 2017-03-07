package com.fzc.beans;

import com.fzc.domain.Person;
import com.fzc.domain.PhoneNumber;
import com.fzc.domain.proto.PersonProto;
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
