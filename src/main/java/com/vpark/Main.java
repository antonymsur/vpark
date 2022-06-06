package com.vpark;

import com.vpark.actions.Command;
import com.vpark.actions.LeaveOperation;
import com.vpark.actions.ParkOperation;
import com.vpark.actions.ParkingOperation;
import com.vpark.actions.ParkingStatusOperation;
import com.vpark.exceptions.InvalidSlotCountException;
import com.vpark.model.Builder;
import com.vpark.model.ParkingLot;
import com.vpark.model.ValueTypes.ParkingLotType;
import com.vpark.model.ValueTypes.SlotType;
import com.vpark.store.InMemoryStore;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static Logger logger = LoggerFactory.getLogger(Main.class);
    private static ParkingLot plot;

    public static void main(String[] args) {
        printAppInfo();

        try {

            BufferedReader reader = new BufferedReader(
                    new InputStreamReader(System.in));
            boolean noExit = true;
            
            while (noExit) {
                if (plot == null) {
                    logger.info("No Parking lot Ready to Serve. Please create a Parking lot first!!!");
                }
                String line = reader.readLine();
                if (line.trim().length() <= 0) {
                    continue;
                }
                noExit = takeAction(line);

            }
        } catch (Exception ex) {
            logger.error("Irrecoverable Error, Exiting.. ", ex);
        }
    }

    public static boolean takeAction(String line) {
        ParkingOperation operation;
        HashMap<String, Object> commandMap = parse(line);
        Command cmd = (Command) commandMap.get("cmd");
        ArrayList<String> argList = (ArrayList<String>) commandMap.get("args");
        SlotType sType = null;
        ParkingLotType plType = null;
        String vType = null;
        String ticketId = null;
        logger.info("Command to execute: " + cmd + argList);
        switch (cmd) {
            case PARKINGLOT_NEW:
                plType = null;
                vType = argList.get(0);
                if ("mall".equalsIgnoreCase(vType)) {
                    plType = ParkingLotType.Mall;
                } else if ("airport".equalsIgnoreCase(vType)) {
                    plType = ParkingLotType.Airport;
                } else if ("stadium".equalsIgnoreCase(vType)) {
                    plType = ParkingLotType.Stadium;
                }
                int bikeSlots = 10;
                int lvSlots = 10;
                int hvSlots = 10;
                try {
                    if (argList.size() > 1) {
                        bikeSlots = Integer.parseInt(argList.get(1));
                    }

                    if (argList.size() > 2) {
                        lvSlots = Integer.parseInt(argList.get(2));
                    }
                    if (argList.size() > 3) {
                        hvSlots = Integer.parseInt(argList.get(3));
                    }
                    try {
                        plot = new Builder.ParkingLotBuilder(plType)
                                .addBikeSlots(bikeSlots)
                                .addHVSlots(hvSlots)
                                .addLVSlots(lvSlots)
                                .addStore(new InMemoryStore())
                                .build();
                        logger.info("Created a Parking Lot :" + plot);
                    } catch (InvalidSlotCountException ex) {
                        logger.error("Create Parking Lot Error !!!", ex);
                    }

                } catch (NumberFormatException e) {
                    logger.error("Error Parsing Arguments for create command", e);
                }

                break;
            case PARKINGLOT_REPORT:
                //TODO
                break;
            case VEHCLE_PARK:
                //bike/lmv/hmv
                sType = null;
                vType = argList.get(0);
                if ("bike".equalsIgnoreCase(vType)) {
                    sType = SlotType.Bike;
                } else if ("lmv".equalsIgnoreCase(vType)) {
                    sType = SlotType.LV;
                } else if ("hmv".equalsIgnoreCase(vType)) {
                    sType = SlotType.HV;
                }
                if (sType != null) {
                    operation = new ParkOperation(plot, sType);
                    operation.execute();
                }
                break;
            case VEHICLE_LEAVE:
                //bike/lmv/hmv
                sType = null;
                vType = argList.get(0);
                ticketId = argList.get(1);
                if ("bike".equalsIgnoreCase(vType)) {
                    sType = SlotType.Bike;
                } else if ("lmv".equalsIgnoreCase(vType)) {
                    sType = SlotType.LV;
                } else if ("hmv".equalsIgnoreCase(vType)) {
                    sType = SlotType.HV;
                }
                if (sType != null) {
                    operation = new LeaveOperation(plot, ticketId, sType);
                    operation.execute();
                }

                break;
            case VEHICLE_STATUS:
                sType = null;
                vType = argList.get(0);
                ticketId = argList.get(1);
                if ("bike".equalsIgnoreCase(vType)) {
                    sType = SlotType.Bike;
                } else if ("lmv".equalsIgnoreCase(vType)) {
                    sType = SlotType.LV;
                } else if ("hmv".equalsIgnoreCase(vType)) {
                    sType = SlotType.HV;
                }
                if (sType != null) {
                    operation = new ParkingStatusOperation(plot, ticketId, sType);
                    operation.execute();
                }
                break;
            case APP_CLOSE:
                return false;
               
        }
        return true;
    }

    public static HashMap<String, Object> parse(String cmdLine) {
        HashMap<String, Object> commandMap = new HashMap<String, Object>();
        StringTokenizer tokens = new StringTokenizer(cmdLine);
        String strCmd = tokens.nextToken();
        Command cmd = Command.get(strCmd);
        commandMap.put("cmd", cmd);
        if (cmd == Command.COMMAND_UNKNOWN) {
            commandMap.put("args", null);
        } else {
            ArrayList<String> args = new ArrayList<String>();
            while (tokens.hasMoreTokens()) {
                args.add(tokens.nextToken());
            }
            commandMap.put("args", args);
        }
        return commandMap;
    }

  

    private static void printAppInfo() {
        StringBuilder builder = new StringBuilder();
        builder.append("\n");
        builder.append("===============VPark Parking System=================\n");
        builder.append("\tActions: \n");
        builder.append("\tcreate  : create new parking lot\n");
        builder.append("\t\targs : parking lot type either of 'mall,stadium,airport'\n");
        builder.append("\tpreport : parking lot report , TODO\n");
        builder.append("\tpark    : park vehicle in one of the slots available based on vehicle type\n");
        builder.append("\t\targs : one of the values from 'bike,lmv,hmv'\n");
        builder.append("\tleave   : leave the parking lot\n");
        builder.append("\t\targs :  one of the values from 'bike,lmv,hmv'\n");
        builder.append("\t\t        Ticket Id \n");
        builder.append("\tvstatus : vehicle status  \n");
        builder.append("\t\targs :  one of the values from 'bike,lmv,hmv'\n");
        builder.append("\t\t        Ticket Id \n");
        builder.append("\tclose	  : application close\n");

        logger.info(builder.toString());
    }
}

