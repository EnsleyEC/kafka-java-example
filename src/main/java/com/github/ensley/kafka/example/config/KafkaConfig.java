package com.github.ensley.kafka.example.config;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class KafkaConfig {

    private static String server = "localhost:9092";
    private static String groupId = "my-third-application";
    private static String autoOffsetReset = "earliest";

    public static Properties getProducerProperties() {

        Properties appProps = new Properties();
        appProps.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,server);
        appProps.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        appProps.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,StringSerializer.class.getName());

        return appProps;

    }

    public static Properties getConsumerProperties() {

        Properties appProps = new Properties();
        appProps.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, server);
        appProps.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        appProps.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        appProps.setProperty(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        appProps.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, autoOffsetReset);

        return appProps;

    }

}
