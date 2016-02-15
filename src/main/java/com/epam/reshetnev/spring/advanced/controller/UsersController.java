package com.epam.reshetnev.spring.advanced.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.EventService;
import com.epam.reshetnev.spring.core.service.UserService;

@Controller
public class UsersController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public ModelAndView getAllUsers() {
        ModelAndView model = new ModelAndView();
        List<User> users = userService.getAll();
        model.addObject("users", users);
        model.setViewName("getAllUsers");
        return model;
    }

    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET)
    public ModelAndView getUserById(@PathVariable String userId) {
        ModelAndView model = new ModelAndView();
        User user = userService.getById(Integer.parseInt(userId));
        model.addObject("user", user);
        model.setViewName("getUserByEmail");
        return model;
    }

    @RequestMapping(value = "/users/list/{name}", method = RequestMethod.GET)
    public ModelAndView getUsersByName(@PathVariable String name) {
        ModelAndView model = new ModelAndView();
        List<User> users = userService.getAllByName(name);
        model.addObject("users", users);
        model.setViewName("getUsersByName");
        return model;
    }

}