//
//Perform Action
/*
    // Use Case Airport
    ParkingLot plot = new Builder.ParkingLotBuilder()
            .addBikeSlots(SlotType.Bike, new Integer(10))
            .addHVSlots(SlotType.HV, new Integer(10))
            .addLVSlots(SlotType.LV, new Integer(10))
            .addStore(new InMemoryStore())
            .build(ParkingLotType.Airport);
    //Waiting for Input
    Vehicle v = new VehicleBike();
    plot.park(v);
    Calendar date = Calendar.getInstance();
    long timeInSecs = date.getTimeInMillis();
    Date afterAdding1500Mins = new Date(timeInSecs + (1500 * 60 * 1000));
    Receipt rcpt = plot.leave(v, afterAdding1500Mins);
    logger.info(rcpt.toJSON());
    //Perform Action
 */
 /*
    // Use Case Stadium
     ParkingLot plot = new Builder.ParkingLotBuilder()
             .addBikeSlots(SlotType.Bike, new Integer(10))
             .addHVSlots(SlotType.HV, new Integer(10))
             .addLVSlots(SlotType.LV, new Integer(10))
             .addStore(new InMemoryStore())
             .build(ParkingLotType.Stadium);
     //Waiting for Input
     Vehicle v = new VehicleBike();
     plot.park(v);
     Calendar date = Calendar.getInstance();

     long timeInSecs = date.getTimeInMillis();
     Date afterAdding1500Mins = new Date(timeInSecs + (1500 * 60 * 1000));
     Receipt rcpt = plot.leave(v, afterAdding1500Mins);
     logger.info(rcpt.toJSON());

     //Perform Action
 */
