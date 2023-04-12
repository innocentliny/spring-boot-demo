package com.example.demo.db.model;

import java.time.LocalDateTime;

import com.example.demo.constant.ApplicationState;
import com.example.demo.constant.ApplicationType;
import com.example.demo.constant.FileType;

/**
 * @see <a href="https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#projections">Projections</a>
 */
public interface ApplicationSummary
{
    Long getId();
    ApplicationType getType();
    String getAppliedUserId();
    FileType getFileType();
    Long getSrcFileId();
    ApplicationState getState();
    LocalDateTime getCreatedDttm();
}
