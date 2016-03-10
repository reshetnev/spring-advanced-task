package com.epam.reshetnev.spring.advanced.rest.client;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.epam.reshetnev.spring.core.domain.User;

@Component
public class UserRestTestClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger log = Logger.getLogger(UserRestTestClient.class);

    private static final String URI = "http://localhost:8080/cinema";

    public void getAllUsers() {
        log.info("Testing getAllUsers API");

        @SuppressWarnings("unchecked")
        List<LinkedHashMap<String, Object>> users = restTemplate.getForObject(URI+"/users", List.class);

        if(users!=null){
            for (LinkedHashMap<String, Object> map : users) {
                log.info("User : id=" + map.get("id") + ", name=" + map.get("name") + ", email=" + map.get("email")
                        + ", birthDay=" + map.get("birthDay") + ", roles=" + map.get("roles"));
            }
        }else{
            log.info("No user exist");
        }
    }

    public User getUser(String id) {
        log.info("Testing getUser API");
        User user = restTemplate.getForObject(URI+"/users/" +id, User.class);
        log.info(user.toString());
        return user;
    }

    public void createUser(User user) {
        log.info("Testing create User API");
        URI uri = restTemplate.postForLocation(URI+"/users", user, User.class);
        log.info("Location : "+uri.toASCIIString());
    }

    public void updateUser(User user) {
        log.info("Testing update User API");
        restTemplate.put(URI+"/users/" + user.getId(), user);
        log.info(user.toString());
    }

    public void deleteUser(User user) {
        log.info("Testing delete User API");
        restTemplate.delete(URI+"/users/" + user.getId());
    }

}
