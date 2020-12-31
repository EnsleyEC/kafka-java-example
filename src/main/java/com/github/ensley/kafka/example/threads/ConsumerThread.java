package com.github.ensley.kafka.example.threads;

import com.github.ensley.kafka.example.config.KafkaConfig;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.errors.WakeupException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.Duration;
import java.util.Arrays;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

public class ConsumerThread extends Thread{

    private KafkaConsumer<String,String> consumer;
    private Logger logger;

    public ConsumerThread(String topic)
    {
        init(topic);
    }

    public void init(String topic)
    {
        logger = LoggerFactory.getLogger(ConsumerThread.class.getName());

        logger.info("Creating the consumer thread");

        // create consumer configs
        Properties properties = KafkaConfig.getConsumerProperties();
        // create consumer
        consumer = new KafkaConsumer<String, String>(properties);
        // subscribe consumer to our topic(s)
        consumer.subscribe(Arrays.asList(topic));

    }

    public void run()
    {
        // poll for new data
        try {
            while (true) {
                ConsumerRecords<String, String> records =
                        consumer.poll(Duration.ofMillis(100)); // new in Kafka 2.0.0

                for (ConsumerRecord<String, String> record : records) {
                    logger.info("Key: " + record.key() + ", Value: " + record.value());
                    logger.info("Partition: " + record.partition() + ", Offset:" + record.offset());
                }
            }
        } catch (WakeupException e) {
            logger.info("Received shutdown signal!");
        } finally {
            consumer.close();
        }


    }

    public void shutdown() {
        // the wakeup() method is a special method to interrupt consumer.poll()
        // it will throw the exception WakeUpException
        consumer.wakeup();
    }

}
