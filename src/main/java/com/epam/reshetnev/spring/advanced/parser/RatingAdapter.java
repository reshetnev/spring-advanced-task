package com.epam.reshetnev.spring.advanced.parser;

import javax.xml.bind.annotation.adapters.XmlAdapter;

import com.epam.reshetnev.spring.core.domain.enums.Rating;

public class RatingAdapter extends XmlAdapter<String, Rating> {

    @Override
    public Rating unmarshal(String v) throws Exception {
        return Rating.valueOf(v);
    }

    @Override
    public String marshal(Rating v) throws Exception {
        return v.name();
    }

}
