package com.persistence.dao.repositories;

import com.model.Card;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface CardRepository extends CrudRepository<Card, Long> {

    List<Card> findCardByType(String type);

    List<Card> findCardByYearAndSent(Integer year, Boolean sent);

    @Modifying
    @Query("UPDATE Card e SET e.type = :type WHERE e.author = :author AND e.year = :year")
    int updateCardTypeByAuthorAndYear(
            @Param("type") String type,
            @Param("author") String author,
            @Param("year") Integer year);

    @Modifying

    @Query(value = "DELETE FROM card WHERE id = :id", nativeQuery = true)
    void deleteCardById(@Param("id") Long id);

}
