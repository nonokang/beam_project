package com.ab.test;

import org.apache.beam.runners.direct.DirectRunner;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.kafka.KafkaIO;
import org.apache.beam.sdk.io.kafka.KafkaRecord;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.DoFn;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
//import org.apache.beam.sdks.java.io.kafka.repackaged.com.google.common.collect.ImmutableMap;
import org.apache.kafka.common.serialization.StringDeserializer;

public class DkafkaIo {

    public static void main(String[] arg){
        // Start by defining the options for the pipeline.
        PipelineOptions options = PipelineOptionsFactory.create();
        options.setRunner(DirectRunner.class);
        // Then create the pipeline.
        Pipeline p = Pipeline.create(options);

        PCollection<KV<String,String>> pc = p.apply("kafka-read", KafkaIO.<String, String>read().withBootstrapServers("localhost:9092")
                .withTopic("wpktopic")
                .withKeyDeserializer(StringDeserializer.class)//必需
                .withValueDeserializer(StringDeserializer.class)//必需
//                .withTimestampFn(new DkafkaFuntion())
//                .updateConsumerProperties(ImmutableMap.<String, Object>of("auto.offset.reset", "earliest"))
                .withoutMetadata()
        );
        /*pc.apply("kafka-content", MapElements.via( // 拼接最后的格式化输出（Key为Word，Value为Count）
                new SimpleFunction<KV<String, String>, String>() {
                    @Override
                    public String apply(KV<String, String> input) {
                        System.out.println(input.getKey() + ": " + input.getValue());
                        return input.getKey() + ": " + input.getValue();
                    }
                }));*/

        pc.apply(ParDo.of(new DoFn<KV<String, String>, String>() {
            @ProcessElement
            public void processElement(ProcessContext c1) {
                System.out.println("=="+c1.element());
                c1.output(c1.element().getValue());
            }
        }));

        p.run().waitUntilFinish();

    }

}
