package com.epam.reshetnev.spring.advanced.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.TicketService;
import com.epam.reshetnev.spring.core.service.UserService;

@RestController
public class UsersRestController {

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @ResponseBody
    @RequestMapping(value = "/users", method = RequestMethod.GET, headers="accept=application/json")
    public List<User> getAll() {
        return userService.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/users/{userId}", method = RequestMethod.GET, headers="accept=application/json")
    public User getById(@PathVariable String userId) {
        return userService.getById(Integer.parseInt(userId));
    }

    @ResponseBody
    @RequestMapping(value = "/users/{userId}/tickets", method = RequestMethod.GET, headers="accept=application/json")
    public List<Ticket> getBookedTickets(@PathVariable String userId) {
        User user = userService.getById(Integer.parseInt(userId));
        return userService.getBookedTickets(user);
    }

    @ResponseBody
    @RequestMapping(value = "/tickets", method = RequestMethod.GET, headers="accept=application/json")
    public List<Ticket> getAllTickets() {
        return ticketService.getAll();
    }

    @ResponseBody
    @RequestMapping(value = "/tickets", method = RequestMethod.GET, headers="accept=application/pdf")
    public ResponseEntity<List<Ticket>> getAllTicketsPdf() {
        return new ResponseEntity<List<Ticket>>(ticketService.getAll(), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/tickets/{ticketId}", method = RequestMethod.GET, headers="accept=application/json")
    public Ticket getTicketById(@PathVariable String ticketId) {
        return ticketService.getById(Integer.parseInt(ticketId));
    }

    @ResponseBody
    @RequestMapping(value = "/tickets/{ticketId}", method = RequestMethod.GET, headers="accept=application/pdf")
    public Ticket getTicketByIdPdf(@PathVariable String ticketId) {
        Ticket ticket = ticketService.getById(Integer.parseInt(ticketId));
        return ticket;
    }
}
