package com.powerhouse.interview.entity;

import java.util.List;

public class Response {

	private List<String> recordedProfileNames;
	private List<Integer> recordedMeterIds;
	private List<String> meterReadingsViolations;

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
