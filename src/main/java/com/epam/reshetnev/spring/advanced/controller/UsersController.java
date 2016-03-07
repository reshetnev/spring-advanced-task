package com.epam.reshetnev.spring.advanced.controller;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.reshetnev.spring.advanced.ws.client.UserServiceClient;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.domain.UserAccount;
import com.epam.reshetnev.spring.core.service.UserAccountService;
import com.epam.reshetnev.spring.core.service.UserService;

@Controller
public class UsersController {

    private static final Logger log = Logger.getLogger(UsersController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserAccountService userAccountService;

    @Autowired
    private UserServiceClient userServiceClient;

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
        Integer id = Integer.parseInt(userId);
        User user = userService.getById(id);
        model.addObject("user", user);
        model.setViewName("getUserByEmail");

        log.info("Testing SOAP WS! Calling UserServiceClient.getById...");
        User result = userServiceClient.getById(id);
        log.info("Test SOAP WS has finished. WebServiceTemplate return result: " + result.toString());

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

    @RequestMapping(value = "/users/{userId}/tickets", method = RequestMethod.GET)
    public ModelAndView getBookedTickets(@PathVariable String userId) {
        ModelAndView model = new ModelAndView();
        User user = userService.getById(Integer.parseInt(userId));
        List<Ticket> tickets = userService.getBookedTickets(user);
        model.addObject("tickets", tickets);
        model.setViewName("getBookedTickets");
        return model;
    }

    @RequestMapping(value = "/users/{userId}/tickets", method = RequestMethod.GET,
            headers="accept=application/pdf")
    public ModelAndView getBookedTicketsPdf(@PathVariable String userId) {
        ModelAndView model = new ModelAndView();
        User user = userService.getById(Integer.parseInt(userId));
        List<Ticket> tickets = userService.getBookedTickets(user);
        model.addObject("tickets", tickets);
        model.setViewName("getBookedTickets");
        return model;
    }

    @RequestMapping(value = "/accounts", method = RequestMethod.GET)
    public ModelAndView getAllAccounts() {
        ModelAndView model = new ModelAndView();
        List<UserAccount> accounts = userAccountService.getAll();
        model.addObject("accounts", accounts);
        model.setViewName("accounts");
        return model;
    }
}
