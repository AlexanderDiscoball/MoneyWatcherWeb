package alex.disco.ball.dao.jdbc;

import alex.disco.ball.dao.common.ProductRepo;
import alex.disco.ball.dao.common.ProductRowMapper;
import alex.disco.ball.entity.Product;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public class JdbcProductRepo extends JdbcAbstractRepo<Product> implements ProductRepo {

    protected RowMapper<Product> rowMapper = new ProductRowMapper();
    private static final String[] SPECIAL_CHARS = new String[]{"$", "#", "&", "%"};

    @Autowired
    public JdbcProductRepo(NamedParameterJdbcTemplate jdbcNamedTemplate) {
        super(jdbcNamedTemplate);
    }

    @Override
    public Long count() {
        String sql = "select count(*) from PRODUCT";
        return jdbcNamedTemplate.queryForObject(sql, new HashMap<>(), Long.class);
    }

    @Override
    public Set<Product> findAll() {
        String sql = "select PRODUCT_ID, NAME, CATEGORY, PRICE, CREATED_AT, MODIFIED_AT from product";
        return new HashSet<>(jdbcNamedTemplate.query(sql, rowMapper));
    }

    @Override
    public boolean save(Product product) {
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("ID",product.getId())
                .addValue("NAME", product.getName())
                .addValue("CATEGORY", product.getCategory().toString())
                .addValue("PRICE", product.getPrice())
                .addValue("CREATED_AT", product.getCreatedAt())
                .addValue("MODIFIED_AT", product.getModifiedAt());
        int status = jdbcNamedTemplate.update(
                "insert into PRODUCT (PRODUCT_ID, NAME, CATEGORY, PRICE, CREATED_AT, MODIFIED_AT) values(:ID, :NAME, :CATEGORY, :PRICE, :CREATED_AT, :MODIFIED_AT)",
                namedParameters);
        return status == 1;
    }

    @Override
    public boolean delete(Product entity) {
        String sql = "delete from PRODUCT where PRODUCT_ID = :id";
        int status = jdbcNamedTemplate.update(sql, Map.of("id", entity.getId()));
        return status == 1;
    }

    @Override
    public boolean update(Product product) {
        if (StringUtils.indexOfAny(product.getName(), SPECIAL_CHARS) != -1 ||
                StringUtils.indexOfAny(product.getName(), SPECIAL_CHARS) != -1) {
            throw new IllegalArgumentException("Text contains weird characters!");
        }
        SqlParameterSource namedParameters = new MapSqlParameterSource()
                .addValue("ID", product.getId())
                .addValue("NAME", product.getName())
                .addValue("CATEGORY", product.getCategory().toString())
                .addValue("PRICE", product.getPrice())
                .addValue("CREATED_AT", product.getCreatedAt())
                .addValue("MODIFIED_AT", product.getModifiedAt());
        int status = jdbcNamedTemplate.update("update PRODUCT set NAME=:NAME, CATEGORY=:CATEGORY, PRICE=:PRICE, CREATED_AT=:CREATED_AT, MODIFIED_AT=:MODIFIED_AT where PRODUCT_ID=:ID",
                namedParameters);
        return status == 1;
    }

    @Override
    public boolean deleteById(Long entityId) {
        String sql = "delete from PRODUCT where PRODUCT_ID = :id";
        int status = jdbcNamedTemplate.update(sql, Map.of("id",entityId));
        return status == 1;
    }

    @Override
    public Optional<Product> findById(Long id) {
        String sql = "select PRODUCT_ID, NAME, CATEGORY, PRICE, CREATED_AT, MODIFIED_AT from PRODUCT where PRODUCT_ID=:ID";
        Optional<Product> product;
        try{
            product = Optional.of(jdbcNamedTemplate.queryForObject(sql, Map.of("ID", id), rowMapper));
        }
        catch (EmptyResultDataAccessException e){
            product = Optional.empty();
        }
        return product;
    }
}
