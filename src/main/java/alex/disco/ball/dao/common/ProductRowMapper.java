package alex.disco.ball.dao.common;

import alex.disco.ball.entity.Category;
import alex.disco.ball.entity.Product;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class ProductRowMapper implements RowMapper<Product> {

    @Override
    public Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Long id = rs.getLong("PRODUCT_ID");
        String name = rs.getString("NAME");
        String categoryStr = rs.getString("CATEGORY");
        Category category = Category.getCategory(categoryStr);
        Integer price = rs.getInt("PRICE");
        LocalDate created_at = rs.getTimestamp("CREATED_AT").toLocalDateTime().toLocalDate();
        LocalDate modified_at = rs.getTimestamp("MODIFIED_AT").toLocalDateTime().toLocalDate();


        return new Product(id,name,category,price,created_at,modified_at);
    }

}
