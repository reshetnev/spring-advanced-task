package com.epam.reshetnev.spring.advanced.rest.client;

import java.io.FileOutputStream;
import java.io.IOException;

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

    public String getTicketPdf(String id) throws IOException {
        byte[] response = generateFilePdf(URI+"/tickets/" + id +".pdf", "C:\\ticket.pdf");
        String responseText = generateText(response, 1);
        return responseText;
    }

    public String getAllTicketsPdf() throws IOException {
        byte[] response = generateFilePdf(URI+"/tickets.pdf", "C:\\tickets.pdf");
        String responseText = generateText(response, 1);
        return responseText;
    }

    public void bookTicket(Ticket ticket) {
        log.info("Testing book ticket by unregistered User");
        restTemplate.put(URI+"/tickets/book/" + ticket.getId().toString(), ticket);
        log.info("Ticket has booked: " + ticket.toString());
    }

    private byte[] generateFilePdf(String url, String file) {
        ClientHttpRequestInterceptor acceptHeaderPdf = new AcceptHeaderHttpRequestInterceptor("application/pdf");
        restTemplate.setInterceptors(Lists.newArrayList(acceptHeaderPdf));
        byte[] response = restTemplate.getForObject(url, byte[].class);
        try {
            FileOutputStream fileOuputStream = new FileOutputStream(file);
            fileOuputStream.write(response);
            fileOuputStream.close();
            log.info("File PDF is created.");
        } catch (Exception e) {
            log.info("File creating error: " + e.getMessage());
        }
        return response;
    }

    private String generateText(byte[] response, int page) throws IOException {
        PdfTextExtractor pdfTextExtractor = new PdfTextExtractor(new PdfReader(response));
        String responseText = pdfTextExtractor.getTextFromPage(page) + "    [Page: " + page + "]";
        return responseText;
    }
}
