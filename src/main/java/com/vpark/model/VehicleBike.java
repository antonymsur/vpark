package com.vpark.model;

import com.vpark.model.ValueTypes.SlotType;
import com.vpark.model.ValueTypes.VehicleType;

public class VehicleBike extends Vehicle {

    public VehicleType getType() {
        return VehicleType.Bike;
    }

    @Override
    public SlotType getSlotType() {
        return SlotType.Bike;
    }
}
