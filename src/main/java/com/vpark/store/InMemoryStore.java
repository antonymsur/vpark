/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vpark.store;

import com.vpark.Constants;
import com.vpark.exceptions.NoTicketFoundException;
import com.vpark.model.Receipt;
import com.vpark.model.Ticket;
import java.util.HashMap;

/**
 *
 * @author antony
 */
public class InMemoryStore extends Store {

    private HashMap<String, HashMap<String, Object>> ticketStore = new HashMap();

    @Override
    public void saveTicket(Ticket ticket) {
        HashMap map = ticketStore.get(ticket.getId());
        if (map == null) {
            map = new HashMap<String, Ticket>();
        }
        map.put(Constants.TICKET_NAME, ticket);
        ticketStore.put(ticket.getId(), map);
    }

    @Override
    public void saveReceipt(Receipt receipt) throws NoTicketFoundException {
        Ticket ticket = receipt.getTicket();
        HashMap map = ticketStore.get(ticket.getId());
        if (map == null) {
            throw new NoTicketFoundException();
        }
        map.put(Constants.RECEIPT_NAME, receipt);

    }

}
