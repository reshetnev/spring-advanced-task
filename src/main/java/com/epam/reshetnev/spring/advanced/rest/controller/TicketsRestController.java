package com.epam.reshetnev.spring.advanced.rest.controller;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.BookingService;
import com.epam.reshetnev.spring.core.service.TicketService;
import com.epam.reshetnev.spring.core.service.UserService;
import com.google.common.collect.Lists;

@RestController
public class TicketsRestController {

    private static final Logger log = Logger.getLogger(TicketsRestController.class);

    @Autowired
    private TicketService ticketService;

    @Autowired
    private UserService userService;

    @Autowired
    private BookingService bookingService;

    @ResponseBody
    @RequestMapping(value = "/tickets/book/{ticketId}", method = RequestMethod.PUT)
    public ResponseEntity<Ticket> bookTicket(HttpServletRequest request, @PathVariable("ticketId") String ticketId,
            @RequestBody Ticket ticket) throws Exception {

        log.info("Booking Ticket ... ");

        Principal principal = request.getUserPrincipal();
        User user = null;
        if (principal != null) {
            String email = principal.getName();
            user = userService.getByEmail(email);
        }

        Integer id = Integer.valueOf(ticketId);
        Ticket currentTicket  = ticketService.getById(id);

        if (currentTicket == null) {
            return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
        }

        if (!ticket.getId().equals(id)) {
            log.info("ticketId: " + ticketId + " must be equal id: " + ticket.getId() + " of ticket in request body");
            return new ResponseEntity<Ticket>(HttpStatus.CONFLICT);
        }

        bookingService.bookTicket(user, ticket);

        return new ResponseEntity<Ticket>(ticket , HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/tickets", method = RequestMethod.GET, headers="accept=application/json")
    public ResponseEntity<List<Ticket>> getAllTickets() {
        List<Ticket> tickets = ticketService.getAll();
        if(tickets.isEmpty()){
            return new ResponseEntity<List<Ticket>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/tickets", method = RequestMethod.GET, headers="accept=application/pdf")
    public ResponseEntity<List<Ticket>> getAllTicketsPdf() {
        log.info("Getting All Tickets ...");
        List<Ticket> tickets = ticketService.getAll();
        if(tickets.isEmpty()){
            return new ResponseEntity<List<Ticket>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/tickets/{ticketId}", method = RequestMethod.GET, headers="accept=application/json")
    public ResponseEntity<Ticket> getTicketById(@PathVariable String ticketId) {
        Ticket ticket = ticketService.getById(Integer.parseInt(ticketId));
        if (ticket == null) {
            return new ResponseEntity<Ticket>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Ticket>(ticket, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/tickets/{ticketId}", method = RequestMethod.GET, headers="accept=application/pdf")
    public ResponseEntity<List<Ticket>> getTicketByIdPdf(@PathVariable String ticketId) {
        log.info("Getting Ticket with id: " + ticketId + " ...");
        Ticket ticket = ticketService.getById(Integer.parseInt(ticketId));
        if (ticket == null) {
            return new ResponseEntity<List<Ticket>>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<List<Ticket>>(Lists.newArrayList(ticket), HttpStatus.OK);
    }
}
