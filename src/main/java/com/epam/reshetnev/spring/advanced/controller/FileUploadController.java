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

import com.epam.reshetnev.spring.advanced.parser.UserListParser;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.UserService;

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
                List<User> users = UserListParser.getUserList(data);
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
