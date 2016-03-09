package com.epam.reshetnev.spring.advanced.parser;

import java.io.IOException;

import com.epam.reshetnev.spring.core.domain.Ticket;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class TicketParser {

    public static Ticket getTicket(String data) throws JsonParseException, JsonMappingException, IOException {
        ObjectMapper mapper = new ObjectMapper();
        mapper.registerModule(new JavaTimeModule());
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        Ticket ticket = mapper.readValue(data, Ticket.class);
        return ticket;
    }
}
