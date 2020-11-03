package alex.disco.ball.dao.jpa;

import alex.disco.ball.entity.Category;
import alex.disco.ball.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;

public interface ProductRepoJpa extends JpaRepository<Product, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Product set name=:name where PRODUCT_ID=:ID")
    void updateName(@Param("ID") Long id, @Param("name") String name);
}
