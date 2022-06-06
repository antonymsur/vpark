/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vpark;

import com.vpark.actions.Command;
import java.util.ArrayList;
import java.util.HashMap;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author qtpi
 */
public class MainTest {
    
    /**
     * Test of takeAction method, of class Main.
     */
    @Test
    public void testTakeAction() {
        System.out.println("takeAction");
        String line = "close";
        boolean expResult = false; //noExit flag to come out of execution
        boolean result = Main.takeAction(line);
        assertEquals(expResult, result);
        
    }

    /**
     * Test of parse method, of class Main.
     */
    @Test
    public void testParse() {
        System.out.println("parse");
        String cmdLine = "create lmv 20 15 5";
        
        HashMap<String, Object> expResult = new HashMap<String,Object>();
        expResult.put("cmd", Command.PARKINGLOT_NEW);
        ArrayList<String> argsList = new ArrayList<String>();
        argsList.add("lmv");
        argsList.add("20");
        argsList.add("15");
        argsList.add("5");
        expResult.put("args", argsList); 
        HashMap<String, Object> result = Main.parse(cmdLine);
        assertEquals(expResult, result);

    }

    
}
