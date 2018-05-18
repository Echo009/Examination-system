package cn.ech0.examinationsystem.entity.user;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/17/2018 12:07 PM
 */
@Entity
@Data
@Table(
        name = "eetu_favorite",
        uniqueConstraints = {
                @UniqueConstraint(name = "idx_q", columnNames = {"userId", "questionId"})
        })
public class FavoriteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private Long questionId;

    private Integer category;

    private Integer type;

    private String title;

    private Date createTime;


}
