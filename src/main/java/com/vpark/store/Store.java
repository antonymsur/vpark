/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vpark.store;

import com.vpark.exceptions.NoTicketFoundException;
import com.vpark.model.Receipt;
import com.vpark.model.Ticket;

/**
 *
 * @author antony
 */
public abstract class Store {

    public abstract void saveTicket(Ticket ticket);

    public abstract void saveReceipt(Receipt receipt) throws NoTicketFoundException;
}
