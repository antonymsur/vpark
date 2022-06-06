package com.vpark.pricing;

import java.util.Date;
import com.vpark.model.ValueTypes.SlotType;
import org.slf4j.LoggerFactory;

public class AirportIntervalPricingStrategy implements PricingStrategy {

    Double lessThan1Bike = new Double(0);
    Double oneTo8Bike = new Double(40);
    Double eightTo24Bike = new Double(60);
    Double after24HPerDayBike = new Double(80);

    Double lessThan12LV = new Double(60);
    Double twelveTo24LV = new Double(80);
    Double after24HPerDayLV = new Double(100);
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(AirportIntervalPricingStrategy.class);

    public AirportIntervalPricingStrategy() {

    }

    public Double calculate(SlotType psType, Date entryTime, Date exitTime) {
        int hours = getHours(entryTime, exitTime);
        Double amount = 0.0;
        if (psType == SlotType.HV) {
            return amount; // No parking Supported
        } else if (psType == SlotType.LV) {
            if (hours > 24) {
                int days = hours / 24;
                int remainingHrs = hours % 24;
                if (remainingHrs >= 1) {
                    days = days + 1;
                }
                amount = days * after24HPerDayLV;
            } else if (hours > 12) {
                amount = twelveTo24LV;
            } else {
                amount = lessThan12LV;
            }

        } else if (psType == SlotType.Bike) {
            if (hours > 24) {
                int days = hours / 24;
                int remainingHrs = hours % 24;
                logger.info("hours " + hours + "days " + days + " remainingHrs " + remainingHrs);
                if (remainingHrs >= 1) {
                    days = days + 1;
                }
                amount = days * after24HPerDayBike;
            } else if (hours > 8) {
                amount = eightTo24Bike;
            } else if (hours > 1) {
                amount = oneTo8Bike;
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
