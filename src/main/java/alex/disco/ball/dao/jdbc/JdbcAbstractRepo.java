package alex.disco.ball.dao.jdbc;

import alex.disco.ball.entity.AbstractEntity;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

public class JdbcAbstractRepo<T extends AbstractEntity> {

    protected NamedParameterJdbcTemplate jdbcNamedTemplate;

    public JdbcAbstractRepo(NamedParameterJdbcTemplate jdbcNamedTemplate) {
        this.jdbcNamedTemplate = jdbcNamedTemplate;
    }



}
