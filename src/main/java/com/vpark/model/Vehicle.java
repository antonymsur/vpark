package com.vpark.model;

import com.vpark.exceptions.NoTicketFoundException;
import com.vpark.model.ValueTypes.SlotType;
import com.vpark.model.ValueTypes.VehicleType;

public abstract class Vehicle {

    private Ticket entryTicket;

    public abstract VehicleType getType();

    public abstract SlotType getSlotType();

    public Ticket getTicket() throws NoTicketFoundException {
        return entryTicket;
    }

    public void assignTicket(Ticket tkt) {
        entryTicket = tkt;
    }
}
