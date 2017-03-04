package com.powerhouse.interview.entity;

import java.util.List;

public class Response {

	private List<String> recordedProfileNames;
	private List<Integer> recordedMeterIds;
	private List<String> meterReadingsViolations;
	private Integer meterId;
	private Month month;
	private Double consumption;

	public Integer getMeterId() {
		return meterId;
	}
	public void setMeterId(Integer meterId) {
		this.meterId = meterId;
	}
	public Month getMonth() {
		return month;
	}
	public void setMonth(Month month) {
		this.month = month;
	}
	public Double getConsumption() {
		return consumption;
	}
	public void setConsumption(Double consumption) {
		this.consumption = consumption;
	}
	public List<String> getRecordedProfileNames() {
		return recordedProfileNames;
	}
	public void setRecordedProfileNames(List<String> recordedProfileNames) {
		this.recordedProfileNames = recordedProfileNames;
	}
	public List<Integer> getRecordedMeterIds() {
		return recordedMeterIds;
	}
	public void setRecordedMeterIds(List<Integer> recordedMeterIds) {
		this.recordedMeterIds = recordedMeterIds;
	}
	public List<String> getMeterReadingsViolations() {
		return meterReadingsViolations;
	}
	public void setMeterReadingsViolations(List<String> meterReadingsViolations) {
		this.meterReadingsViolations = meterReadingsViolations;
	}

}
