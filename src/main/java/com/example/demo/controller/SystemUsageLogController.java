package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.service.DaoService;
import com.example.demo.db.entity.SystemUsageLog;

@RestController
@RequestMapping("log/mgr")
public class SystemUsageLogController
{
    private DaoService daoService;

    public SystemUsageLogController(@Autowired DaoService daoService)
    {
        this.daoService = daoService;
    }

    @GetMapping(path = "systemUsage")
    public List<SystemUsageLog> getAll(Pageable pageable)
    {
        return daoService.getAllSystemUsageLog(pageable);
    }

    @GetMapping("seqDesc")
    public List<SystemUsageLog> seqDesc()
    {
        return daoService.getAllSystemUsageLogSortedBySeqDesc();
    }

    @GetMapping("createdDttmDesc")
    public List<SystemUsageLog> createdDttmDesc()
    {
        return daoService.getAllSystemUsageLogSortedByCreatedDttmDesc();
    }
}
