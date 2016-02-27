package com.epam.reshetnev.spring.advanced.parser;

import java.io.IOException;
import java.util.List;

import com.epam.reshetnev.spring.core.domain.UserAccount;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.Lists;

public class AccountListParser {

    public static List<UserAccount> getAccountList(String data) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<UserAccount> accounts = Lists.newArrayList();
        if (!data.isEmpty()) {
            accounts = mapper.readValue(data,
                    TypeFactory.defaultInstance()
                    .constructCollectionType(
                            List.class,
                            UserAccount.class));
        }
        return accounts;
    }
}
