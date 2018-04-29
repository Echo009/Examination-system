package cn.ech0.examinationsystem.entity.user;


import cn.ech0.examinationsystem.constant.Constant;
import cn.ech0.examinationsystem.enums.RoleCodeEnum;
import cn.ech0.examinationsystem.enums.UserSexEnum;
import lombok.Data;

import javax.persistence.*;

/**
 * Author : Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 03/16/2018 02:17 PM
 * <p>
 * 用户基础信息
 *
 * @author Ech0
 */
@Entity
@Data
@Table(uniqueConstraints = {
        @UniqueConstraint(name = "idx_id", columnNames = {"userId"}),
        @UniqueConstraint(name = "idx_name",columnNames = {"userName"}),
        @UniqueConstraint(name = "idx_phone",columnNames = {"phone"}),
})
public class UserBaseInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userId;

    private String userName;

    private String headImg = Constant.DEFAULT_HEAD_IMG_URL;

    private String email;

    private Long phone;

    private String password;

    private Integer sex = UserSexEnum.UNKNOW.getCode();

    private Integer roleCode = RoleCodeEnum.USER.getCode();

}