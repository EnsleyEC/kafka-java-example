package com.github.ensley.kafka.example.threads;

import com.github.ensley.kafka.example.config.KafkaConfig;
import com.github.ensley.kafka.example.model.Message;
import org.apache.kafka.clients.producer.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;
import java.util.Properties;

public class ProducerThread extends Thread{

    private Properties properties;
    private Logger logger;
    private Message message;
    private final int TOTAL_MESSAGES = 3;

    public ProducerThread(Message message)
    {
        init();
        this.message = message;
    }

    public void init()
    {
        logger = LoggerFactory.getLogger(ProducerThread.class);
        properties = KafkaConfig.getProducerProperties();
    }

    public void run() {

        // create producer
        KafkaProducer<String, String> producer = new KafkaProducer<String, String>(properties);

        for (int i = 0; i < TOTAL_MESSAGES; i++) {

            // create a producer record
            final ProducerRecord<String, String> record = new ProducerRecord<String, String>(message.getTopic(),message.getKey(),message.getValue()+i);

            // send data - asynchronous
            producer.send(record, (recordMetadata, e) -> {
                // executes every time a record is successfully sent or an exception is thrown
                if (e == null) {
                    // the record was successfully sent
                    logger.info(String.format("Received new metadata. \n" +
                            "Topic: " + recordMetadata.topic() + "\n" +
                            "Partition: " + recordMetadata.partition() + " \n" +
                            "Offset: " + recordMetadata.offset() + " \n" +
                            "Timestamp: " + new Date(recordMetadata.timestamp()) + ""));
                } else {
                    logger.error("Error while producing", e);
                }
            });

        }

        // flush data and close producer
        producer.flush();
        producer.close();

    }

}
