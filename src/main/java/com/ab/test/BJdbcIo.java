package com.ab.test;

import org.apache.beam.runners.direct.DirectRunner;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.coders.*;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.io.jdbc.JdbcIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.Reshuffle;
import org.apache.beam.sdk.transforms.SimpleFunction;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.PCollectionList;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class BJdbcIo {

    public static void main(String[] arg){
        PipelineOptions options = PipelineOptionsFactory.create();
        options.setRunner(DirectRunner.class);
        // Then create the pipeline.
        Pipeline p = Pipeline.create(options);

        String jdbcDriver = "com.mysql.jdbc.Driver";
        String jdbcUrl = "jdbc:mysql://localhost:3306/test?useUnicode=true&amp;characterEncoding=utf8";
        String jdbcUser = "root";
        String jdbcPassword = "root";
        String querySql = "select * from a_spark_text";

        /*PCollection<KV<String, String>> lines1 = p.apply(
                "ReadLines", JdbcIO.<KV<String, String>>read().withDataSourceConfiguration(
                        JdbcIO.DataSourceConfiguration.create(jdbcDriver, jdbcUrl)
                                .withUsername(jdbcUser)
                                .withPassword(jdbcPassword))
                        .withQuery(querySql)
                        .withCoder(KvCoder.of(StringUtf8Coder.of(), StringUtf8Coder.of()))
                        .withRowMapper(new JdbcIO.RowMapper<KV<String, String>>() {
                            public KV<String, String> mapRow(ResultSet resultSet) throws Exception {
                                System.out.println(resultSet.getString(2));
                                return KV.of(resultSet.getString(2) , resultSet.getString(3));
                            }
                        })
        );*/

        /*PCollection<List<String>> lines2 = p.apply(
                "ReadLines", JdbcIO.<List<String>>read().withDataSourceConfiguration(
                        JdbcIO.DataSourceConfiguration.create(jdbcDriver, jdbcUrl)
                                .withUsername(jdbcUser)
                                .withPassword(jdbcPassword))
                        .withQuery(querySql)
                        .withCoder(ListCoder.of(StringUtf8Coder.of()))
                        .withRowMapper(new JdbcIO.RowMapper<List<String>>() {
                            public List<String> mapRow(ResultSet resultSet) throws Exception {
                                System.out.println(resultSet.getString(3));
                                List<String> list = new ArrayList<String>();
                                list.add(resultSet.getString(2));
                                return list;
                            }
                        })
        );*/

        PCollection<BJdbcBean> lines3 = p.apply(
                "ReadLines", JdbcIO.<BJdbcBean>read().withDataSourceConfiguration(
                        JdbcIO.DataSourceConfiguration.create(jdbcDriver, jdbcUrl)
                                .withUsername(jdbcUser)
                                .withPassword(jdbcPassword))
                        .withQuery(querySql)
                        .withCoder(AvroCoder.of(BJdbcBean.class))
                        .withRowMapper(new JdbcIO.RowMapper<BJdbcBean>() {
                            public BJdbcBean mapRow(ResultSet resultSet) throws Exception {
                                BJdbcBean bean = new BJdbcBean();
                                bean.setId(resultSet.getInt(1));
                                bean.setName(resultSet.getString(2));
                                bean.setCode(resultSet.getString(3));
                                bean.setOperateStatus(resultSet.getString(4));
                                System.out.println(bean.toString());
                                return bean;
                            }
                        })
        );

        lines3.apply("ConcatResultKVs", MapElements.via( // 拼接最后的格式化输出（Key为Word，Value为Count）
                new SimpleFunction<BJdbcBean, String>() {
                    @Override
                    public String apply(BJdbcBean input) {
                        return input.getId() + "," + input.getName() + "," + input.getCode() + "," + input.getOperateStatus();
                    }
                })).apply(TextIO.write().to("wpk").withShardNameTemplate("-001-mysql").withNumShards(1).withSuffix(".txt"));//txt|csv|json

        System.out.println(lines3.toString());
        p.run().waitUntilFinish();

    }

}
