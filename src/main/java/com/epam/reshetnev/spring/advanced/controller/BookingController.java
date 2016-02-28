package com.epam.reshetnev.spring.advanced.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.domain.form.BookForm;
import com.epam.reshetnev.spring.core.service.BookingService;
import com.epam.reshetnev.spring.core.service.EventService;
import com.epam.reshetnev.spring.core.service.TicketService;
import com.epam.reshetnev.spring.core.service.UserAccountService;
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

    @Autowired
    private UserAccountService userAccountService;

    @RequestMapping(value = "/tickets/book", method = RequestMethod.GET)
    public ModelAndView bookTicket() {
        ModelAndView model = new ModelAndView();
        BookForm bookForm = new BookForm();
        model.addObject("bookForm", bookForm);
        model.setViewName("book");
        return model;
    }

    @RequestMapping(value = "/tickets/book", method = RequestMethod.POST)
    public String bookTicketProcessing(HttpServletRequest request, BookForm bookForm) {
        String email = request.getUserPrincipal().getName();
        User user = userService.getByEmail(email);
        Event event = eventService.getByName(bookForm.getName());
        Integer seat = bookForm.getSeat();
        Ticket ticket = ticketService.getByEventAndSeat(event, seat);
        bookingService.bookTicket(user, ticket);
        userAccountService.bookTicket(user, ticket);
        return "redirect:/";
    }

    @RequestMapping(value = "/tickets/events/{eventId}", method = RequestMethod.GET)
    public ModelAndView getAllTickets(@PathVariable String eventId) {
        ModelAndView model = new ModelAndView();
        Event event = eventService.getById(Integer.parseInt(eventId));
        List<Ticket> tickets = bookingService.getTicketsForEvent(event);
        model.addObject("tickets", tickets);
        model.setViewName("getAllTickets");
        return model;
    }

    @RequestMapping(value = "/tickets/events/{eventId}", method = RequestMethod.GET,
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
