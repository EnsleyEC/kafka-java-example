package com.github.ensley.kafka.example.model;

public class Message {

    private String topic;
    private String key;
    private String value;

    public Message(String topic, String key, String value)
    {
        this.topic = topic;
        this.key = key;
        this.value = value;
    }

    public String getTopic() {
        return topic;
    }

    public String getKey() {
        return key;
    }

    public String getValue() {
        return value;
    }
}
