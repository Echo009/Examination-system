package cn.ech0.examinationsystem.entity;


import cn.ech0.examinationsystem.constant.Constant;
import cn.ech0.examinationsystem.enums.RoleCodeEnum;
import cn.ech0.examinationsystem.enums.UserSexEnum;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * Author : Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 03/16/2018 02:17 PM
 *
 *  用户基础信息
 * @author Ech0
 */
@Entity(name = "user_base_info")
@Data
public class UserBaseInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String userName;

    private String headImg= Constant.DEFAULT_HEAD_IMG_URL;

    private String email;

    private Long phone;

    private String password;

    private Integer sex = UserSexEnum.UNKNOW.getCode();

    private Integer roleCode = RoleCodeEnum.USER.getCode();

}
