package com.vpark.model;

import com.vpark.model.ValueTypes.VehicleType;

public class VehicleHV extends Vehicle {

    //Ticket
    public VehicleType getType() {
        return VehicleType.HV;
    }

    @Override
    public ValueTypes.SlotType getSlotType() {
        return ValueTypes.SlotType.HV;
    }

}
