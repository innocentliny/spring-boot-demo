package com.example.demo.db.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "system_usage_log")
@Getter
@Setter
@ToString
public class SystemUsageLog
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long seq;

    private String type; //TODO should use enum

    @Column(name = "account_id")
    private Long accountId;

    @Column(name = "created_dttm")
    private Date createdDttm;
}
