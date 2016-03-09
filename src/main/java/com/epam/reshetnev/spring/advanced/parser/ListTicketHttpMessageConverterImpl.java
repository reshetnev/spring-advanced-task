package com.epam.reshetnev.spring.advanced.parser;

import java.io.IOException;
import java.util.List;

import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import com.epam.reshetnev.spring.core.domain.Ticket;
import com.google.common.collect.Lists;

public class ListTicketHttpMessageConverterImpl implements HttpMessageConverter<List<Ticket>> {

    @Override
    public boolean canRead(Class<?> clazz, MediaType mediaType) {
        if (getSupportedMediaTypes().contains(mediaType)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean canWrite(Class<?> clazz, MediaType mediaType) {
        if (getSupportedMediaTypes().contains(mediaType)) {
            return true;
        }
        return false;
    }

    @Override
    public List<MediaType> getSupportedMediaTypes() {
        return Lists.newArrayList(new MediaType("application","pdf"));
    }

    @Override
    public List<Ticket> read(Class<? extends List<Ticket>> clazz, HttpInputMessage inputMessage)
            throws IOException, HttpMessageNotReadableException {
        return TicketListParser.getTicketList(Converter.read(inputMessage.getBody()));
    }

    @Override
    public void write(List<Ticket> t, MediaType contentType, HttpOutputMessage outputMessage)
            throws IOException, HttpMessageNotWritableException {
        outputMessage.getBody().write(t.toString().getBytes());
    }



}
