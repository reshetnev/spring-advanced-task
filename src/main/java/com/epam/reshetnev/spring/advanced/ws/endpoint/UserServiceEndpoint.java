package com.epam.reshetnev.spring.advanced.ws.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.epam.reshetnev.spring.advanced.ws.user.Delete;
import com.epam.reshetnev.spring.advanced.ws.user.DeleteResponse;
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
import com.epam.reshetnev.spring.advanced.ws.user.SaveAllResponse;
import com.epam.reshetnev.spring.advanced.ws.user.SaveResponse;
import com.epam.reshetnev.spring.advanced.ws.user.Update;
import com.epam.reshetnev.spring.advanced.ws.user.UpdateResponse;
import com.epam.reshetnev.spring.core.service.UserService;

@Endpoint
public class UserServiceEndpoint {

    private static final String NAMESPACE_URI = "http://impl.service.core.spring.reshetnev.epam.com/";

    @Autowired
    private UserService userService;

    @PayloadRoot(localPart = "save", namespace = NAMESPACE_URI)
    @ResponsePayload
    public SaveResponse saveUser(@RequestPayload Save save) {
        SaveResponse saveResponse = new SaveResponse();
        userService.save(save.getArg0());
        return saveResponse;
    }

    @PayloadRoot(localPart = "delete", namespace = NAMESPACE_URI)
    @ResponsePayload
    public DeleteResponse deleteUser(@RequestPayload Delete delete) {
        DeleteResponse deleteResponse = new DeleteResponse();
        userService.delete(delete.getArg0());
        return deleteResponse;
    }

    @PayloadRoot(localPart = "getById", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetByIdResponse getUserById(@RequestPayload GetById getById) {
        GetByIdResponse getByIdResponse = new GetByIdResponse();
        getByIdResponse.setReturn(userService.getById(getById.getArg0()));
        return getByIdResponse;
    }

    @PayloadRoot(localPart = "getByEmail", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetByEmailResponse getByEmail(@RequestPayload GetByEmail getByEmail) {
        GetByEmailResponse getByEmailResponse = new GetByEmailResponse();
        getByEmailResponse.setReturn(userService.getByEmail(getByEmail.getArg0()));
        return getByEmailResponse;
    }

    @PayloadRoot(localPart = "getAllByName", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetAllByNameResponse getAllByName(@RequestPayload GetAllByName getAllByName) {
        GetAllByNameResponse getAllByNameResponse = new GetAllByNameResponse();
        getAllByNameResponse.setReturn(userService.getAllByName(getAllByName.getArg0()));
        return getAllByNameResponse;
    }

    @PayloadRoot(localPart = "getBookedTickets", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetBookedTicketsResponse getBookedTicketsUser(@RequestPayload GetBookedTickets getBookedTickets) {
        GetBookedTicketsResponse getBookedTicketsResponse = new GetBookedTicketsResponse();
        getBookedTicketsResponse.setReturn(userService.getBookedTickets(getBookedTickets.getArg0()));
        return getBookedTicketsResponse;
    }

    @PayloadRoot(localPart = "getAll", namespace = NAMESPACE_URI)
    @ResponsePayload
    public GetAllResponse getAllUsers(@RequestPayload GetAll getAll) {
        GetAllResponse getAllResponse = new GetAllResponse();
        getAllResponse.setReturn(userService.getAll());
        return getAllResponse;
    }

    @PayloadRoot(localPart = "update", namespace = NAMESPACE_URI)
    @ResponsePayload
    public UpdateResponse updateUser(@RequestPayload Update update) {
        UpdateResponse updateResponse = new UpdateResponse();
        userService.update(update.getArg0());
        return updateResponse;
    }

    @PayloadRoot(localPart = "saveAll", namespace = NAMESPACE_URI)
    @ResponsePayload
    public SaveAllResponse saveAllUsers(@RequestPayload SaveAll saveAll) {
        SaveAllResponse saveAllResponse = new SaveAllResponse();
        userService.saveAll(saveAll.getArg0());
        return saveAllResponse;
    }
}
