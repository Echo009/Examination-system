package cn.ech0.examinationsystem.entity.exam;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/29/2018 11:21 AM
 * 试题练习记录
 */

@Entity
@Data
@Table(
        name = "eetu_practice_record",
        uniqueConstraints = {
                @UniqueConstraint(name = "idx_q", columnNames = {"userId", "questionId"}),
        })
public class PracticeRecordEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private  Long questionId;

    private Integer category;

    private Integer useTimes = 1;

    private Integer errorTimes = 0;

    private Date lastUseTime = new Date();

}
