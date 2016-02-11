package com.epam.reshetnev.spring.advanced.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.EventService;
import com.epam.reshetnev.spring.core.service.UserService;

@Controller
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView showHomePage(HttpServletRequest request) {
        ModelAndView model = new ModelAndView();
        List<User> users = userService.getAll();
        model.addObject("users", users);
        model.setViewName("home");
        return model;
    }

    @RequestMapping(value = "/getUserByEmail", method = RequestMethod.GET)
    public ModelAndView getUserByEmail(@RequestParam("email") String email) {
        ModelAndView model = new ModelAndView();
        User user = userService.getByEmail(email);
        model.addObject("user", user);
        model.setViewName("getUserByEmail");
        return model;
    }

    @RequestMapping(value = "/getUsersByName", method = RequestMethod.GET)
    public ModelAndView getUsersByName(@RequestParam("name") String name) {
        ModelAndView model = new ModelAndView();
        List<User> users = userService.getAllByName(name);
        model.addObject("users", users);
        model.setViewName("getUsersByName");
        return model;
    }

    @RequestMapping(value = "/getBookedTickets", method = RequestMethod.GET)
    public ModelAndView getBookedTickets(@RequestParam("email") String email) {
        ModelAndView model = new ModelAndView();
        User user = userService.getByEmail(email);
        List<Ticket> tickets = userService.getBookedTickets(user);
        model.addObject("tickets", tickets);
        model.setViewName("getBookedTickets");
        return model;
    }
}
