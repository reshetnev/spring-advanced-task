package com.epam.reshetnev.spring.advanced.ws.client;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.ws.client.core.WebServiceTemplate;

import com.epam.reshetnev.spring.advanced.ws.user.Delete;
import com.epam.reshetnev.spring.advanced.ws.user.GetAll;
import com.epam.reshetnev.spring.advanced.ws.user.GetAllByName;
import com.epam.reshetnev.spring.advanced.ws.user.GetAllByNameResponse;
import com.epam.reshetnev.spring.advanced.ws.user.GetAllResponse;
import com.epam.reshetnev.spring.advanced.ws.user.GetBookedTickets;
import com.epam.reshetnev.spring.advanced.ws.user.GetBookedTicketsResponse;
import com.epam.reshetnev.spring.advanced.ws.user.GetByEmail;
import com.epam.reshetnev.spring.advanced.ws.user.GetByEmailResponse;
import com.epam.reshetnev.spring.advanced.ws.user.GetById;
import com.epam.reshetnev.spring.advanced.ws.user.GetByIdResponse;
import com.epam.reshetnev.spring.advanced.ws.user.Save;
import com.epam.reshetnev.spring.advanced.ws.user.SaveAll;
import com.epam.reshetnev.spring.advanced.ws.user.Update;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.epam.reshetnev.spring.core.domain.User;

@Component
public class UserServiceClient {

    private static final Logger log = Logger.getLogger(UserServiceClient.class);

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    public void save(User user) {
        Save save = new Save();
        save.setArg0(user);
        webServiceTemplate.marshalSendAndReceive(save);
    }

    public void delete(User user) {
        Delete delete = new Delete();
        delete.setArg0(user);
        webServiceTemplate.marshalSendAndReceive(delete);
    }

    public User getById(Integer id) {
        GetById getById = new GetById();
        getById.setArg0(id);
        GetByIdResponse getByIdResponse = (GetByIdResponse) webServiceTemplate.marshalSendAndReceive(getById);
        User user = getByIdResponse.getReturn();
        log.info("SOAP result: " + user.toString());
        return user;
    }

    public User getByEmail(String email) {
        GetByEmail getByEmail = new GetByEmail();
        getByEmail.setArg0(email);
        GetByEmailResponse getByEmailResponse = (GetByEmailResponse) webServiceTemplate.marshalSendAndReceive(getByEmail);
        User user = getByEmailResponse.getReturn();
        return user;
    }

    public List<User> getAllByName() {
        GetAllByName getAllByName = new GetAllByName();
        GetAllByNameResponse getAllByNameResponse = (GetAllByNameResponse) webServiceTemplate.marshalSendAndReceive(getAllByName);
        List<User> users = getAllByNameResponse.getReturn();
        return users;
    }

    public List<Ticket> getBookedTickets(User user) {
        GetBookedTickets getBookedTickets = new GetBookedTickets();
        getBookedTickets.setArg0(user);
        GetBookedTicketsResponse getBookedTicketsResponse = (GetBookedTicketsResponse) webServiceTemplate
                .marshalSendAndReceive(getBookedTickets);
        List<Ticket> tickets = getBookedTicketsResponse.getReturn();
        return tickets;
    }

    public List<User> getAll() {
        GetAll getAll = new GetAll();
        GetAllResponse getAllResponse = (GetAllResponse) webServiceTemplate.marshalSendAndReceive(getAll);
        List<User> users = getAllResponse.getReturn();
        return users;
    }

    public void update(User user) {
        Update update = new Update();
        update.setArg0(user);
        webServiceTemplate.marshalSendAndReceive(update);
    }

    public void saveAll(List<User> users) {
        SaveAll saveAll = new SaveAll();
        saveAll.setArg0(users);
        webServiceTemplate.marshalSendAndReceive(saveAll);
    }
}
