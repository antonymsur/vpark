/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vpark.pricing;

import com.vpark.model.ValueTypes.SlotType;
import java.util.Calendar;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author antony
 */
public class FlatPricingStrategyTest {

    /**
     * Test of setHourlyBikeSlotPrice method, of class FlatPricingStrategy.
     */
    @Test
    public void testSetHourlyBikeSlotPrice() {
        System.out.println("setHourlyBikeSlotPrice");
        Double amount = 50.0;
        FlatPricingStrategy instance = new FlatPricingStrategy();
        instance.setHourlyBikeSlotPrice(amount);
        Double expected = amount;
        Double result = instance.hourlyPricingSlot.get((SlotType.Bike));
        assertEquals(expected, result);
    }

    /**
     * Test of setHourlyLVSlotPrice method, of class FlatPricingStrategy.
     */
    @Test
    public void testSetHourlyLVSlotPrice() {
        System.out.println("setHourlyLVSlotPrice");
        Double amount = 70.0;
        FlatPricingStrategy instance = new FlatPricingStrategy();
        instance.setHourlyLVSlotPrice(amount);
        Double expected = amount;
        Double result = instance.hourlyPricingSlot.get((SlotType.LV));
        assertEquals(expected, result);
    }

    /**
     * Test of setHourlyHVSlotPrice method, of class FlatPricingStrategy.
     */
    @Test
    public void testSetHourlyHVSlotPrice() {
        System.out.println("setHourlyHVSlotPrice");
        Double amount = 100.0;
        FlatPricingStrategy instance = new FlatPricingStrategy();
        instance.setHourlyHVSlotPrice(amount);
        Double expected = amount;
        Double result = instance.hourlyPricingSlot.get(SlotType.HV);
        assertEquals(expected, result);
    }

    /**
     * Test of calculate method, of class FlatPricingStrategy.
     */
    @Test
    public void testCalculate() {
        System.out.println("calculate");
        SlotType psType = SlotType.HV;
        Date entryTime = new Date();
        Calendar date = Calendar.getInstance();
        long timeInSecs = date.getTimeInMillis();
        Date exitTime = new Date(timeInSecs + (120 * 60 * 1000));
        Double amount = 100.0;
        FlatPricingStrategy instance = new FlatPricingStrategy();
        instance.setHourlyHVSlotPrice(amount);
        Double expResult = 200.0;
        Double result = instance.calculate(psType, entryTime, exitTime);
        assertEquals(expResult, result);

    }

    /**
     * Test of getHours method, of class FlatPricingStrategy.
     */
    @Test
    public void testGetHours() {
        System.out.println("getHours");
        Date entryTime = new Date();
        Calendar date = Calendar.getInstance();
        long timeInSecs = date.getTimeInMillis();
        Date exitTime = new Date(timeInSecs + (120 * 60 * 1000));
        FlatPricingStrategy instance = new FlatPricingStrategy();
        int expResult = 2;
        int result = instance.getHours(entryTime, exitTime);
        assertEquals(expResult, result);

    }

}
