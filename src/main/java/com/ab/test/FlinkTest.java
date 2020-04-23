package com.ab.test;

import org.apache.beam.runners.flink.FlinkPipelineOptions;
import org.apache.beam.runners.flink.FlinkRunner;
import org.apache.beam.runners.flink.FlinkRunnerRegistrar;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.*;
import org.apache.beam.sdk.values.KV;

public class FlinkTest {

    public static void main(String[] arg){
        // Start by defining the options for the pipeline.
        PipelineOptions options = PipelineOptionsFactory.create();
        FlinkOptions fo = new FlinkOptions();
        fo.setFlinkMaster("172.17.8.101:31123");
        fo.setAppName("flink-wpk-test");
        FlinkRunner fr = FlinkRunner.fromOptions(fo);
        options.setRunner(FlinkRunner.class);
        // Then create the pipeline.
        Pipeline p = Pipeline.create(options);
        p.apply("get-test-content", TextIO.read().from("C:\\Users\\ennwpae\\Desktop\\apache_beam.txt"))
                .apply("count-word", ParDo.of(new DoFn<String, String>() {
                    @ProcessElement
                    public void processElement(ProcessContext c1) {
                        for (String word : c1.element().split("[\\s:\\,\\.\\-]+")) {
                            if (!word.isEmpty()) {
                                c1.output(word);
                            }
                        }
                    }
                }))
                .apply(Count.<String> perElement()) // 统计每一个Word的Count
                .apply("ConcatResultKVs", MapElements.via( // 拼接最后的格式化输出（Key为Word，Value为Count）
                        new SimpleFunction<KV<String, Long>, String>() {
                            @Override
                            public String apply(KV<String, Long> input) {
                                System.out.println(input.getKey() + ": " + input.getValue());
                                return input.getKey() + ": " + input.getValue();
                            }
                        }))
                .apply(TextIO.write().to("wpk").withShardNameTemplate("-001-text").withNumShards(1).withFooter("=====end=====").withHeader("=====begin=====").withSuffix(".txt")); // 输出结果

        p.run().waitUntilFinish();
    }

}
