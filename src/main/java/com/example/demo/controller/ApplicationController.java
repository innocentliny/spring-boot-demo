package com.example.demo.controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.LongStream;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.constant.ApplicationState;
import com.example.demo.constant.ApplicationType;
import com.example.demo.constant.FileType;
import com.example.demo.db.entity.ApplicationData;
import com.example.demo.db.entity.RecursiveData;
import com.example.demo.db.model.ApplicationDetail;
import com.example.demo.db.model.ApplicationSummary;
import com.example.demo.dto.ApplicationDetailDto;
import com.example.demo.dto.ApplicationSummaryDto;
import com.example.demo.dto.ApplicationSummaryDtoV2;
import com.example.demo.dto.FileDto;
import com.example.demo.service.DaoService;

@RestController
@RequestMapping("app")
public class ApplicationController
{
    private DaoService daoService;

    public ApplicationController(DaoService daoService)
    {
        this.daoService = daoService;
    }

    @PostMapping(path = "v1")
    ResponseEntity<String> createApplication(@RequestBody(required = false) ApplicationDetailDto data)
    {
        if(data == null)
        {
            return ResponseEntity.badRequest().body("No request body.");
        }

        ApplicationData entity = new ApplicationData();
        entity.setType(data.getType());
        entity.setFileType(data.getFile()
                               .getFileType());
        entity.setSrcFileId(data.getFile().getSrcId());
        entity.setAppliedUserId(data.getAppliedUserId());
        entity.setAcl(data.getAcl());
        entity.setState(ApplicationState.WAIT_SIGN_OFF);
        daoService.createApplicationData(entity);
        return ResponseEntity.noContent().build();
    }

    @PostMapping(path = "v1/fakeData")
    ResponseEntity<Object> createFakeApplicationData()
    {
        LongStream.range(0, 10).forEach(i -> { //FIXME create in batch
            ApplicationData data = new ApplicationData();
            data.setType(i % 2 == 0 ? ApplicationType.PUB_DL : ApplicationType.PRI_DL);
            data.setAppliedUserId("user_0");
            data.setFileType(i % 2 == 0 ? FileType.PROJ : null);
            data.setSrcFileId(i);
            data.setState(i % 2 == 0 ? ApplicationState.WAIT_SIGN_OFF : ApplicationState.REJECTED);
            data.setAcl("detail acls");

            daoService.createApplicationData(data);
        });
        return ResponseEntity.noContent().build();
    }


    @GetMapping(path = "v1/{id}")
    ApplicationSummaryDto getSummaryByIdV1(@PathVariable long id)
    {
        return daoService.getSummaryByIdV1(id);
    }

    @GetMapping(path = "detail/v1/{id}")
    ResponseEntity<ApplicationDetailDto> getDetail(@PathVariable long id)
    {
        Optional<ApplicationDetail> optionalDetail = daoService.getDetailById(id);
        if(!optionalDetail.isPresent())
        {
            return ResponseEntity.notFound().build();
        }
        ApplicationDetail detail = optionalDetail.get();
        ApplicationDetailDto result = new ApplicationDetailDto();
        result.setId(detail.getId());
        result.setType(detail.getType());
        result.setAppliedUserId(detail.getAppliedUserId());
        result.setCreatedDttm(detail.getCreatedDttm());
        FileDto file = new FileDto();
        file.setFileType(detail.getFileType());
        file.setSrcId(detail.getSrcFileId());
        result.setFile(file);
        result.setAcl(detail.getAcl());

        return ResponseEntity.ok(result);
    }

    /**
     * @see <a href="https://www.baeldung.com/spring-date-parameters">spring-date-parameters</a>
     */
    @GetMapping(path = "/history/v1/{start}/{end}")
    ResponseEntity<List<ApplicationSummaryDtoV2>> getHistory(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime start,
                                                             @PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime end)
    {
        List<ApplicationSummary> summaries = daoService.getTop5SummariesOrderByCreatedDttmDesc(start, end);
        if(summaries.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        List<ApplicationSummaryDtoV2> results = new ArrayList<>();
        summaries.forEach(summary -> {
            ApplicationSummaryDtoV2 result = new ApplicationSummaryDtoV2();
            result.setId(summary.getId());
            result.setType(summary.getType());
            result.setAppliedUserId(summary.getAppliedUserId());
            result.setCreatedDttm(summary.getCreatedDttm());
            FileDto file = new FileDto();
            file.setFileType(summary.getFileType());
            file.setSrcId(summary.getSrcFileId());
            result.setFile(file);
            results.add(result);
        });

        return ResponseEntity.ok(results);
    }

    /**
     * 偷懶直接用 entity 回
     */
    @GetMapping(path = "recursive/v1/{id}")
    ResponseEntity<List<RecursiveData>> getAncestors(@PathVariable long id)
    {
        List<RecursiveData> ancestors = daoService.getAncestors(id);
        if(ancestors.isEmpty())
        {
            return ResponseEntity.notFound().build();
        }

        return ResponseEntity.ok(ancestors);
    }

    @PostMapping(path = "recursive/v1/fakeData")
    void saveFakeData()
    {
        List<RecursiveData> recursiveDataList = LongStream.range(0, 10).mapToObj(i -> {
            RecursiveData data = new RecursiveData();
            if(i != 0)
            {
                data.setParent(i);
            }
            return data;
        }).collect(Collectors.toList());
        daoService.saveAllRecursiveData(recursiveDataList);
    }
}
