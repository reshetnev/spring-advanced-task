package com.epam.reshetnev.spring.advanced.controller;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Controller
public class FileUploadController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView handleFormUpload(@RequestParam("file") MultipartFile file) throws IOException {

        ModelAndView model = new ModelAndView();
        if (!file.isEmpty()) {

            byte[] bytes = file.getBytes();
            String data = new String(bytes);
            model.addObject("data", data);
            model.setViewName("upload");
            try {
                ObjectMapper mapper = new ObjectMapper();
                mapper.registerModule(new JavaTimeModule());
                mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
                List<User> users = mapper.readValue(data,
                        TypeFactory.defaultInstance()
                        .constructCollectionType(
                                List.class,
                                User.class));
                userService.saveAll(users);
            } catch(Exception e) {
                model.addObject("message", e.getMessage());
                model.setViewName("errorParse");
            } finally {
                return model;
            }
        }

        model.setViewName("errorUpload");
        return model;
    }
}
