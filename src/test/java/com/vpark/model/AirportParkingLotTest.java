/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vpark.model;

import com.vpark.exceptions.InvalidSlotCountException;
import com.vpark.model.ValueTypes.ParkingLotType;
import com.vpark.model.ValueTypes.SlotType;
import com.vpark.pricing.PricingStrategy;
import com.vpark.store.InMemoryStore;
import com.vpark.model.Builder;
import com.vpark.pricing.AirportIntervalPricingStrategy;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antony
 */
public class AirportParkingLotTest {
    
    /**
     * Test of getType method, of class AirportParkingLot.
     */
    @Test
    public void testGetType() throws InvalidSlotCountException {
        System.out.println("getType");
        AirportParkingLot instance =  (AirportParkingLot)new Builder.ParkingLotBuilder(ParkingLotType.Airport)
            .addBikeSlots(20)
            .addHVSlots(5)
            .addLVSlots(10)
            .addStore(new InMemoryStore())
            .build();
        ValueTypes.ParkingLotType expResult = ValueTypes.ParkingLotType.Airport;
        ValueTypes.ParkingLotType result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPricingStrategy method, of class AirportParkingLot.
     */
    @Test
    public void testGetPricingStrategy() throws InvalidSlotCountException {
        System.out.println("getPricingStrategy");
        AirportParkingLot instance =  (AirportParkingLot)new Builder.ParkingLotBuilder(ParkingLotType.Airport)
            .addBikeSlots(20)
            .addHVSlots(5)
            .addLVSlots(10)
            .addStore(new InMemoryStore())
            .build();
        PricingStrategy expResult = new AirportIntervalPricingStrategy();
        PricingStrategy result = instance.getPricingStrategy();
        assertEquals(expResult.getClass(), result.getClass());
        
    }

    /**
     * Test of getTotalSlots method, of class AirportParkingLot.
     */
    @Test
    public void testGetTotalSlots() throws InvalidSlotCountException {
        System.out.println("getTotalSlots");
        SlotType type = SlotType.Bike;
        AirportParkingLot instance =  (AirportParkingLot)new Builder.ParkingLotBuilder(ParkingLotType.Airport)
            .addBikeSlots(20)
            .addHVSlots(5)
            .addLVSlots(10)
            .addStore(new InMemoryStore())
            .build();
        int expResult = 20;
        int result = instance.getTotalSlots(type);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of getAvailableSlots method, of class AirportParkingLot.
     */
    @Test
    public void testGetAvailableSlots() throws InvalidSlotCountException {
        System.out.println("getAvailableSlots");
        SlotType type = SlotType.LV;
        AirportParkingLot instance =  (AirportParkingLot)new Builder.ParkingLotBuilder(ParkingLotType.Airport)
            .addBikeSlots(20)
            .addHVSlots(5)
            .addLVSlots(10)
            .addStore(new InMemoryStore())
            .build();
        int expResult = 10;
        int result = instance.getAvailableSlots(type);
        assertEquals(expResult, result);
        
    }

   

    /**
     * Test of park method, of class AirportParkingLot.
     */
    @Test
    public void testPark() throws Exception {
        System.out.println("park");
        Vehicle v = new VehicleBike();
        AirportParkingLot instance = (AirportParkingLot)new Builder.ParkingLotBuilder(ParkingLotType.Airport)
            .addBikeSlots(20)
            .addHVSlots(5)
            .addLVSlots(10)
            .addStore(new InMemoryStore())
            .build();
        Ticket expResult = new Ticket("Airport1",SlotType.Bike, ParkingLotType.Airport, new Date());
        Ticket result = instance.park(v);
        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.plType, result.plType);
        assertEquals(expResult.slotType, result.slotType);
       
    }
    
}
