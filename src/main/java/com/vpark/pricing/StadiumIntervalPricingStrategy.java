package com.vpark.pricing;

import java.util.Date;
import com.vpark.model.ValueTypes.SlotType;
import org.slf4j.LoggerFactory;

public class StadiumIntervalPricingStrategy implements PricingStrategy {

    Double lessThan4Bike = new Double(30);
    Double fourTo12Bike = new Double(60);
    Double after12HPerHourBike = new Double(100);

    Double lessThan4LV = new Double(60);
    Double fourTo12LV = new Double(120);
    Double after12HPerHourLV = new Double(200);
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(StadiumIntervalPricingStrategy.class);

    public StadiumIntervalPricingStrategy() {

    }

    public Double calculate(SlotType psType, Date entryTime, Date exitTime) {
        int hours = getHours(entryTime, exitTime);
        Double amount = 0.0;
        if (psType == SlotType.HV) {
            return amount; // No parking Supported
        } else if (psType == SlotType.LV) {
            if (hours > 12) {
                int remainingHrs = hours - 12;
                amount = fourTo12LV + (remainingHrs * after12HPerHourLV);
            } else if (hours > 4) {
                amount = fourTo12LV;
            } else {
                amount = lessThan4LV;
            }

        } else if (psType == SlotType.Bike) {
            if (hours > 12) {
                int remainingHrs = hours - 12;
                logger.info("hours " + hours + " remainingHrs " + remainingHrs);
                amount = fourTo12Bike + (remainingHrs * after12HPerHourBike);
            } else if (hours > 4) {
                amount = fourTo12Bike;
            } else {
                amount = lessThan4Bike;
            }
        }
        return amount;
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
