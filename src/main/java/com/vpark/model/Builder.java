/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vpark.model;

import com.vpark.Constants;
import com.vpark.exceptions.InvalidSlotCountException;
import com.vpark.exceptions.NoParkingTypeFoundException;
import com.vpark.model.ValueTypes.ParkingLotType;
import com.vpark.model.ValueTypes.SlotType;
import com.vpark.pricing.AirportIntervalPricingStrategy;
import com.vpark.pricing.FlatPricingStrategy;
import com.vpark.pricing.StadiumIntervalPricingStrategy;
import com.vpark.pricing.PricingStrategy;
import com.vpark.store.Store;
import java.util.HashMap;

/**
 *
 * @author antony
 */
public class Builder {

    public static class ParkingLotBuilder {

        HashMap<SlotType, Integer> parkingSlots = new HashMap<SlotType, Integer>();
        HashMap<SlotType, Integer> availableParkingSlots = new HashMap<SlotType, Integer>();
        PricingStrategy pricingStrategy;
        Store store;
        ParkingLotType type;
        public ParkingLotBuilder(ParkingLotType plType){
            type = plType;
        }
        public ParkingLotBuilder addLVSlots(Integer slotCount) throws InvalidSlotCountException {
            if (slotCount.intValue() <= 0) {
                throw new InvalidSlotCountException();
            }
            parkingSlots.put(SlotType.LV, slotCount);
            availableParkingSlots.put(SlotType.LV, slotCount);
            return this;

        }

        public ParkingLotBuilder addHVSlots(Integer slotCount) throws InvalidSlotCountException {
            if (slotCount.intValue() <= 0) {
                throw new InvalidSlotCountException();
            }
            parkingSlots.put(SlotType.HV, slotCount);
            availableParkingSlots.put(SlotType.HV, slotCount);
            return this;

        }

        public ParkingLotBuilder addBikeSlots(Integer slotCount) throws InvalidSlotCountException {

            if (slotCount.intValue() <= 0) {
                throw new InvalidSlotCountException(); //TODO add custom exception
            }
            parkingSlots.put(SlotType.Bike, slotCount);
            availableParkingSlots.put(SlotType.Bike, slotCount);
            return this;

        }
        
        public ParkingLotBuilder addPricingStrategy() throws NoParkingTypeFoundException {
            switch (type) {
                case Airport:
                    pricingStrategy = new AirportIntervalPricingStrategy(); 
                    break;
                case Mall:
                    pricingStrategy = new FlatPricingStrategy();
                    ((FlatPricingStrategy) pricingStrategy).setHourlyBikeSlotPrice(Constants.FLAT_HOURLY_BIKE);
                    ((FlatPricingStrategy) pricingStrategy).setHourlyLVSlotPrice(Constants.FLAT_HOURLY_LV);
                    ((FlatPricingStrategy) pricingStrategy).setHourlyHVSlotPrice(Constants.FLAT_HOURLY_HV);
                    break;
                case Stadium:
                    pricingStrategy = new StadiumIntervalPricingStrategy(); //TODO Move to builder function
                    break;
                default:
                    throw new NoParkingTypeFoundException();
            }
            return this;
        }
        
        public ParkingLotBuilder addStore(Store store) {
            this.store = store;
            return this;
        }
        
        /**
         * build function. TODO: remove the hard coded strategy
         * @return 
         */
        public ParkingLot build() {
            ParkingLot pLot = null;
            switch (type) {
                case Airport:
                    pricingStrategy = new AirportIntervalPricingStrategy(); //TODO Move to builder function
                    pLot = new AirportParkingLot(this);
                    break;
                case Mall:
                    pricingStrategy = new FlatPricingStrategy();
                    ((FlatPricingStrategy) pricingStrategy).setHourlyBikeSlotPrice(Constants.FLAT_HOURLY_BIKE);
                    ((FlatPricingStrategy) pricingStrategy).setHourlyLVSlotPrice(Constants.FLAT_HOURLY_LV);
                    ((FlatPricingStrategy) pricingStrategy).setHourlyHVSlotPrice(Constants.FLAT_HOURLY_HV);
                    pLot = new MallParkingLot(this);
                    break;
                case Stadium:
                    pricingStrategy = new StadiumIntervalPricingStrategy(); //TODO Move to builder function
                    pLot = new StadiumParkingLot(this);
                    break;

            }
            return pLot;
        }
    }
}
