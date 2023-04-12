package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Pageable;

import com.example.demo.db.entity.ApplicationData;
import com.example.demo.db.entity.RecursiveData;
import com.example.demo.db.entity.SystemUsageLog;
import com.example.demo.db.entity.Todo;
import com.example.demo.db.model.ApplicationDetail;
import com.example.demo.db.model.ApplicationSummary;
import com.example.demo.dto.ApplicationSummaryDto;
import com.example.demo.dto.TodoDto;

public interface DaoService
{
    List<Todo> getAllTodos();

    List<TodoDto> getAllAsDto();

    List<SystemUsageLog> getAllSystemUsageLog(Pageable pageable);

    List<SystemUsageLog> getAllSystemUsageLogSortedBySeqDesc();

    List<SystemUsageLog> getAllSystemUsageLogSortedByCreatedDttmDesc();

    void createApplicationData(ApplicationData data);
    ApplicationSummaryDto getSummaryByIdV1(long applicationId);

    Optional<ApplicationDetail> getDetailById(long applicationId);

    List<ApplicationSummary> getTop5SummariesOrderByCreatedDttmDesc(LocalDateTime start,
                                                                    LocalDateTime end);
    void saveAllRecursiveData(List<RecursiveData> data);

    List<RecursiveData> getAncestors(long id);
}
