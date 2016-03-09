package com.epam.reshetnev.spring.advanced.view.pdf;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.view.document.AbstractPdfView;

import com.epam.reshetnev.spring.core.domain.Ticket;
import com.lowagie.text.Document;
import com.lowagie.text.Element;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;

public class TicketPdfViewResolver extends AbstractPdfView {

    @Override
    protected void buildPdfDocument(Map<String, Object> model, Document document, PdfWriter arg2, HttpServletRequest arg3,
            HttpServletResponse arg4) throws Exception {

        Ticket ticket = (Ticket) model.get("ticket");

        @SuppressWarnings("unchecked")
        List<Ticket> tickets = (List<Ticket>) model.get("tickets");

        Table table = new Table(1);
        table.getDefaultCell().setHorizontalAlignment(Element.ALIGN_CENTER);
        table.getDefaultCell().setVerticalAlignment(Element.ALIGN_MIDDLE);

        if (ticket != null) {
            table.addCell("Ticket:");
            table.addCell(ticket.toString());
        } else if (tickets != null) {
            table.addCell("Tickets:");
            for (Ticket t: tickets) {
                table.addCell(t.toString());
            }
        }

        document.add(table);
    }

}
