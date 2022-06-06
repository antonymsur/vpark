package com.vpark.model;

import com.vpark.model.ValueTypes.VehicleType;

public class VehicleLV extends Vehicle {

    //Ticket
    public VehicleType getType() {
        return VehicleType.LV;
    }

    @Override
    public ValueTypes.SlotType getSlotType() {
        return ValueTypes.SlotType.LV;
    }

}
