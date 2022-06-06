/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vpark.actions;

import com.vpark.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author antony
 */
public enum Command {

    PARKINGLOT_NEW,
    PARKINGLOT_REPORT,
    VEHCLE_PARK,
    VEHICLE_LEAVE,
    VEHICLE_STATUS,
    APP_CLOSE,
    COMMAND_UNKNOWN;

    private static Logger logger = LoggerFactory.getLogger(Command.class);

    public static Command get(String command) {
        if (command.equals(Constants.PARKINGLOT_NEW)) {
            logger.info("PARKINGLOT_NEW inside");
            return PARKINGLOT_NEW;
        } else if (command.equals(Constants.PARKINGLOT_REPORT)) {
            return PARKINGLOT_REPORT;
        } else if (command.equals(Constants.VEHCLE_PARK)) {
            logger.info("VEHCLE_PARK inside");
            return VEHCLE_PARK;
        } else if (command.equals(Constants.VEHICLE_LEAVE)) {
            return VEHICLE_LEAVE;
        } else if (command.equals(Constants.VEHICLE_STATUS)) {
            return VEHICLE_STATUS;
        } else if (command.equals(Constants.APP_CLOSE)) {
            return APP_CLOSE;
        } else {
            return COMMAND_UNKNOWN;
        }
    }
}
