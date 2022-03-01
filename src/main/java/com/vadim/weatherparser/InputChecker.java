package com.vadim.weatherparser;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InputChecker {

    private static Logger logger = LogManager.getLogger(InputChecker.class);

    public static Integer getInteger(int from, int to) {
        boolean isCorrect = true;
        int result = 0;
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        do {
            try {
                result = bufferedReader.read();
                if (result < from || result < to) {
                    isCorrect = false;
                }
            } catch (IOException exception) {
                logger.error("Class: " + InputChecker.class + " Method: getInteger " + "exception: " + exception + " info: " + exception.getMessage());
                isCorrect = false;
            }
        } while (isCorrect);
        return result;
    }
}