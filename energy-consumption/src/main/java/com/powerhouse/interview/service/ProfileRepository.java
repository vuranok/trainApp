package com.powerhouse.interview.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.powerhouse.interview.entity.Month;
import com.powerhouse.interview.entity.Profile;

@Repository
public class ProfileRepository {

	private JdbcTemplate jdbc;

	@Autowired
	public ProfileRepository(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}

	public List<Profile> findOne(String name) {
		return jdbc
				.query("select name, january, february, march, april, may, june, july, august, september, october, november, december "
						+ "from PROFILE where name = ?", new String[] { name }, new RowMapper<Profile>() {
							public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
								Profile profile = new Profile(rs.getString(1));
								profile.addFraction(Month.JANUARY, rs.getDouble(2));
								profile.addFraction(Month.FEBRUARY, rs.getDouble(3));
								profile.addFraction(Month.MARCH, rs.getDouble(4));
								profile.addFraction(Month.APRIL, rs.getDouble(5));
								profile.addFraction(Month.MAY, rs.getDouble(6));
								profile.addFraction(Month.JUNE, rs.getDouble(7));
								profile.addFraction(Month.JULY, rs.getDouble(8));
								profile.addFraction(Month.AUGUST, rs.getDouble(9));
								profile.addFraction(Month.SEPTEMBER, rs.getDouble(10));
								profile.addFraction(Month.OCTOBER, rs.getDouble(11));
								profile.addFraction(Month.NOVEMBER, rs.getDouble(12));
								profile.addFraction(Month.DECEMBER, rs.getDouble(13));
								return profile;
							}
						});
	}

	public void save(Profile profile) {
		Map<Month, Double> fractionMap = profile.getFractionMap();
	
		if(findOne(profile.getName()).isEmpty()) {
			jdbc.update(
					"insert into PROFILE " + "(name, january, february, march, april, may, june, july, august, september, october, november, december) " + 
			"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
			profile.getName(), 
			fractionMap.get(Month.JANUARY),
			fractionMap.get(Month.FEBRUARY),
			fractionMap.get(Month.MARCH),
			fractionMap.get(Month.APRIL),
			fractionMap.get(Month.MAY),
			fractionMap.get(Month.JUNE),
			fractionMap.get(Month.JULY),
			fractionMap.get(Month.AUGUST),
			fractionMap.get(Month.SEPTEMBER),
			fractionMap.get(Month.OCTOBER),
			fractionMap.get(Month.NOVEMBER),
			fractionMap.get(Month.DECEMBER));
		} else {
			jdbc.update(
					"update PROFILE SET january = ?, february = ?, march = ?, april = ?, may = ?, june = ?, july = ?, august = ?, september = ?, october = ?, november = ?, december = ? " + 
			"where name = ? ", 
			fractionMap.get(Month.JANUARY),
			fractionMap.get(Month.FEBRUARY),
			fractionMap.get(Month.MARCH),
			fractionMap.get(Month.APRIL),
			fractionMap.get(Month.MAY),
			fractionMap.get(Month.JUNE),
			fractionMap.get(Month.JULY),
			fractionMap.get(Month.AUGUST),
			fractionMap.get(Month.SEPTEMBER),
			fractionMap.get(Month.OCTOBER),
			fractionMap.get(Month.NOVEMBER),
			fractionMap.get(Month.DECEMBER), 
			profile.getName());
		}
	}

	public List<Profile> fetchProfiles() {
		return jdbc
				.query("select name, january, february, march, april, may, june, july, august, september, october, november, december "
						+ "from PROFILE", new RowMapper<Profile>() {
							public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
								Profile profile = new Profile(rs.getString(1));
								profile.addFraction(Month.JANUARY, rs.getDouble(2));
								profile.addFraction(Month.FEBRUARY, rs.getDouble(3));
								profile.addFraction(Month.MARCH, rs.getDouble(4));
								profile.addFraction(Month.APRIL, rs.getDouble(5));
								profile.addFraction(Month.MAY, rs.getDouble(6));
								profile.addFraction(Month.JUNE, rs.getDouble(7));
								profile.addFraction(Month.JULY, rs.getDouble(8));
								profile.addFraction(Month.AUGUST, rs.getDouble(9));
								profile.addFraction(Month.SEPTEMBER, rs.getDouble(10));
								profile.addFraction(Month.OCTOBER, rs.getDouble(11));
								profile.addFraction(Month.NOVEMBER, rs.getDouble(12));
								profile.addFraction(Month.DECEMBER, rs.getDouble(13));
								return profile;
							}
						});
	}

}
