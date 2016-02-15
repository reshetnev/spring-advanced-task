package com.epam.reshetnev.spring.advanced.parser;

import java.io.IOException;
import java.util.List;

import com.epam.reshetnev.spring.core.domain.User;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class UserListParser {

    public static List<User> getUserList(String data) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        List<User> users = mapper.readValue(data,
                TypeFactory.defaultInstance()
                .constructCollectionType(
                        List.class,
                        User.class));
        return users;
    }
}
