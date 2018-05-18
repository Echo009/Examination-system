package cn.ech0.examinationsystem.entity.admin;

import cn.ech0.examinationsystem.enums.exam.AuditStatusEnum;
import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/17/2018 12:23 PM
 */
@Entity
@Data
@Table(
        name = "eetu_audit_record",
        uniqueConstraints = {
                @UniqueConstraint(name = "idx_q", columnNames = {"userId", "questionId","commentId"})
        })
public class AuditRecordEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private Long questionId;

    private Long commentId;

    private Integer status = AuditStatusEnum.WAIT_FOR_AUDIT.getCode();

    private Date createTime = new Date();

    private Date auditPassTIme;
}
