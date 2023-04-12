package com.example.demo.db.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.db.entity.SystemUsageLog;

@Repository
public interface SystemUsageLogRepository extends JpaRepository<SystemUsageLog, Long>
{
    List<SystemUsageLog> findByOrderBySeqDesc();
    List<SystemUsageLog> findByOrderByCreatedDttmDesc();
}
