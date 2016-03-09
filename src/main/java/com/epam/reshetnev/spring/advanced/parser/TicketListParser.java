package com.epam.reshetnev.spring.advanced.parser;

import java.io.IOException;
import java.util.List;

import com.epam.reshetnev.spring.core.domain.Ticket;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.google.common.collect.Lists;

public class TicketListParser {

    public static List<Ticket> getTicketList(String data) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        List<Ticket> tickets = Lists.newArrayList();
        if (!data.isEmpty()) {
            tickets = mapper.readValue(data,
                    TypeFactory.defaultInstance()
                    .constructCollectionType(
                            List.class,
                            Ticket.class));
        }
        return tickets;
    }
}
