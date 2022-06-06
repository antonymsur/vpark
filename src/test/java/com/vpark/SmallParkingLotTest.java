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
import com.vpark.store.InMemoryStore;
import java.util.Calendar;
import java.util.Date;
import java.util.logging.Level;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author antony
 */
public class SmallParkingLotTest {

    /**
     * Test of park method, of class MallParkingLot.
     */
    private static Logger logger = LoggerFactory.getLogger(SmallParkingLotTest.class);

    @Test
    public void testParkAndLeave() {
        logger.info("testParkAndLeave");
        Vehicle v1 = new VehicleBike();
        Vehicle v2 = new VehicleBike();
        Vehicle v3 = new VehicleBike();
        MallParkingLot instance;
        try {
            instance = (MallParkingLot) new Builder.ParkingLotBuilder(ValueTypes.ParkingLotType.Mall)
                    .addBikeSlots(2)
                    .addStore(new InMemoryStore())
                    .build();
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
            try {
                Ticket tkt3 = instance.park(v3);
                logger.info(tkt3.toString());
            } catch (NoSlotAvailableException ex) {
                logger.info("No more space availabe");
            }
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
            try {
                Ticket tkt3 = instance.park(v3);
                logger.info(tkt3.toString());
            } catch (NoSlotAvailableException ex) {
                logger.info("No more space availabe");
            }
            try {
                Calendar date = Calendar.getInstance();
                long timeInSecs = date.getTimeInMillis();
                Date after3hr30Mins = new Date(timeInSecs + (210 * 60 * 1000));
                Receipt rcpt1 = instance.leave(v1, after3hr30Mins);
                logger.info(rcpt1.toString());
            } catch (NoTicketFoundException ex) {
                logger.info("No Ticket Found");;
            }
        } catch (InvalidSlotCountException ex) {
            logger.info("InvalidSlotCountException");

        }
    }
}
