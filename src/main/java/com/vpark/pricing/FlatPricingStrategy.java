package com.vpark.pricing;

import com.vpark.model.ValueTypes.SlotType;
import java.util.Date;
import java.util.HashMap;

public class FlatPricingStrategy implements PricingStrategy {

    HashMap<SlotType, Double> hourlyPricingSlot;

    public FlatPricingStrategy() {
        hourlyPricingSlot = new HashMap<SlotType, Double>();

    }

    public void setHourlyBikeSlotPrice(Double amount) {
        hourlyPricingSlot.put(SlotType.Bike, amount);
    }

    public void setHourlyLVSlotPrice(Double amount) {
        hourlyPricingSlot.put(SlotType.LV, amount);
    }

    public void setHourlyHVSlotPrice(Double amount) {
        hourlyPricingSlot.put(SlotType.HV, amount);
    }

    public Double calculate(SlotType psType, Date entryTime, Date exitTime) {
        int hours = getHours(entryTime, exitTime);
        Double hourlyPrice = hourlyPricingSlot.get(psType);
        return hourlyPrice * hours;
    }

    public int getHours(Date entryTime, Date exitTime) {
        long secs = (exitTime.getTime() - entryTime.getTime()) / 1000;
        int hours = (int) (secs / 3600);
        secs = secs % 3600;
        int mins = (int) (secs / 60);
        secs = secs % 60;
        if (mins > 1) {
            hours++;
        }
        return hours;
    }
}
