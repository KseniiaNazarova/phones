package com.phone.site.core.dao;

import java.util.List;

import com.phone.site.core.dao.entity.ModelData;
import com.phone.site.core.dao.entity.TesterData;
import com.phone.site.core.dao.entity.PhoneHistoryData;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class PhoneHistoryDAOPostgres implements PhoneHistoryDAO
{
    private static final RowMapper<PhoneHistoryData> HISTORY_ROW_MAPPER = (rs, i) -> PhoneHistoryData.builder()
            .withPhoneId(rs.getLong("phone_id"))
            .withModelData(ModelData.builder()
                                   .withModel(rs.getString("model"))
                                   .withBrand(rs.getString("brand"))
                                   .build())
            .withTester(TesterData.builder()
                                .withId(rs.getLong("tester_id"))
                                .withFirstName(rs.getString("first_name"))
                                .withLastName(rs.getString("last_name"))
                                .withEmail(rs.getString("email"))
                                .build())
            .withBookedOn(rs.getTimestamp("booked_on"))
            .withReturnedOn(rs.getTimestamp("returned_on"))
            .build();

    private final NamedParameterJdbcTemplate namedJt;


    public PhoneHistoryDAOPostgres(final NamedParameterJdbcTemplate namedJt)
    {
        this.namedJt = namedJt;
    }


    @Override
    public List<PhoneHistoryData> getHistoryByPhoneId(final Long phoneId)
    {
        final String sql = "SELECT phone_id," +
                "   brand, " +
                "   model, " +
                "   tester_id, " +
                "   first_name, " +
                "   last_name, " +
                "   email, " +
                "   booked_on, " +
                "   returned_on FROM history " +
                "JOIN phones p ON history.phone_id = p.id " +
                "JOIN testers t ON history.tester_id = t.id " +
                "WHERE phone_id = :phone_id";

        final MapSqlParameterSource map = new MapSqlParameterSource("phone_id", phoneId);

        return namedJt.query(sql, map, HISTORY_ROW_MAPPER);
    }


    @Override
    public boolean addReturnHistory(final Long phoneId)
    {
        final String sql = "UPDATE history SET returned_on = now() WHERE phone_id = :phone_id";
        final MapSqlParameterSource map = new MapSqlParameterSource("phone_id", phoneId);

        return 1 == namedJt.update(sql, map);
    }


    @Override
    public boolean addBookHistory(final Long phoneId, final Long testerId)
    {
        final String sql = "INSERT INTO history (phone_id, tester_id, booked_on) " +
                "VALUES (:phone_id, :tester_id, now())";

        final MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("phone_id", phoneId)
                .addValue("tester_id", testerId);

        return 1 == namedJt.update(sql, map);
    }
}
