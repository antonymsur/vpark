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
import com.vpark.model.StadiumParkingLot;
import com.vpark.model.Receipt;
import com.vpark.model.Ticket;
import com.vpark.model.ValueTypes;
import com.vpark.model.Vehicle;
import com.vpark.model.VehicleBike;
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
public class StadiumParkingLotTest {

    /**
     * Test of park method, of class StadiumParkingLotTest.
     */
    private static Logger logger = LoggerFactory.getLogger(StadiumParkingLotTest.class);

    @Test
    public void testParkAndLeave() {
        logger.info("testParkAndLeave");
        Vehicle v1 = new VehicleBike();
        Vehicle v2 = new VehicleBike();

        StadiumParkingLot instance;
        try {
            // Create Stadium ParkingLot with 1000 bikes 1500 Light Vehicles
            // Heavy Vehicles
            instance = (StadiumParkingLot) new Builder.ParkingLotBuilder(ValueTypes.ParkingLotType.Stadium)
                    .addBikeSlots(1000)
                    .addLVSlots(1500)
                    .addStore(new InMemoryStore())
                    .build();
            //Park V1 Motorcycle for 3 hr 40 mins
            try {
                Ticket tkt1 = instance.park(v1);
                logger.info(tkt1.toString());
            } catch (NoSlotAvailableException ex) {
                logger.info("NoSlotAvailableException");
            }
            //Motor Cycle Unparked after 3 hrs 30 mins
            try {
                Calendar date = Calendar.getInstance();
                long timeInSecs = date.getTimeInMillis();
                Date after3hr40Mins = new Date(timeInSecs + (220 * 60 * 1000));
                Receipt rcpt1 = instance.leave(v1, after3hr40Mins);
                logger.info(rcpt1.toString());
            } catch (NoTicketFoundException ex) {
                logger.info("No Ticket Found");;
            }
            
            // Park V2 Motorcycle for 14 hr 59 mins
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
                Date after14hrs59Mins = new Date(timeInSecs + (899 * 60 * 1000));
                Receipt rcpt1 = instance.leave(v2, after14hrs59Mins);
                logger.info(rcpt1.toString());
            } catch (NoTicketFoundException ex) {
                logger.info("No Ticket Found");;
            }
            {
                logger.info("No more space availabe");
            }
            
            // VehicleLV Electric SUV  11hr 30min
            Vehicle v3 = new VehicleLV();
            //Park V3 for 11hr 30min
            try {
                Ticket tkt1 = instance.park(v3);
                logger.info(tkt1.toString());
            } catch (NoSlotAvailableException ex) {
                logger.info("NoSlotAvailableException");
            }
            //Unparked after11hr 30min
            try {
                Calendar date = Calendar.getInstance();
                long timeInSecs = date.getTimeInMillis();
                Date after11hr30Min = new Date(timeInSecs + (690 * 60 * 1000));
                Receipt rcpt1 = instance.leave(v3, after11hr30Min);
                logger.info(rcpt1.toString());
            } catch (NoTicketFoundException ex) {
                logger.info("No Ticket Found");;
            }

            // SUV 13 hr 5min
            Vehicle v4 = new VehicleLV();
            //Park V3 for 13 Hrs 5 min
            try {
                Ticket tkt1 = instance.park(v4);
                logger.info(tkt1.toString());
            } catch (NoSlotAvailableException ex) {
                logger.info("NoSlotAvailableException");
            }
            //Unparked after 13 hr 5min
            try {
                Calendar date = Calendar.getInstance();
                long timeInSecs = date.getTimeInMillis();
                Date after13hr5Min = new Date(timeInSecs + (785 * 60 * 1000));
                Receipt rcpt1 = instance.leave(v4, after13hr5Min);
                logger.info(rcpt1.toString());
            } catch (NoTicketFoundException ex) {
                logger.info("No Ticket Found");;
            }

        } catch (InvalidSlotCountException ex) {
            logger.info("InvalidSlotCountException");

        }
    }
}
