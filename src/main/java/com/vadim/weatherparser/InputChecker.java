package com.vadim.weatherparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputChecker {

    private static Logger logger = LogManager.getLogger(InputChecker.class);

    public static Integer getInteger(int from, int to) {
        logger.info("Class: " + InputChecker.class + " Method: getInteger " + "params: from = " + from + " to = " + to);
        boolean isCorrect = false;
        int result = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        do {
            try {
                result = Integer.parseInt(bufferedReader.readLine());
                if (result >= from && result <= to) {
                    isCorrect = true;
                }
            } catch (IOException exception) {
                logger.error("Class: " + InputChecker.class + " Method: getInteger " + "exception: " + exception + " info: " + exception.getMessage());
            }
        } while (!isCorrect);
        return result;
    }
}
