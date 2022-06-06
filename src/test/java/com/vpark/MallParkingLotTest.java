/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vpark;

import com.vpark.exceptions.InvalidSlotCountException;
import com.vpark.exceptions.NoSlotAvailableException;
import com.vpark.exceptions.NoTicketFoundException;
import com.vpark.model.Builder;
import com.vpark.model.MallParkingLot;
import com.vpark.model.Receipt;
import com.vpark.model.Ticket;
import com.vpark.model.ValueTypes;
import com.vpark.model.Vehicle;
import com.vpark.model.VehicleBike;
import com.vpark.model.VehicleHV;
import com.vpark.model.VehicleLV;
import com.vpark.store.InMemoryStore;
import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author antony
 */
public class MallParkingLotTest {

    /**
     * Test of park method, of class MallParkingLot.
     */
    private static Logger logger = LoggerFactory.getLogger(MallParkingLotTest.class);

    @Test
    public void testParkAndLeave() {
        logger.info("testParkAndLeave");
        Vehicle v1 = new VehicleBike();
        Vehicle v2 = new VehicleBike();
        
        MallParkingLot instance;
        try {
            // Create Mall ParkingLot with 100 Bikes/Scooter/MotorCycltes , 80 Light Vehicles and 10
            // Heavy Vehicles
            instance = (MallParkingLot) new Builder.ParkingLotBuilder(ValueTypes.ParkingLotType.Mall)
                    .addBikeSlots(100)
                    .addLVSlots(80)
                    .addHVSlots(10)
                    .addStore(new InMemoryStore())
                    .build();
            //Park V1 and V2
            try {
                Ticket tkt1 = instance.park(v1);
                logger.info(tkt1.toString());
            } catch (NoSlotAvailableException ex) {
                logger.info("NoSlotAvailableException");
            }
            try {
                Ticket tkt2 = instance.park(v2);
                logger.info(tkt2.toString());
            } catch (NoSlotAvailableException ex) {
                logger.info("NoSlotAvailableException");
            }
            // Leave V1 and V2
            try {
                Calendar date = Calendar.getInstance();
                long timeInSecs = date.getTimeInMillis();
                Date after30Mins = new Date(timeInSecs + (30 * 60 * 1000));
                Receipt rcpt1 = instance.leave(v2, after30Mins);
                logger.info(rcpt1.toString());
            } catch (NoTicketFoundException ex) {
                logger.info("No Ticket Found");;
            }
            {
                logger.info("No more space availabe");
            }
            //Motor Cycle Unparked after 3 hrs 30 mins
            try {
                Calendar date = Calendar.getInstance();
                long timeInSecs = date.getTimeInMillis();
                Date after3hr30Mins = new Date(timeInSecs + (210 * 60 * 1000));
                Receipt rcpt1 = instance.leave(v1, after3hr30Mins);
                logger.info(rcpt1.toString());
            } catch (NoTicketFoundException ex) {
                logger.info("No Ticket Found");;
            }
            // VehicleLV cars/SUV/etc 6hr 1min
            Vehicle v3 = new VehicleLV();
            //Park V3 for 6 Hrs 1 min
            try {
                Ticket tkt1 = instance.park(v3);
                logger.info(tkt1.toString());
            } catch (NoSlotAvailableException ex) {
                logger.info("NoSlotAvailableException");
            }
            //Unparked after 6 hrs 1 min
            try {
                Calendar date = Calendar.getInstance();
                long timeInSecs = date.getTimeInMillis();
                Date after6hr1Min = new Date(timeInSecs + (361 * 60 * 1000));
                Receipt rcpt1 = instance.leave(v3, after6hr1Min);
                logger.info(rcpt1.toString());
            } catch (NoTicketFoundException ex) {
                logger.info("No Ticket Found");;
            }
            
             // VehicleLV Truck Bus 1 hr 59min
            Vehicle v4 = new VehicleHV();
            //Park V3 for 6 Hrs 1 min
            try {
                Ticket tkt1 = instance.park(v4);
                logger.info(tkt1.toString());
            } catch (NoSlotAvailableException ex) {
                logger.info("NoSlotAvailableException");
            }
            //Unparked after 1 hrs 59 min
            try {
                Calendar date = Calendar.getInstance();
                long timeInSecs = date.getTimeInMillis();
                Date after1hr59Min = new Date(timeInSecs + (119 * 60 * 1000));
                Receipt rcpt1 = instance.leave(v4, after1hr59Min);
                logger.info(rcpt1.toString());
            } catch (NoTicketFoundException ex) {
                logger.info("No Ticket Found");;
            }
            
        } catch (InvalidSlotCountException ex) {
            logger.info("InvalidSlotCountException");

        }
    }
}
 