/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vpark.model;

import java.util.Date;
import org.json.simple.JSONObject;

/**
 *
 * @author antony
 */
public class Receipt {

    private String id;
    private Ticket ticket;
    private Date exitTime;
    private Double amount;

    public Receipt(Ticket ticket, Date exitTime, Double amount) {
        this.ticket = ticket;
        this.exitTime = exitTime;
        this.amount = amount;
        id = getHash(ticket.getId(), exitTime);
    }

    public String getId() {
        return id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    private String getHash(String id, Date exitTime) {
        return id + "-" + exitTime.getTime();
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("======================================\n");
        builder.append("\tParking Lot Type     : " + ticket.plType.name() + "\n");
        builder.append("\tParking Vehicle Type : " + ticket.slotType.name() + "\n");
        builder.append("\tParking Entry Time   : " + ticket.entryTime.toString() + "\n");
        builder.append("\tParking Exit Time    : " + exitTime.toString() + "\n");
        builder.append("\tParking Total Amount : " + amount + "\n");
        builder.append("======================================\n");
        return builder.toString();
    }

    public String toJSON() {
        JSONObject json = new JSONObject();
        json.put("parking", ticket.plType.name());
        json.put("vehicle", ticket.slotType.name());
        json.put("entry", ticket.entryTime.toString());
        json.put("exit", exitTime.toString());
        json.put("amount", amount);
        return json.toJSONString();
    }
}
