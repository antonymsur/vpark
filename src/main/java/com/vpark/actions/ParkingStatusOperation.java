/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vpark.actions;

import com.vpark.exceptions.NoTicketFoundException;
import com.vpark.exceptions.NoVehicleFoundException;
import com.vpark.model.ParkingLot;
import com.vpark.model.ValueTypes.SlotType;
import com.vpark.model.Vehicle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author antony
 */
public class ParkingStatusOperation implements ParkingOperation {

    ParkingLot pLot;
    String ticketId;
    SlotType sType;
    private static Logger logger = LoggerFactory.getLogger(ParkingStatusOperation.class);

    public ParkingStatusOperation(ParkingLot pLot, String ticketId, SlotType sType) {
        this.pLot = pLot;
        this.ticketId = ticketId;
        this.sType = sType;
    }

    @Override
    public void execute() {
        String hash = this.pLot.getHash(this.ticketId, this.sType, this.pLot.getType());
        Vehicle v;
        try {
            v = this.pLot.getParkedVehicle(hash);
            if(v != null) {
                logger.info(v.getTicket().toJSON());
            }
        } catch (NoVehicleFoundException ex) {
            logger.error("NoVehicleFoundException !!!!");
        } catch (NoTicketFoundException ex) {
            logger.error("NoTicketFoundException !!!!");
        }

    }

}
