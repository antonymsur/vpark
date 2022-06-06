/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vpark.actions;

import com.vpark.exceptions.NoTicketFoundException;
import com.vpark.exceptions.NoVehicleFoundException;
import com.vpark.model.ParkingLot;
import com.vpark.model.Receipt;
import com.vpark.model.ValueTypes.SlotType;
import com.vpark.model.Vehicle;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author antony
 */
public class LeaveOperation implements ParkingOperation {

    ParkingLot pLot;
    String ticketId;
    SlotType sType;
    private static Logger logger = LoggerFactory.getLogger(LeaveOperation.class);

    public LeaveOperation(ParkingLot pLot, String ticketId, SlotType sType) {
        this.pLot = pLot;
        this.ticketId = ticketId;
        this.sType = sType;
    }

    @Override
    public void execute() {
        Calendar date = Calendar.getInstance();
        long timeInSecs = date.getTimeInMillis();
        String hash = this.pLot.getHash(this.ticketId, this.sType, this.pLot.getType());
        Vehicle v;
        try {
            v = this.pLot.getParkedVehicle(hash);
            //Date afterAdding1500Mins = new Date(timeInSecs + (1500 * 60 * 1000));
            Receipt rcpt;
            try {
                rcpt = pLot.leave(v, new Date(timeInSecs));
                logger.info(rcpt.toJSON());
            } catch (NoTicketFoundException ex) {
                logger.error("NoTicketFoundException !!!!");
            }
        } catch (NoVehicleFoundException ex) {
            logger.error("NoTicketFoundException !!!!");
        }

    }

}
