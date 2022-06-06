/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vpark.actions;

import com.vpark.exceptions.NoSlotAvailableException;
import com.vpark.model.ParkingLot;
import com.vpark.model.Ticket;
import com.vpark.model.ValueTypes.SlotType;
import com.vpark.model.Vehicle;
import com.vpark.model.VehicleBike;
import com.vpark.model.VehicleHV;
import com.vpark.model.VehicleLV;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author antony
 */
public class ParkOperation implements ParkingOperation {

    ParkingLot pLot;
    SlotType sType;
    private static Logger logger = LoggerFactory.getLogger(ParkOperation.class);

    public ParkOperation(ParkingLot pLot, SlotType sType) {
        this.pLot = pLot;
        this.sType = sType;
    }

    @Override
    public void execute() {
        Vehicle v = null;
        switch (sType) {
            case Bike:
                v = new VehicleBike();
                break;
            case LV:
                v = new VehicleLV();
                break;
            case HV:
                v = new VehicleHV();
        }
        try {
            if (v != null) {
                Ticket tkt = this.pLot.park(v);
                logger.info("Vehicle Parked" +  "Remaining Slots: " + this.pLot.getAvailableSlots(this.sType));
                logger.info("Ticket " +tkt.toJSON() );
            }

        } catch (NoSlotAvailableException ex) {
            logger.error("NoSlotAvailableException !!!");
        }

    }

}
