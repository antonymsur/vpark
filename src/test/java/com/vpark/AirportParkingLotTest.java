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
import com.vpark.model.AirportParkingLot;
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
public class AirportParkingLotTest {

    /**
     * Test of park method, of class AirportParkingLot.
     */
    private static Logger logger = LoggerFactory.getLogger(AirportParkingLotTest.class);

    @Test
    public void testParkAndLeave() {
        logger.info("testParkAndLeave");
        Vehicle v1 = new VehicleBike(); //Motorcycle
        Vehicle v2 = new VehicleBike(); //Motorcycle
        Vehicle v3 = new VehicleBike(); //Motorcycle
        Vehicle v4 = new VehicleLV(); //Car
        Vehicle v5 = new VehicleLV(); //SUV
        Vehicle v6 = new VehicleLV(); //Car
        
        AirportParkingLot instance;
        try {
            // Create Airport ParkingLot with 200 Bikes/Scooter/MotorCycltes ,500 Light Vehicles and 100
            // Heavy Vehicles
            instance = (AirportParkingLot) new Builder.ParkingLotBuilder(ValueTypes.ParkingLotType.Airport)
                    .addBikeSlots(200)
                    .addLVSlots(500)
                    .addHVSlots(100)
                    .addStore(new InMemoryStore())
                    .build();
            //Park V1,V2,V3, V4,V5,V6
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
                logger.info("NoSlotAvailableException");
            }
            try {
                Ticket tkt4 = instance.park(v4);
                logger.info(tkt4.toString());
            } catch (NoSlotAvailableException ex) {
                logger.info("NoSlotAvailableException");
            }
            try {
                Ticket tkt5 = instance.park(v5);
                logger.info(tkt5.toString());
            } catch (NoSlotAvailableException ex) {
                logger.info("NoSlotAvailableException");
            }
            try {
                Ticket tkt6 = instance.park(v6);
                logger.info(tkt6.toString());
            } catch (NoSlotAvailableException ex) {
                logger.info("NoSlotAvailableException");
            }
            

            // Leave V1 after 55 mins
            try {
                Calendar date = Calendar.getInstance();
                long timeInSecs = date.getTimeInMillis();
                Date after55Mins = new Date(timeInSecs + (55 * 60 * 1000));
                Receipt rcpt1 = instance.leave(v1, after55Mins);
                logger.info(rcpt1.toString());
            } catch (NoTicketFoundException ex) {
                logger.info("No Ticket Found");;
            }
            {
                logger.info("No more space availabe");
            }
            
            // Leave V2 after 14 Hrs 59 Mins
            try {
                Calendar date = Calendar.getInstance();
                long timeInSecs = date.getTimeInMillis();
                Date after14hr59Mins = new Date(timeInSecs + (899 * 60 * 1000));
                Receipt rcpt1 = instance.leave(v2, after14hr59Mins);
                logger.info(rcpt1.toString());
            } catch (NoTicketFoundException ex) {
                logger.info("No Ticket Found");;
            }
            
            
            //Leave V3 after 1 day 12 Hrs 
         
            try {
                Calendar date = Calendar.getInstance();
                long timeInSecs = date.getTimeInMillis();
                Date after1day12hrs = new Date(timeInSecs + (2160 * 60 * 1000));
                Receipt rcpt1 = instance.leave(v3, after1day12hrs);
                logger.info(rcpt1.toString());
            } catch (NoTicketFoundException ex) {
                logger.info("No Ticket Found");;
            }
            
            //V4 Unparked after 50 min
            try {
                Calendar date = Calendar.getInstance();
                long timeInSecs = date.getTimeInMillis();
                Date after50Min = new Date(timeInSecs + (50 * 60 * 1000));
                Receipt rcpt1 = instance.leave(v4, after50Min);
                logger.info(rcpt1.toString());
            } catch (NoTicketFoundException ex) {
                logger.info("No Ticket Found");;
            }
            
              //V5 Unparked after 23 hrs 59 min
            try {
                Calendar date = Calendar.getInstance();
                long timeInSecs = date.getTimeInMillis();
                Date after25hrs59Min = new Date(timeInSecs + (1439 * 60 * 1000));
                Receipt rcpt1 = instance.leave(v5, after25hrs59Min);
                logger.info(rcpt1.toString());
            } catch (NoTicketFoundException ex) {
                logger.info("No Ticket Found");;
            }
            
            //V6 Unparked after 3days hrs 1hr
            try {
                Calendar date = Calendar.getInstance();
                long timeInSecs = date.getTimeInMillis();
                Date after3dayss1hr = new Date(timeInSecs + (4380 * 60 * 1000));
                Receipt rcpt1 = instance.leave(v6, after3dayss1hr);
                logger.info(rcpt1.toString());
            } catch (NoTicketFoundException ex) {
                logger.info("No Ticket Found");;
            }
            
        } catch (InvalidSlotCountException ex) {
            logger.info("InvalidSlotCountException");

        }
    }
}
 