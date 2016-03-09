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
import com.epam.reshetnev.spring.core.service.EventService;

@Controller
public class EventsController {

    @Autowired
    private EventService eventService;

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public ModelAndView getAllEvents() {
        ModelAndView model = new ModelAndView();
        List<Event> events = eventService.getAll();
        model.addObject("events", events);
        model.setViewName("getAllEvents");
        return model;
    }

    @RequestMapping(value = "/events/{eventId}", method = RequestMethod.GET)
    public ModelAndView getEventById(@PathVariable String eventId) {
        ModelAndView model = new ModelAndView();
        Event event = eventService.getById(Integer.parseInt(eventId));
        model.addObject("event", event);
        model.setViewName("getEventById");
        return model;
    }

    @RequestMapping(value = "/events/event/{name}", method = RequestMethod.GET)
    public ModelAndView getEventByName(@PathVariable String name) {
        ModelAndView model = new ModelAndView();
        Event event = eventService.getByName(name);
        model.addObject("event", event);
        model.setViewName("getEventByName");
        return model;
    }

    @RequestMapping(value = "/events/{eventId}/tickets", method = RequestMethod.GET)
    public ModelAndView getBookedTickets(@PathVariable String eventId) {
        ModelAndView model = new ModelAndView();
        Event event = eventService.getById(Integer.parseInt(eventId));
        List<Ticket> tickets = eventService.getBookedTickets(event);
        model.addObject("tickets", tickets);
        model.setViewName("getBookedTickets");
        return model;
    }

    @RequestMapping(value = "/events/{eventId}/tickets", method = RequestMethod.GET, headers="accept=application/pdf")
    public ModelAndView getBookedTicketsPdf(@PathVariable String eventId) {
        ModelAndView model = new ModelAndView();
        Event event = eventService.getById(Integer.parseInt(eventId));
        List<Ticket> tickets = eventService.getBookedTickets(event);
        model.addObject("tickets", tickets);
        model.setViewName("getBookedTickets");
        return model;
    }
}
