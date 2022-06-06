/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vpark.model;

import com.vpark.exceptions.InvalidSlotCountException;
import com.vpark.model.ValueTypes.ParkingLotType;
import com.vpark.model.ValueTypes.SlotType;
import com.vpark.pricing.FlatPricingStrategy;
import com.vpark.pricing.PricingStrategy;
import com.vpark.store.InMemoryStore;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antony
 */
public class MallParkingLotTest {

    /**
     * Test of getType method, of class MallParkingLot.
     */
    @Test
    public void testGetType() throws InvalidSlotCountException {
        System.out.println("getType");
        MallParkingLot instance = (MallParkingLot) new Builder.ParkingLotBuilder(ParkingLotType.Mall)
                .addBikeSlots(20)
                .addHVSlots(5)
                .addLVSlots(10)
                .addStore(new InMemoryStore())
                .build();
        ParkingLotType expResult = ParkingLotType.Mall;
        ParkingLotType result = instance.getType();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPricingStrategy method, of class MallParkingLot.
     */
    @Test
    public void testGetPricingStrategy() throws InvalidSlotCountException {
        System.out.println("getPricingStrategy");
        MallParkingLot instance = (MallParkingLot) new Builder.ParkingLotBuilder(ParkingLotType.Mall)
                .addBikeSlots(20)
                .addHVSlots(5)
                .addLVSlots(10)
                .addStore(new InMemoryStore())
                .build();
        PricingStrategy expResult = new FlatPricingStrategy();
        PricingStrategy result = instance.getPricingStrategy();
        assertEquals(expResult.getClass(), result.getClass());

    }

    /**
     * Test of getTotalSlots method, of class MallParkingLot.
     */
    @Test
    public void testGetTotalSlots() throws InvalidSlotCountException {
        System.out.println("getTotalSlots");
        SlotType type = SlotType.Bike;
        MallParkingLot instance = (MallParkingLot) new Builder.ParkingLotBuilder(ParkingLotType.Mall)
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
     * Test of getAvailableSlots method, of class MallParkingLot.
     */
    @Test
    public void testGetAvailableSlots() throws InvalidSlotCountException {
        System.out.println("getAvailableSlots");
        SlotType type = SlotType.LV;
        MallParkingLot instance = (MallParkingLot) new Builder.ParkingLotBuilder(ParkingLotType.Mall)
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
     * Test of park method, of class MallParkingLot.
     */
    @Test
    public void testPark() throws Exception {
        System.out.println("park");
        Vehicle v = new VehicleBike();
        MallParkingLot instance = (MallParkingLot) new Builder.ParkingLotBuilder(ParkingLotType.Mall)
                .addBikeSlots(20)
                .addHVSlots(5)
                .addLVSlots(10)
                .addStore(new InMemoryStore())
                .build();
        Ticket expResult = new Ticket("Mall1", SlotType.Bike, ParkingLotType.Mall, new Date());
        Ticket result = instance.park(v);
        assertEquals(expResult.getId(), result.getId());
        assertEquals(expResult.plType, result.plType);
        assertEquals(expResult.slotType, result.slotType);

    }

}
