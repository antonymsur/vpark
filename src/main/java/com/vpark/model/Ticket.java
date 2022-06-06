/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vpark.model;

import com.vpark.model.ValueTypes.ParkingLotType;
import com.vpark.model.ValueTypes.SlotType;
import java.util.Date;
import org.json.simple.JSONObject;

/**
 *
 * @author antony
 */
public class Ticket {

    private String id;
    Date entryTime;
    SlotType slotType;
    ParkingLotType plType;

    public Ticket(String id, SlotType type, ParkingLotType plType, Date entryTime) {
        this.slotType = type;
        this.plType = plType;
        this.id = id;
        this.entryTime = entryTime;
    }

    public String getId() {
        return id;
    }
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("===============Ticket=================\n");
        builder.append("\tParking Lot Type     : " + plType.name() + "\n");
        builder.append("\tParking Vehicle Type : " + slotType.name() + "\n");
        builder.append("\tParking Entry Time   : " + entryTime.toString() + "\n");
        builder.append("\tTicket ID    : " + id + "\n");
        builder.append("======================================\n");
        return builder.toString();
    }

    public String toJSON() {
        JSONObject json = new JSONObject();
        json.put("parking", plType.name());
        json.put("vehicle", slotType.name());
        json.put("entry", entryTime.toString());
        json.put("id", id);
        return json.toJSONString();
    }
}
