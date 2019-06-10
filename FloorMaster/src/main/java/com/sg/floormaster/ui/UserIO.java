/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.floormaster.ui;

/**
 *
 * @author wgv85
 */
public interface UserIO {
    
    /**
     *
     * A very simple method that takes in a message to display on the console 
     *
     * @param msg - String of information to display to the user.
     *
     */
    void print(String msg);

    /**
     *
     * A simple method that takes in a message to display on the console, 
     * and then waits for a double answer from the user to return.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @return the answer to the message as double
     */
    double readDouble(String msgPrompt);

    /**
     *
     * A slightly more complex method that takes in a message to display on the console, 
     * and then waits for a double answer within the specified min/max range and returns it.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @param min - minimum acceptable value for return
     * @param max - maximum acceptable value for return
     * @return an double value as an answer to the message prompt within the min/max range
     */
    double readDouble(String msgPrompt, double min, double max);

    /**
     *
     * A simple method that takes in a message to display on the console, 
     * and then waits for a float answer from the user to return.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @return the answer to the message as float
     */
    float readFloat(String msgPrompt);

       /**
     *
     * A slightly more complex method that takes in a message to display on the console, 
     * and then waits for a float answer within the specified min/max range and returns it.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @param min - minimum acceptable value for return
     * @param max - maximum acceptable value for return
     * @return an float value as an answer to the message prompt within the min/max range
     */
    float readFloat(String msgPrompt, float min, float max);

    /**
     *
     * A simple method that takes in a message to display on the console, 
     * and then waits for a integer answer from the user to return.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @return the answer to the message as integer
     */
    int readInt(String msgPrompt);
    
        /**
     *
     * A slightly more complex method that takes in a message to display on the console, 
     * and then waits for a integer answer within the specified min/max range and returns it.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @param min - minimum acceptable value for return
     * @param max - maximum acceptable value for return
     * @return an integer value as an answer to the message prompt within the min/max range
     */
    int readInt(String msgPrompt, int min, int max);

    /**
     *
     * A simple method that takes in a message to display on the console, 
     * and then waits for a long answer from the user to return.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @return the answer to the message as long
     */
    long readLong(String msgPrompt);

    /**
     * A slightly more complex method that takes in a message to display on the console, 
     * and then waits for a long answer within the specified min/max range and returns it.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @param min - minimum acceptable value for return
     * @param max - maximum acceptable value for return
     * @return an long value as an answer to the message prompt within the min/max range
     */
    long readLong(String msgPrompt, long min, long max);

    /**
     *
     * A simple method that takes in a message to display on the console, 
     * and then waits for an answer from the user to return.
     *
     * @param msgPrompt - String explaining what information you want from the user.
     * @return the answer to the message as string
     */
    String readString(String msgPrompt);
    
}
