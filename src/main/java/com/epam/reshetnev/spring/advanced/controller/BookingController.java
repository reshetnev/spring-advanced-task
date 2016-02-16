package com.epam.reshetnev.spring.advanced.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.BookingService;
import com.epam.reshetnev.spring.core.service.EventService;
import com.epam.reshetnev.spring.core.service.TicketService;
import com.epam.reshetnev.spring.core.service.UserService;

@Controller
public class BookingController {

    @Autowired
    private BookingService bookingService;

    @Autowired
    private EventService eventService;

    @Autowired
    private UserService userService;

    @Autowired
    private TicketService ticketService;

    @RequestMapping(value = "/book/users/{userId}/tickets/{ticketId}", method = RequestMethod.POST)
    public ModelAndView bookTicket(@PathVariable String ticketId, @PathVariable String userId) {
        ModelAndView model = new ModelAndView();
        User user = userService.getById(Integer.parseInt(userId));
        Ticket ticket = ticketService.getById(Integer.parseInt(ticketId));
        bookingService.bookTicket(user, ticket);
        model.setViewName("bookTicket");
        return model;
    }

    @RequestMapping(value = "/book/events/{eventId}/allTickets", method = RequestMethod.GET)
    public ModelAndView getAllTickets(@PathVariable String eventId) {
        ModelAndView model = new ModelAndView();
        Event event = eventService.getById(Integer.parseInt(eventId));
        List<Ticket> tickets = bookingService.getTicketsForEvent(event);
        model.addObject("tickets", tickets);
        model.setViewName("getAllTickets");
        return model;
    }

    @RequestMapping(value = "/book/events/{eventId}/allTickets", method = RequestMethod.GET,
            headers="accept=application/pdf")
    public ModelAndView getAllTicketsPdf(@PathVariable String eventId) {
        ModelAndView model = new ModelAndView();
        Event event = eventService.getById(Integer.parseInt(eventId));
        List<Ticket> tickets = bookingService.getTicketsForEvent(event);
        model.addObject("tickets", tickets);
        model.setViewName("getAllTickets");
        return model;
    }
}
