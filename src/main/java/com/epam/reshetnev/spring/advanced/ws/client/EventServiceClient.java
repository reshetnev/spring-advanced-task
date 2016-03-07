package com.epam.reshetnev.spring.advanced.ws.client;

import java.time.LocalDate;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.epam.reshetnev.spring.advanced.ws.event.AssignAuditorium;
import com.epam.reshetnev.spring.advanced.ws.event.Delete;
import com.epam.reshetnev.spring.advanced.ws.event.GetAll;
import com.epam.reshetnev.spring.advanced.ws.event.GetAllForDateRange;
import com.epam.reshetnev.spring.advanced.ws.event.GetAllForDateRangeResponse;
import com.epam.reshetnev.spring.advanced.ws.event.GetAllNextEvents;
import com.epam.reshetnev.spring.advanced.ws.event.GetAllNextEventsResponse;
import com.epam.reshetnev.spring.advanced.ws.event.GetAllResponse;
import com.epam.reshetnev.spring.advanced.ws.event.GetBookedTickets;
import com.epam.reshetnev.spring.advanced.ws.event.GetBookedTicketsResponse;
import com.epam.reshetnev.spring.advanced.ws.event.GetById;
import com.epam.reshetnev.spring.advanced.ws.event.GetByIdResponse;
import com.epam.reshetnev.spring.advanced.ws.event.GetByName;
import com.epam.reshetnev.spring.advanced.ws.event.GetByNameResponse;
import com.epam.reshetnev.spring.advanced.ws.event.Save;
import com.epam.reshetnev.spring.advanced.ws.event.SaveAll;
import com.epam.reshetnev.spring.advanced.ws.event.Update;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.Event;

@Component
public class EventServiceClient {

    private static final Logger log = Logger.getLogger(EventServiceClient.class);

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    public void save(Event event) {
        Save save = new Save();
        save.setArg0(event);
        webServiceTemplate.marshalSendAndReceive(save);
    }

    public void delete(Event event) {
        Delete delete = new Delete();
        delete.setArg0(event);
        webServiceTemplate.marshalSendAndReceive(delete);
    }

    public Event getById(Integer id) {
        GetById getById = new GetById();
        getById.setArg0(id);
        GetByIdResponse getByIdResponse = (GetByIdResponse) webServiceTemplate.marshalSendAndReceive(getById);
        Event event = getByIdResponse.getReturn();
        log.info("SOAP result: " + event.toString());
        return event;
    }

    public Event getByName() {
        GetByName getByName = new GetByName();
        GetByNameResponse getByNameResponse = (GetByNameResponse) webServiceTemplate.marshalSendAndReceive(getByName);
        Event event = getByNameResponse.getReturn();
        return event;
    }

    public List<Event> getAll() {
        GetAll getAll = new GetAll();
        GetAllResponse getAllResponse = (GetAllResponse) webServiceTemplate.marshalSendAndReceive(getAll);
        List<Event> events = getAllResponse.getReturn();
        return events;
    }

    public List<Event> getAllForDateRange(LocalDate from, LocalDate to) {
        GetAllForDateRange getAllForDateRange = new GetAllForDateRange();
        getAllForDateRange.setArg0(from);
        getAllForDateRange.setArg1(to);
        GetAllForDateRangeResponse getAllForDateRangeResponse = (GetAllForDateRangeResponse) webServiceTemplate
                .marshalSendAndReceive(getAllForDateRange);
        List<Event> events = getAllForDateRangeResponse.getReturn();
        return events;
    }

    public List<Event> getAllNextEvents(LocalDate to) {
        GetAllNextEvents getAllNextEvents = new GetAllNextEvents();
        getAllNextEvents.setArg0(to);
        GetAllNextEventsResponse getAllNextEventsResponse = (GetAllNextEventsResponse) webServiceTemplate
                .marshalSendAndReceive(getAllNextEvents);
        List<Event> events = getAllNextEventsResponse.getReturn();
        return events;
    }

    public void assignAuditorium(Event event, String auditorium, String date, String time) {
        AssignAuditorium assignAuditorium = new AssignAuditorium();
        assignAuditorium.setArg0(event);
        assignAuditorium.setArg1(auditorium);
        assignAuditorium.setArg2(date);
        assignAuditorium.setArg3(time);
        webServiceTemplate.marshalSendAndReceive(assignAuditorium);
    }

    public void update(Event event) {
        Update update = new Update();
        update.setArg0(event);
        webServiceTemplate.marshalSendAndReceive(update);
    }

    public void saveAll(List<Event> events) {
        SaveAll saveAll = new SaveAll();
        saveAll.setArg0(events);
        webServiceTemplate.marshalSendAndReceive(saveAll);
    }

    public List<Ticket> getBookedTickets(Event event) {
        GetBookedTickets getBookedTickets = new GetBookedTickets();
        getBookedTickets.setArg0(event);
        GetBookedTicketsResponse getBookedTicketsResponse = (GetBookedTicketsResponse) webServiceTemplate
                .marshalSendAndReceive(getBookedTickets);
        List<Ticket> tickets = getBookedTicketsResponse.getReturn();
        return tickets;
    }
}
