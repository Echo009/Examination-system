package cn.ech0.examinationsystem.entity.user;

import lombok.Data;

import javax.persistence.*;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/17/2018 12:12 PM
 */
@Entity
@Data
@Table(
        name = "eetu_comment",
        uniqueConstraints = {
                @UniqueConstraint(name = "idx_q", columnNames = {"userId", "questionId","content"})
        })
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private Long questionId;

    private String content;

    private String imgs;
}
