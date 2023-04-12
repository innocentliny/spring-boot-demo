package com.example.demo.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.db.entity.RecursiveData;

@Repository
public interface RecursiveDataRepository extends JpaRepository<RecursiveData, Long>
{
    @Query(value = "with recursive ancesstor(id, parent) as ( " +
                   "select id, parent from recursive_data where id = 5 " +
                   "union all " +
                   "    select ra.id, ra.parent from recursive_data ra, ancesstor where ra.id = ancesstor.parent " +
                   ") " +
                   "select * from ancesstor", nativeQuery = true)
    List<RecursiveData> getAncestors(long id);
}
