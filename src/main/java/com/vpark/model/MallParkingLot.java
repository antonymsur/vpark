package com.vpark.model;

import com.vpark.Constants;
import com.vpark.exceptions.NoSlotAvailableException;
import com.vpark.exceptions.NoTicketFoundException;
import com.vpark.model.ValueTypes.ParkingLotType;
import com.vpark.model.ValueTypes.SlotType;
import com.vpark.pricing.PricingStrategy;
import java.util.Date;

import org.slf4j.LoggerFactory;

public class MallParkingLot extends ParkingLot {

    private static int ticketNo = 0;
    private static org.slf4j.Logger logger = LoggerFactory.getLogger(MallParkingLot.class);

    public MallParkingLot(Builder.ParkingLotBuilder builder) {
        this.name = Constants.MALL_NAME;
        pricingStrategy = builder.pricingStrategy;
        availableParkingSlots = builder.availableParkingSlots;
        parkingSlots = builder.availableParkingSlots;
        store = builder.store;
    }

    public ParkingLotType getType() {
        return ParkingLotType.Mall;
    }

    public PricingStrategy getPricingStrategy() {
        return this.pricingStrategy;
    }

    @Override
    public int getTotalSlots(SlotType type) {
        return parkingSlots.get(type).intValue();
    }

    @Override
    public int getAvailableSlots(SlotType type) {
        return availableParkingSlots.get(type).intValue();
    }

    @Override
    public Vehicle getParkedVehicle(String hash) {
        return (Vehicle) parkedVehicles.get(hash);
    }

    @Override
    public Ticket park(Vehicle v) throws NoSlotAvailableException {
        logger.info("availableParkingSlots " + availableParkingSlots);
        int available = availableParkingSlots.get(v.getSlotType()).intValue();
        if (available <= 0) {
            throw new NoSlotAvailableException();
        }
        String ticketId = name + (++ticketNo);
        Ticket ticket = new Ticket(ticketId, v.getSlotType(), getType(), new Date());
        v.assignTicket(ticket);
        String hash = getHash(ticketId, v.getSlotType(), getType());
        parkedVehicles.put(hash, v);

        //reduce one
        availableParkingSlots.put(v.getSlotType(), available - 1);
        store.saveTicket(ticket);
        return ticket;
    }

    @Override
    public Receipt leave(Vehicle v, Date exitTime) throws NoTicketFoundException {
        if (v == null || v.getTicket() == null) {
            throw new NoTicketFoundException();
        }
        Ticket tkt = v.getTicket();
        String ticketId = tkt.getId();
        String hash = getHash(ticketId, v.getSlotType(), getType());
        Double payableAmount = getPricingStrategy().calculate(v.getSlotType(), tkt.entryTime, exitTime);
        logger.info("Payable Amount " + payableAmount);
        parkedVehicles.remove(hash);
        //increment one
        int available = availableParkingSlots.get(v.getSlotType()).intValue();
        availableParkingSlots.put(v.getSlotType(), available + 1);
        Receipt receipt = new Receipt(tkt, exitTime, payableAmount);
        store.saveReceipt(receipt);
        return receipt;
    }

}
