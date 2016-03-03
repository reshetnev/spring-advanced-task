package com.epam.reshetnev.spring.advanced.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import com.epam.reshetnev.spring.advanced.jaxws.user.GetById;
import com.epam.reshetnev.spring.core.domain.User;
import com.epam.reshetnev.spring.core.service.UserService;

@Endpoint
public class UserServiceEndpoint {

    private static final String NAMESPACE_URI = "http://impl.service.core.spring.reshetnev.epam.com/";

    @Autowired
    private UserService userService;

    @PayloadRoot(localPart = "getById", namespace = NAMESPACE_URI)
    @ResponsePayload
    public User getUser(@RequestPayload GetById getById) {
      return userService.getById(getById.getArg0());
    }
}
