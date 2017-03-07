package com.fzc.controller;

import com.fzc.domain.Person;
import com.fzc.domain.proto.PersonProto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by mark on 17-3-7.
 */
@RestController
@RequestMapping("/person")
public class MainController {

    @Autowired
    Person person;

    @Autowired
    PersonProto personProto;

    @RequestMapping("/json")
    public Person testJson() {
        return person;
    }

    @RequestMapping(value = "/proto", produces = "application/x-protobuf")
    public PersonProto testProtoc() {
        return personProto;
    }
}
