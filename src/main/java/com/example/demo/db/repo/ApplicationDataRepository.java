package com.example.demo.db.repo;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.db.entity.ApplicationData;
import com.example.demo.dto.ApplicationSummaryDto;

@Repository
public interface ApplicationDataRepository extends JpaRepository<ApplicationData, Long>
{
    /**
     * @see <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query.spel-expressions">SpEL</a>
     */
    @Query("select new com.example.demo.dto.ApplicationSummaryDto(id, type, srcFileId, createdDttm) from #{#entityName} where id = :id")
    ApplicationSummaryDto getSummaryByIdV1(long id);

    /**
     * @see <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projection.dynamic">Dynamic Projections</a>
     */
    <T> Optional<T> getById(long id, Class<T> clazz);

    /**
     * @see <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projection.dynamic">Dynamic Projections</a>
     */
    <T> List<T> getByCreatedDttmBetween(LocalDateTime start, LocalDateTime end, Class<T> clazz, PageRequest pageRequest);
}
