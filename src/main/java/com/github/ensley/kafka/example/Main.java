package com.github.ensley.kafka.example;

import com.github.ensley.kafka.example.model.Message;
import com.github.ensley.kafka.example.threads.ConsumerThread;
import com.github.ensley.kafka.example.threads.ProducerThread;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Main {

    private static Logger logger;

    public static void main(String[] args){

        logger = LoggerFactory.getLogger(Main.class.getName());

        String topic = "first_topic";

        // Creating consumer Thread
        ConsumerThread consumerThread = new ConsumerThread(topic);
        consumerThread.start();;

        // Creating producer Thread
        ProducerThread producerThread = new ProducerThread(new Message(topic,"id_0","test "));
        producerThread.start();

        // command to shutdown consumer poll
        // consumerThread.shutdown();

    }

}
