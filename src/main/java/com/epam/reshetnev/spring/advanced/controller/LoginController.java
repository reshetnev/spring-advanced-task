package com.epam.reshetnev.spring.advanced.controller;

import java.io.IOException;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.SchemaOutputResolver;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.epam.reshetnev.spring.advanced.parser.MySchemaOutputResolver;
import com.epam.reshetnev.spring.core.domain.Event;
import com.epam.reshetnev.spring.core.domain.User;

@Controller
public class LoginController {

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView handleLogin(
        @RequestParam(value = "error", required = false) String error,
        @RequestParam(value = "logout", required = false) String logout) {

        ModelAndView model = new ModelAndView();
        if (error != null) {
            model.addObject("error", "Invalid username or password!");
        }

        if (logout != null) {
            model.addObject("message", "You have been logged out successfully.");
        }
        model.setViewName("login");

        return model;
    }

    @RequestMapping(value = "/forbidden")
    public String handleForbiddenPage() {
        return "forbidden";
    }

    @RequestMapping(value = "/xsd", method = RequestMethod.GET)
    public String xsd() throws IOException, JAXBException {

        Class[] classes = new Class[2];
        classes[0] = User.class;
        classes[1] = Event.class;
        JAXBContext jaxbContext;
        try {
            jaxbContext = JAXBContext.newInstance(classes);
            SchemaOutputResolver sor = new MySchemaOutputResolver();
            jaxbContext.generateSchema(sor);
        } catch (JAXBException e) {
            throw new JAXBException(e.getMessage());
        } catch (IOException e) {
            throw new IOException(e.getMessage());
        }

        return "redirect:/login";
    }
}
