package com.ab.test;

import org.apache.beam.runners.flink.FlinkPipelineOptions;
import org.apache.beam.sdk.PipelineRunner;
import org.apache.beam.sdk.metrics.MetricsSink;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.transforms.display.DisplayData;
import org.apache.flink.runtime.state.StateBackend;
import org.apache.flink.streaming.api.CheckpointingMode;

import java.util.List;
import java.util.Map;

public class FlinkOptions implements FlinkPipelineOptions {
    @Override
    public List<String> getFilesToStage() {
        return null;
    }

    @Override
    public void setFilesToStage(List<String> value) {

    }

    @Override
    public String getFlinkMaster() {
        return null;
    }

    @Override
    public void setFlinkMaster(String value) {

    }

    @Override
    public Integer getParallelism() {
        return null;
    }

    @Override
    public void setParallelism(Integer value) {

    }

    @Override
    public Long getCheckpointingInterval() {
        return null;
    }

    @Override
    public void setCheckpointingInterval(Long interval) {

    }

    @Override
    public CheckpointingMode getCheckpointingMode() {
        return null;
    }

    @Override
    public void setCheckpointingMode(CheckpointingMode mode) {

    }

    @Override
    public Long getCheckpointTimeoutMillis() {
        return null;
    }

    @Override
    public void setCheckpointTimeoutMillis(Long checkpointTimeoutMillis) {

    }

    @Override
    public Long getMinPauseBetweenCheckpoints() {
        return null;
    }

    @Override
    public void setMinPauseBetweenCheckpoints(Long minPauseInterval) {

    }

    @Override
    public Integer getNumberOfExecutionRetries() {
        return null;
    }

    @Override
    public void setNumberOfExecutionRetries(Integer retries) {

    }

    @Override
    public Long getExecutionRetryDelay() {
        return null;
    }

    @Override
    public void setExecutionRetryDelay(Long delay) {

    }

    @Override
    public Boolean getObjectReuse() {
        return null;
    }

    @Override
    public void setObjectReuse(Boolean reuse) {

    }

    @Override
    public StateBackend getStateBackend() {
        return null;
    }

    @Override
    public void setStateBackend(StateBackend stateBackend) {

    }

    @Override
    public Boolean getEnableMetrics() {
        return null;
    }

    @Override
    public void setEnableMetrics(Boolean enableMetrics) {

    }

    @Override
    public Boolean isExternalizedCheckpointsEnabled() {
        return null;
    }

    @Override
    public void setExternalizedCheckpointsEnabled(Boolean externalCheckpoints) {

    }

    @Override
    public Boolean getRetainExternalizedCheckpointsOnCancellation() {
        return null;
    }

    @Override
    public void setRetainExternalizedCheckpointsOnCancellation(Boolean retainOnCancellation) {

    }

    @Override
    public Long getMaxBundleSize() {
        return null;
    }

    @Override
    public void setMaxBundleSize(Long size) {

    }

    @Override
    public Long getMaxBundleTimeMills() {
        return null;
    }

    @Override
    public void setMaxBundleTimeMills(Long time) {

    }

    @Override
    public Boolean isShutdownSourcesOnFinalWatermark() {
        return null;
    }

    @Override
    public void setShutdownSourcesOnFinalWatermark(Boolean shutdownOnFinalWatermark) {

    }

    @Override
    public Long getLatencyTrackingInterval() {
        return null;
    }

    @Override
    public void setLatencyTrackingInterval(Long interval) {

    }

    @Override
    public boolean isStreaming() {
        return false;
    }

    @Override
    public void setStreaming(boolean value) {

    }

    @Override
    public String getAppName() {
        return null;
    }

    @Override
    public void setAppName(String value) {

    }

    @Override
    public <T extends PipelineOptions> T as(Class<T> kls) {
        return null;
    }

    @Override
    public Class<? extends PipelineRunner<?>> getRunner() {
        return null;
    }

    @Override
    public void setRunner(Class<? extends PipelineRunner<?>> kls) {

    }

    @Override
    public CheckEnabled getStableUniqueNames() {
        return null;
    }

    @Override
    public void setStableUniqueNames(CheckEnabled enabled) {

    }

    @Override
    public String getTempLocation() {
        return null;
    }

    @Override
    public void setTempLocation(String value) {

    }

    @Override
    public String getJobName() {
        return null;
    }

    @Override
    public void setJobName(String jobName) {

    }

    @Override
    public Map<String, Map<String, Object>> outputRuntimeOptions() {
        return null;
    }

    @Override
    public long getOptionsId() {
        return 0;
    }

    @Override
    public void setOptionsId(long id) {

    }

    @Override
    public String getUserAgent() {
        return null;
    }

    @Override
    public void setUserAgent(String userAgent) {

    }

    @Override
    public Class<? extends MetricsSink> getMetricsSink() {
        return null;
    }

    @Override
    public void setMetricsSink(Class<? extends MetricsSink> metricsSink) {

    }

    @Override
    public Long getMetricsPushPeriod() {
        return null;
    }

    @Override
    public void setMetricsPushPeriod(Long period) {

    }

    @Override
    public String getMetricsHttpSinkUrl() {
        return null;
    }

    @Override
    public void setMetricsHttpSinkUrl(String metricsSink) {

    }

    @Override
    public void populateDisplayData(DisplayData.Builder builder) {

    }
}
