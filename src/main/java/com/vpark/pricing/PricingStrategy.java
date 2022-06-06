package com.vpark.pricing;

import com.vpark.model.ValueTypes.SlotType;
import java.util.Date;

public interface PricingStrategy {

    public Double calculate(SlotType psType, Date entryTime, Date exitTime);
}
