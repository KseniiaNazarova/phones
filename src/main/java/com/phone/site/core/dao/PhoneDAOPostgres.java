package com.phone.site.core.dao;

import java.util.List;
import java.util.Optional;

import com.phone.site.core.dao.entity.ModelData;
import com.phone.site.core.dao.entity.PhoneData;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;


@Repository
public class PhoneDAOPostgres implements PhoneDAO
{
    private static final RowMapper<PhoneData> PHONE_ROW_MAPPER = (rs, i) -> PhoneData.builder()
            .withId(rs.getLong("id"))
            .withModelData(ModelData.builder()
                                   .withModel(rs.getString("model"))
                                   .withBrand(rs.getString("brand"))
                                   .build())
            .isAvailable(rs.getBoolean("is_available"))
            .build();

    private final NamedParameterJdbcTemplate namedJt;


    public PhoneDAOPostgres(final NamedParameterJdbcTemplate namedJt)
    {
        this.namedJt = namedJt;
    }


    @Override
    public Optional<PhoneData> getPhoneById(final Long id)
    {
        final String sql = "SELECT id, brand, model, is_available FROM phones WHERE id = :id";

        final MapSqlParameterSource map = new MapSqlParameterSource("id", id);

        return SqlUtils.getOptional(() -> namedJt.queryForObject(sql, map, PHONE_ROW_MAPPER));
    }


    @Override
    public List<PhoneData> getAllPhones()
    {
        final String sql = "SELECT id, brand, model, is_available FROM phones";
        return namedJt.query(sql, PHONE_ROW_MAPPER);
    }


    @Override
    public boolean bookPhone(final Long id)
    {
        final String sql = "UPDATE phones SET is_available = FALSE WHERE id = :id";
        final MapSqlParameterSource map = new MapSqlParameterSource("id", id);

        return 1 == namedJt.update(sql, map);
    }


    @Override
    public boolean returnPhone(final Long id)
    {
        final String sql = "UPDATE phones SET is_available = TRUE WHERE id = :id";
        final MapSqlParameterSource map = new MapSqlParameterSource("id", id);

        return 1 == namedJt.update(sql, map);
    }
}
