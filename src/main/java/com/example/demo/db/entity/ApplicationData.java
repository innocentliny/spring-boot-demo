package com.example.demo.db.entity;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.example.demo.constant.ApplicationState;
import com.example.demo.constant.ApplicationType;
import com.example.demo.constant.FileType;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "application_data")
@Setter
@Getter
@ToString
public class ApplicationData
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    /**
     * Application type
     * 預設 column type 是 int，
     * 存的是 enum ordinal，
     * 好處是省 DB 空間，
     * 壞處是直接查 DB 會不知道對應哪個 enum，或許可額外建立 enum table 做 select 關聯。
     *
     * @see <a href="https://www.baeldung.com/jpa-persisting-enums-in-jpa">存 enum 至 DB</a>
     */
    @Column(nullable = false, updatable = false)
    private ApplicationType type;

    @Column(name = "applied_user_id", length = 250, nullable = false, updatable = false)
    private String appliedUserId;

    /**
     * 使用 {@code @Converter} 存 enum 的自訂短碼。
     *
     * @see <a href="https://www.baeldung.com/jpa-persisting-enums-in-jpa">存 enum 至 DB</a>
     */
    @Column(name = "file_type", updatable = false, length = 1)
    private FileType fileType;

    @Column(name = "src_file_id", nullable = false, updatable = false)
    private Long srcFileId;

    @Column(updatable = false)
    private String acl;

    /**
     * 存 enum name 到 DB，
     * 壞處是占用空間。
     */
    @Column(length = 20, nullable = false)
    @Enumerated(EnumType.STRING)
    private ApplicationState state;

    @Column(name = "created_dttm", nullable = false, insertable = false, updatable = false, columnDefinition = "timestamptz default CURRENT_TIMESTAMP")
    private LocalDateTime createdDttm;
}
