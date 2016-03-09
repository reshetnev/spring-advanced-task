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
import com.epam.reshetnev.spring.core.service.TicketService;
import com.google.common.collect.Lists;

@RestController
public class TicketsRestController {

    @Autowired
    private TicketService ticketService;

    @ResponseBody
    @RequestMapping(value = "/tickets", method = RequestMethod.GET, headers="accept=application/json")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        return new ResponseEntity<List<Ticket>>(ticketService.getAll(), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/tickets", method = RequestMethod.GET, headers="accept=application/pdf")
    public ResponseEntity<List<Ticket>> getAllTicketsPdf() {
        return new ResponseEntity<List<Ticket>>(ticketService.getAll(), HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/tickets/{ticketId}", method = RequestMethod.GET, headers="accept=application/json")
    public ResponseEntity<Ticket> getTicketById(@PathVariable String ticketId) {
        Ticket ticket = ticketService.getById(Integer.parseInt(ticketId));
        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/tickets/{ticketId}", method = RequestMethod.GET, headers="accept=application/pdf")
    public ResponseEntity<List<Ticket>> getTicketByIdPdf(@PathVariable String ticketId) {
        Ticket ticket = ticketService.getById(Integer.parseInt(ticketId));
        return new ResponseEntity<List<Ticket>>(Lists.newArrayList(ticket), HttpStatus.OK);
    }
}
