package alex.disco.ball.dao.jpa;

import alex.disco.ball.entity.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface PersonRepoJpa extends JpaRepository<Person, Long> {

    @Modifying(clearAutomatically = true)
    @Query("update Person set first_name=:name where PERSON_ID=:ID")
    void updateFirstName(@Param("ID") Long id, @Param("name") String firstNAme);
}
