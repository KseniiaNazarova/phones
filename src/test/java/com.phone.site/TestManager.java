package com.phone.site;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;


@Component
public class TestManager
{
    @Autowired
    private NamedParameterJdbcTemplate namedJt;

    @Autowired
    private JdbcTemplate jdbcTemplate;


    public void clear()
    {
        jdbcTemplate.execute("TRUNCATE testers CASCADE");
    }


    public void addTester(final Long testerId)
    {
        final String sql = "INSERT INTO testers (id, first_name, last_name, email) VALUES (:id, 'John', 'Smith', 'test@mail.com')";
        final MapSqlParameterSource map = new MapSqlParameterSource("id", testerId);
        namedJt.update(sql, map);
    }


    public boolean isPhoneAvailable(final Long phoneId)
    {
        final String sql = "SELECT is_available FROM phones WHERE id = :id";
        final MapSqlParameterSource map = new MapSqlParameterSource("id", phoneId);

        return namedJt.queryForObject(sql, map, Boolean.class);
    }
}
