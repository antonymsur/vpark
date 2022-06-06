package com.vpark.model;

import com.vpark.exceptions.NoSlotAvailableException;
import com.vpark.exceptions.NoTicketFoundException;
import com.vpark.exceptions.NoVehicleFoundException;
import com.vpark.model.ValueTypes.ParkingLotType;
import com.vpark.model.ValueTypes.SlotType;
import com.vpark.pricing.PricingStrategy;
import com.vpark.store.Store;
import java.util.Date;
import java.util.HashMap;

public abstract class ParkingLot {

    protected String name;
    protected HashMap<SlotType, Integer> parkingSlots = new HashMap<SlotType, Integer>();
    protected HashMap<SlotType, Integer> availableParkingSlots = new HashMap<SlotType, Integer>();
    protected HashMap parkedVehicles = new HashMap<String, Vehicle>();
    protected PricingStrategy pricingStrategy;
    protected Store store;

    public abstract ParkingLotType getType();

    public abstract PricingStrategy getPricingStrategy();

    public abstract int getTotalSlots(SlotType type);

    public abstract int getAvailableSlots(SlotType type);

    public abstract Vehicle getParkedVehicle(String hash) throws NoVehicleFoundException;

    public abstract Ticket park(Vehicle v) throws NoSlotAvailableException;

    public abstract Receipt leave(Vehicle v, Date exitTime) throws NoTicketFoundException;

    public String getHash(String ticketId, SlotType slotType, ParkingLotType plType) {
        return ticketId + slotType.name() + plType.name();
    }
}
