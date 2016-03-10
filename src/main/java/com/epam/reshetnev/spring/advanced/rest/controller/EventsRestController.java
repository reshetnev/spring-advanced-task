package com.epam.reshetnev.spring.advanced.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.service.EventService;

@RestController
public class EventsRestController {

    @Autowired
    private EventService eventService;

    @ResponseBody
    @RequestMapping(value = "/events", method = RequestMethod.GET, headers="accept=application/json")
    public ResponseEntity<List<Event>> getAll() {
        List<Event> events = eventService.getAll();
        if(events.isEmpty()){
            return new ResponseEntity<List<Event>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Event>>(events, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/events/{eventId}", method = RequestMethod.GET, headers="accept=application/json")
    public ResponseEntity<Event> getById(@PathVariable String eventId) {
        Event event = eventService.getById(Integer.parseInt(eventId));
        if (event == null) {
            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Event>(event, HttpStatus.OK);
    }

    @ResponseBody
    @RequestMapping(value = "/events/{eventId}/tickets", method = RequestMethod.GET, headers="accept=application/json")
    public ResponseEntity<List<Ticket>> getBookedTickets(@PathVariable String eventId) {
        Event event = eventService.getById(Integer.parseInt(eventId));
        List<Ticket> tickets = eventService.getBookedTickets(event);
        if(tickets.isEmpty()){
            return new ResponseEntity<List<Ticket>>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<List<Ticket>>(tickets, HttpStatus.OK);
    }

    @RequestMapping(value = "/events", method = RequestMethod.POST)
    public ResponseEntity<Void> createEvent(@RequestBody Event event, UriComponentsBuilder ucBuilder) {

        if (eventService.getById(event.getId()) != null) {
            return new ResponseEntity<Void>(HttpStatus.CONFLICT);
        }

        eventService.save(event);

        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/events/{id}").buildAndExpand(event.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Event> updateEvent(@PathVariable("id") String id, @RequestBody Event event) {

        Event currentEvent = eventService.getById(Integer.valueOf(id));

        if (currentEvent==null) {
            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
        }

        currentEvent.setName(event.getName());
        currentEvent.setDate(event.getDate());
        currentEvent.setTime(event.getTime());
        currentEvent.setBasePrice(event.getBasePrice());
        currentEvent.setRating(event.getRating());
        currentEvent.setAuditorium(event.getAuditorium());

        eventService.update(currentEvent);
        return new ResponseEntity<Event>(currentEvent, HttpStatus.OK);
    }

    @RequestMapping(value = "/events/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Event> deleteEvent(@PathVariable("id") String id) {

        Event event = eventService.getById(Integer.valueOf(id));
        if (event == null) {
            return new ResponseEntity<Event>(HttpStatus.NOT_FOUND);
        }

        eventService.delete(event);
        return new ResponseEntity<Event>(HttpStatus.NO_CONTENT);
    }
}
