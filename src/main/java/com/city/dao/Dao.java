package com.city.dao;

import com.city.Model.CityEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class Dao  {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<CityEvent> getAllCityEvents() {
        validDBConnection();
        return jdbcTemplate.query("" +
            ,
        new CityEventMapper());
    }

    private void validDBConnection() {
        List<Integer> ids = jdbcTemplate.queryForList("SELECT event_id FROM city_event LIMIT 1", Integer.class);
        if (ids == null || ids.isEmpty()) {
            throw new RuntimeException("Database is not connected!");
        }
    }
}


class CityEventMapper implements RowMapper<CityEvent> {
    @Override
    public CityEvent mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new CityEvent(
            rs.getString("date"),
            rs.getString("aday"),
            rs.getString("start_time"),
            rs.getString("end_time"),
            rs.getString("notes"),
            rs.getString("company"),
            rs.getString("event_name"),
            rs.getString("category"),
            rs.getString("page_url"),
            rs.getString("pic_url"),
            rs.getString("description"),
            rs.getString("age_lower"),
            rs.getString("age_upper"),
            rs.getString("price"),
            rs.getString("location"),
            rs.getString("address"),
            rs.getString("phone"),
            rs.getInt("event_id"),
            rs.getInt("company_id"),
            rowNum
        );
    }
}
