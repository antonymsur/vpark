/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vpark.actions;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *
 * @author antony
 */
public class ParkingExecuteBatchOperation implements ParkingOperation {

    File input;
    File output;
    private static Logger logger = LoggerFactory.getLogger(ParkingExecuteBatchOperation.class);

    public ParkingExecuteBatchOperation(String fileInput) {
        input = new File(fileInput);
    }

    @Override
    public void execute() {
        

    }

}
