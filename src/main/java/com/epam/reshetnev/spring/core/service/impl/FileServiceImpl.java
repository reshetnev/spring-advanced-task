package com.epam.reshetnev.spring.core.service.impl;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.epam.reshetnev.spring.advanced.parser.AccountListParser;
import com.epam.reshetnev.spring.advanced.parser.Converter;
import com.epam.reshetnev.spring.advanced.parser.EventListParser;
import com.epam.reshetnev.spring.advanced.parser.UserListParser;
import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.domain.UserAccount;
import com.epam.reshetnev.spring.core.service.EventService;
import com.epam.reshetnev.spring.core.service.FileService;
import com.epam.reshetnev.spring.core.service.UserAccountService;
import com.epam.reshetnev.spring.core.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.google.common.collect.Lists;

@Service
public class FileServiceImpl implements FileService {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserAccountService userAccountService;

    @Override
    public List<String> parseFiles(Map<String, MultipartFile> fileMap)
            throws IOException, JsonParseException, JsonMappingException {

        List<String> dataList = Lists.newArrayList();
        for (String key: fileMap.keySet()) {

            if (key.equals("users")) {
                String data = Converter.read(fileMap.get(key).getInputStream());
                List<User> users = UserListParser.getUserList(data);
                userService.saveAll(users);
                dataList.add(data);
            }

            if (key.equals("events")) {
                String data = Converter.read(fileMap.get(key).getInputStream());
                List<Event> events = EventListParser.getEventList(data);
                eventService.saveAll(events);
                dataList.add(data);
            }

            if (key.equals("accounts")) {
                String data = Converter.read(fileMap.get(key).getInputStream());
                List<UserAccount> accounts = AccountListParser.getAccountList(data);
                userAccountService.saveAll(accounts);
                dataList.add(data);
            }
        }
        return dataList;
    }

}
