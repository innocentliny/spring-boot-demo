package com.example.demo.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.db.entity.ApplicationData;
import com.example.demo.db.entity.RecursiveData;
import com.example.demo.db.entity.SystemUsageLog;
import com.example.demo.db.entity.Todo;
import com.example.demo.db.model.ApplicationDetail;
import com.example.demo.db.model.ApplicationSummary;
import com.example.demo.db.repo.ApplicationDataRepository;
import com.example.demo.db.repo.RecursiveDataRepository;
import com.example.demo.db.repo.SystemUsageLogRepository;
import com.example.demo.db.repo.TodoDao;
import com.example.demo.dto.ApplicationSummaryDto;
import com.example.demo.dto.TodoDto;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@AllArgsConstructor
public class DaoServiceImpl implements DaoService
{
    private TodoDao todoDao;

    private SystemUsageLogRepository systemUsageLogRepo;
    private ApplicationDataRepository applicationDataRepo;
    private RecursiveDataRepository recursiveDataRepo;

//    @Autowired
//    public DaoServiceImpl(TodoDao todoDao, SystemUsageLogRepository systemUsageLogRepo, ApplicationDataRepository applicationDataRepo)
//    {
//        this.todoDao = todoDao;
//        this.systemUsageLogRepo = systemUsageLogRepo;
//        this.applicationDataRepo = applicationDataRepo;
//    }

    @Override
    public List<Todo> getAllTodos()
    {
        return todoDao.findAll();
    }

    @Override
    public List<TodoDto> getAllAsDto()
    {
        return todoDao.findAllAsDto();
    }

    /*
        Pageable example: https://...?page=0&size=50&sort=accountId,desc&createdDttm,desc
     */
    @Override
    public List<SystemUsageLog> getAllSystemUsageLog(Pageable pageable)
    {
        return systemUsageLogRepo.findAll(pageable).toList(); //FIXME should sort by created_dttm
    }

    @Override
    public List<SystemUsageLog> getAllSystemUsageLogSortedBySeqDesc()
    {
        return systemUsageLogRepo.findByOrderBySeqDesc();
    }

    @Override
    public List<SystemUsageLog> getAllSystemUsageLogSortedByCreatedDttmDesc()
    {
        return systemUsageLogRepo.findByOrderByCreatedDttmDesc();
    }

    @Override
    public void createApplicationData(ApplicationData data)
    {
        applicationDataRepo.save(data);
    }

    @Override
    public ApplicationSummaryDto getSummaryByIdV1(long applicationId)
    {
        return applicationDataRepo.getSummaryByIdV1(applicationId);
    }

    @Override
    public Optional<ApplicationDetail> getDetailById(long applicationId)
    {
        return applicationDataRepo.getById(applicationId, ApplicationDetail.class);
    }

    @Override
    public List<ApplicationSummary> getTop5SummariesOrderByCreatedDttmDesc(LocalDateTime start,
                                                                           LocalDateTime end)
    {
        return applicationDataRepo.getByCreatedDttmBetween(start, end, ApplicationSummary.class, PageRequest.of(0, 5, Sort.by(Sort.Order.desc("CreatedDttm"))));
    }

    public void createApplication(ApplicationData data)
    {
        ApplicationData saved = applicationDataRepo.save(data);
        log.info("Saved application ID={}", saved.getId());
    }

    @Override
    @Transactional(timeout = 3)
    public void saveAllRecursiveData(List<RecursiveData> data)
    {
        recursiveDataRepo.saveAll(data);
    }

    @Override
    public List<RecursiveData> getAncestors(long id)
    {
        return recursiveDataRepo.getAncestors(id);
    }
}
