package com.example;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.kstream.KStream;

import java.util.Properties;

public class DslExample {

    public static final String SOURCE_TOPIC_NAME = "users";
    public static final Properties config = new Properties();
    static{
        // set the required properties for running Kafka Streams

        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "gpdev1");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.Void().getClass());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass());
    }

    public static void main(String args[]){

        //The stream builder used to construct the topology
        //what is a topology ? it's a template for the stream application which can be scaled into multiple instances need basis
        StreamsBuilder builder = new StreamsBuilder();

        //create a stream to read from source topic , which is called a source processor
        KStream<Void,String> usersStream = builder.stream(SOURCE_TOPIC_NAME);

        usersStream.foreach((key,value) -> {
            System.out.println("DSL example - Hello world "+value);
        });

        //build the topology
        KafkaStreams streams = new KafkaStreams(builder.build(),config);

        //start streaming
        streams.start();

        //Add shutdown hook to gracefully close the kafka streams
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}
