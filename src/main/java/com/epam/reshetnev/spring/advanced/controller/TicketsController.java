package com.epam.reshetnev.spring.advanced.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.EventService;
import com.epam.reshetnev.spring.core.service.UserService;

@Controller
public class TicketsController {

    @Autowired
    private UserService userService;

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/tickets/{userId}", method = RequestMethod.GET)
    public ModelAndView getBookedTickets(@PathVariable String userId) {
        ModelAndView model = new ModelAndView();
        User user = userService.getById(Integer.parseInt(userId));
        List<Ticket> tickets = userService.getBookedTickets(user);
        model.addObject("tickets", tickets);
        model.setViewName("getBookedTickets");
        return model;
    }

    @RequestMapping(value = "/tickets/{userId}", method = RequestMethod.GET, headers="accept=application/pdf")
    public ModelAndView getBookedTicketsPdf(@PathVariable String userId) {
        ModelAndView model = new ModelAndView();
        User user = userService.getById(Integer.parseInt(userId));
        List<Ticket> tickets = userService.getBookedTickets(user);
        model.addObject("tickets", tickets);
        model.setViewName("getBookedTickets");
        return model;
    }
}
