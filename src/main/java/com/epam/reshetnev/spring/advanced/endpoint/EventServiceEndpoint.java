package com.epam.reshetnev.spring.advanced.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.epam.reshetnev.spring.advanced.jaxws.event.AssignAuditorium;
import com.epam.reshetnev.spring.advanced.jaxws.event.AssignAuditoriumResponse;
import com.epam.reshetnev.spring.advanced.jaxws.event.Delete;
import com.epam.reshetnev.spring.advanced.jaxws.event.DeleteResponse;
import com.epam.reshetnev.spring.advanced.jaxws.event.GetAll;
import com.epam.reshetnev.spring.advanced.jaxws.event.GetAllForDateRange;
import com.epam.reshetnev.spring.advanced.jaxws.event.GetAllForDateRangeResponse;
import com.epam.reshetnev.spring.advanced.jaxws.event.GetAllNextEvents;
import com.epam.reshetnev.spring.advanced.jaxws.event.GetAllNextEventsResponse;
import com.epam.reshetnev.spring.advanced.jaxws.event.GetAllResponse;
import com.epam.reshetnev.spring.advanced.jaxws.event.GetBookedTickets;
import com.epam.reshetnev.spring.advanced.jaxws.event.GetBookedTicketsResponse;
import com.epam.reshetnev.spring.advanced.jaxws.event.GetById;
import com.epam.reshetnev.spring.advanced.jaxws.event.GetByIdResponse;
import com.epam.reshetnev.spring.advanced.jaxws.event.GetByName;
import com.epam.reshetnev.spring.advanced.jaxws.event.GetByNameResponse;
import com.epam.reshetnev.spring.advanced.jaxws.event.Save;
import com.epam.reshetnev.spring.advanced.jaxws.event.SaveAll;
import com.epam.reshetnev.spring.advanced.jaxws.event.SaveAllResponse;
import com.epam.reshetnev.spring.advanced.jaxws.event.SaveResponse;
import com.epam.reshetnev.spring.advanced.jaxws.event.Update;
import com.epam.reshetnev.spring.advanced.jaxws.event.UpdateResponse;
import com.epam.reshetnev.spring.core.service.EventService;

@Endpoint
public class EventServiceEndpoint {

    private static final String NAMESPACE_URI = "http://event.impl.service.core.spring.reshetnev.epam.com/";

    @Autowired
    private EventService eventService;

    @PayloadRoot(localPart = "save", namespace = NAMESPACE_URI)
    @ResponsePayload
    public SaveResponse saveEvent(@RequestPayload Save save) {
        SaveResponse saveResponse = new SaveResponse();
        eventService.save(save.getArg0());
        return saveResponse;
    }

    @PayloadRoot(localPart = "delete", namespace = NAMESPACE_URI)
    @ResponsePayload
    public DeleteResponse deleteEvent(@RequestPayload Delete delete) {
        DeleteResponse deleteResponse = new DeleteResponse();
        eventService.delete(delete.getArg0());
        return deleteResponse;
    }

    @PayloadRoot(localPart = "getById", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetByIdResponse getEventById(@RequestPayload GetById getById) {
        GetByIdResponse getByIdResponse = new GetByIdResponse();
        getByIdResponse.setReturn(eventService.getById(getById.getArg0()));
        return getByIdResponse;
    }

    @PayloadRoot(localPart = "getByName", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetByNameResponse getByName(@RequestPayload GetByName getByName) {
        GetByNameResponse getByNameResponse = new GetByNameResponse();
        getByNameResponse.setReturn(eventService.getByName(getByName.getArg0()));
        return getByNameResponse ;
    }

    @PayloadRoot(localPart = "getAll", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetAllResponse getAllEvents(@RequestPayload GetAll getAll) {
        GetAllResponse getAllResponse = new GetAllResponse();
        getAllResponse.setReturn(eventService.getAll());
        return getAllResponse ;
    }

    @PayloadRoot(localPart = "getAllForDateRange", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetAllForDateRangeResponse getAllForDateRange(@RequestPayload GetAllForDateRange getAllForDateRange) {
        GetAllForDateRangeResponse getAllForDateRangeResponse = new GetAllForDateRangeResponse();
        getAllForDateRangeResponse
                .setReturn(eventService
                        .getAllForDateRange(getAllForDateRange.getArg0(), getAllForDateRange.getArg1()));
        return getAllForDateRangeResponse;
    }

    @PayloadRoot(localPart = "getAllNextEvents", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetAllNextEventsResponse getAllNextEvents(@RequestPayload GetAllNextEvents getAllNextEvents) {
        GetAllNextEventsResponse getAllNextEventsResponse = new GetAllNextEventsResponse();
        getAllNextEventsResponse.setReturn(eventService.getAllNextEvents(getAllNextEvents.getArg0()));
        return getAllNextEventsResponse ;
    }

    @PayloadRoot(localPart = "assignAuditorium", namespace = NAMESPACE_URI)
    @ResponsePayload
    public AssignAuditoriumResponse assignAuditorium(@RequestPayload AssignAuditorium assignAuditorium) {
        AssignAuditoriumResponse assignAuditoriumResponse = new AssignAuditoriumResponse();
        eventService.assignAuditorium(assignAuditorium.getArg0(), assignAuditorium.getArg1(),
                assignAuditorium.getArg2(), assignAuditorium.getArg3());
        return assignAuditoriumResponse;
    }

    @PayloadRoot(localPart = "update", namespace = NAMESPACE_URI)
    @ResponsePayload
    public UpdateResponse updateEvent(@RequestPayload Update update) {
        UpdateResponse updateResponse = new UpdateResponse();
        eventService.update(update.getArg0());
        return updateResponse;
    }

    @PayloadRoot(localPart = "saveAll", namespace = NAMESPACE_URI)
    @ResponsePayload
    public SaveAllResponse saveAllEvents(@RequestPayload SaveAll saveAll) {
        SaveAllResponse saveAllResponse = new SaveAllResponse();
        eventService.saveAll(saveAll.getArg0());
        return saveAllResponse;
    }

    @PayloadRoot(localPart = "getBookedTickets", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetBookedTicketsResponse getBookedTicketEvent(@RequestPayload GetBookedTickets getBookedTickets) {
        GetBookedTicketsResponse getBookedTicketsResponse =  new GetBookedTicketsResponse();
        getBookedTicketsResponse.setReturn(eventService.getBookedTickets(getBookedTickets.getArg0()));
        return getBookedTicketsResponse ;
    }
}
