package com.powerhouse.interview.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.powerhouse.interview.entity.MeterReading;
import com.powerhouse.interview.entity.Month;

@Repository
public class MeterRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public MeterRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public List<MeterReading> findOne(Integer meterID) {
		return jdbc
				.query("select id, profile_name, january, february, march, april, may, june, july, august, september, october, november, december "
						+ "from METER where id = ?", new Integer[] { meterID }, new RowMapper<MeterReading>() {
							public MeterReading mapRow(ResultSet rs, int rowNum) throws SQLException {
								MeterReading meterReading = new MeterReading(rs.getInt(1), rs.getString(2));
								meterReading.addReading(Month.JANUARY, rs.getInt(3));
								meterReading.addReading(Month.FEBRUARY, rs.getInt(4));
								meterReading.addReading(Month.MARCH, rs.getInt(5));
								meterReading.addReading(Month.APRIL, rs.getInt(6));
								meterReading.addReading(Month.MAY, rs.getInt(7));
								meterReading.addReading(Month.JUNE, rs.getInt(8));
								meterReading.addReading(Month.JULY, rs.getInt(9));
								meterReading.addReading(Month.AUGUST, rs.getInt(10));
								meterReading.addReading(Month.SEPTEMBER, rs.getInt(11));
								meterReading.addReading(Month.OCTOBER, rs.getInt(12));
								meterReading.addReading(Month.NOVEMBER, rs.getInt(13));
								meterReading.addReading(Month.DECEMBER, rs.getInt(14));
								return meterReading;
							}
						});
	}

	public void save(MeterReading meterReading) {
		Map<Month, Integer> meterReadingMap = meterReading.getMeterReadingMap();
	
		if(findOne(meterReading.getMeterID()).isEmpty()) {
			jdbc.update(
					"insert into METER " + "(id, profile_name, january, february, march, april, may, june, july, august, september, october, november, december) " + 
			"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
			meterReading.getMeterID(), 
			meterReading.getProfileName(),
			meterReadingMap.get(Month.JANUARY),
			meterReadingMap.get(Month.FEBRUARY),
			meterReadingMap.get(Month.MARCH),
			meterReadingMap.get(Month.APRIL),
			meterReadingMap.get(Month.MAY),
			meterReadingMap.get(Month.JUNE),
			meterReadingMap.get(Month.JULY),
			meterReadingMap.get(Month.AUGUST),
			meterReadingMap.get(Month.SEPTEMBER),
			meterReadingMap.get(Month.OCTOBER),
			meterReadingMap.get(Month.NOVEMBER),
			meterReadingMap.get(Month.DECEMBER));
		} else {
			jdbc.update(
					"update METER SET january = ?, february = ?, march = ?, april = ?, may = ?, june = ?, july = ?, august = ?, september = ?, october = ?, november = ?, december = ?, profile_name = ?" + 
			"where id = ? ", 
			meterReadingMap.get(Month.JANUARY),
			meterReadingMap.get(Month.FEBRUARY),
			meterReadingMap.get(Month.MARCH),
			meterReadingMap.get(Month.APRIL),
			meterReadingMap.get(Month.MAY),
			meterReadingMap.get(Month.JUNE),
			meterReadingMap.get(Month.JULY),
			meterReadingMap.get(Month.AUGUST),
			meterReadingMap.get(Month.SEPTEMBER),
			meterReadingMap.get(Month.OCTOBER),
			meterReadingMap.get(Month.NOVEMBER),
			meterReadingMap.get(Month.DECEMBER),
			meterReading.getProfileName(),
			meterReading.getMeterID());
		}
	}

	public List<MeterReading> fetchMeters() {
		return jdbc
				.query("select id, profile_name, january, february, march, april, may, june, july, august, september, october, november, december "
						+ "from METER", new RowMapper<MeterReading>() {
							public MeterReading mapRow(ResultSet rs, int rowNum) throws SQLException {
								MeterReading meterReading = new MeterReading(rs.getInt(1), rs.getString(2));
								meterReading.addReading(Month.JANUARY, rs.getInt(3));
								meterReading.addReading(Month.FEBRUARY, rs.getInt(4));
								meterReading.addReading(Month.MARCH, rs.getInt(5));
								meterReading.addReading(Month.APRIL, rs.getInt(6));
								meterReading.addReading(Month.MAY, rs.getInt(7));
								meterReading.addReading(Month.JUNE, rs.getInt(8));
								meterReading.addReading(Month.JULY, rs.getInt(9));
								meterReading.addReading(Month.AUGUST, rs.getInt(10));
								meterReading.addReading(Month.SEPTEMBER, rs.getInt(11));
								meterReading.addReading(Month.OCTOBER, rs.getInt(12));
								meterReading.addReading(Month.NOVEMBER, rs.getInt(13));
								meterReading.addReading(Month.DECEMBER, rs.getInt(14));
								return meterReading;
							}
						});
	}

}