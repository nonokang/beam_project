package com.ab.test;

import org.apache.beam.sdk.transforms.SerializableFunction;
import org.apache.beam.sdk.values.KV;
import org.joda.time.DateTime;
import org.joda.time.Instant;


public class DkafkaFuntion implements SerializableFunction<KV<String, String>, Instant> {

    public Instant apply(KV<String, String> input){
        String[] temps = input.getValue().split(",");
        DateTime t = new DateTime(Long.valueOf(temps[1]));
        return t.toInstant();
    }

}
