package com.test.config.queue;


public class Receiver {


    public void receiveMessage(String message) {
        System.out.println("Received <" + message + ">");
    }
}