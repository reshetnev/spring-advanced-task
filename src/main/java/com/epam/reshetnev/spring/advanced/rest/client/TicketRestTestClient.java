package com.epam.reshetnev.spring.advanced.rest.client;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import com.epam.reshetnev.spring.advanced.rest.client.interceptor.AcceptHeaderHttpRequestInterceptor;
import com.epam.reshetnev.spring.core.domain.Ticket;
import com.google.common.collect.Lists;
import com.lowagie.text.pdf.PdfReader;
import com.lowagie.text.pdf.parser.PdfTextExtractor;

@Component
public class TicketRestTestClient {

    @Autowired
    private RestTemplate restTemplate;

    private static final Logger log = Logger.getLogger(TicketRestTestClient.class);

    private static final String URI = "http://localhost:8080/cinema";

    public void getAllTickets() {
        log.info("Testing getAllTickets API");

        @SuppressWarnings("unchecked")
        List<LinkedHashMap<String, Object>> tickets = restTemplate.getForObject(URI+"/tickets", List.class);

        if(tickets!=null) {
            for (LinkedHashMap<String, Object> map : tickets) {
                log.info("Ticket : id=" + map.get("id") + ", name=" + map.get("name") + ", email=" + map.get("email")
                        + ", birthDay=" + map.get("birthDay") + ", roles=" + map.get("roles"));
            }
        }else{
            log.info("No ticket exist");
        }
    }

    public Ticket getTicket(String id) {
        log.info("Testing getTicket API");
        Ticket ticket = restTemplate.getForObject(URI+"/tickets/" +id, Ticket.class);
        log.info(ticket.toString());
        return ticket;
    }

    public String getTicketPdf(String id) throws IOException {
        ClientHttpRequestInterceptor acceptHeaderPdf = new AcceptHeaderHttpRequestInterceptor("application/pdf");
        restTemplate.setInterceptors(Lists.newArrayList(acceptHeaderPdf));
        byte[] response = restTemplate.getForObject(URI+"/tickets/" +id, byte[].class);
        PdfTextExtractor pdfTextExtractor = new PdfTextExtractor(new PdfReader(response));
        String responseText = pdfTextExtractor.getTextFromPage(1);
        return responseText;
    }
}
