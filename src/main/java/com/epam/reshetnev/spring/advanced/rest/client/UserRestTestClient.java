package com.epam.reshetnev.spring.advanced.rest.client;

import java.net.URI;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.web.client.RestTemplate;

import com.epam.reshetnev.spring.core.domain.User;

public class UserRestTestClient {

    private static final Logger log = Logger.getLogger(UserRestTestClient.class);

    private static final String URI = "http://localhost:8080/cinema";

    @SuppressWarnings("unchecked")
    public void listAllUsers() {
        log.info("Testing listAllUsers API");

        RestTemplate restTemplate = new RestTemplate();
        List<LinkedHashMap<String, Object>> usersMap = restTemplate.getForObject(URI+"/users", List.class);

        if(usersMap!=null){
            for(LinkedHashMap<String, Object> map : usersMap){
                log.info("User : id="+map.get("id")+", Name="+map.get("name")+", Age="+map.get("age")+", Salary="+map.get("salary"));;
            }
        }else{
            log.info("No user exist");
        }
    }

    public void getUser(String id){
        log.info("Testing getUser API");
        RestTemplate restTemplate = new RestTemplate();
        User user = restTemplate.getForObject(URI+"/users/" +id, User.class);
        log.info(user);
    }

    public void createUser(User user) {
        log.info("Testing create User API");
        RestTemplate restTemplate = new RestTemplate();
        URI uri = restTemplate.postForLocation(URI+"/users", user, User.class);
        log.info("Location : "+uri.toASCIIString());
    }

    public void updateUser(User user) {
        log.info("Testing update User API");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.put(URI+"/users/" + user.getId(), user);
        log.info(user);
    }

    public void deleteUser(User user) {
        log.info("Testing delete User API");
        RestTemplate restTemplate = new RestTemplate();
        restTemplate.delete(URI+"/users/" + user.getId());
    }

}
