package cn.ech0.examinationsystem.form.common;

import cn.ech0.examinationsystem.constant.Constant;
import cn.ech0.examinationsystem.enums.UserSexEnum;
import lombok.Data;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/24/2018 12:47 PM
 */
@Data
public class UserForm {

    @NotEmpty(message = "用户名不能为空 ！")
    private String userName;

    private String headImg = Constant.DEFAULT_HEAD_IMG_URL;
    @Email(message = "请输入有效的邮箱地址 ！")
    private String email;

    private Long phone;
    @NotEmpty(message = "用户密码不能为空 ！")
    private String password;

    private Integer sex = UserSexEnum.UNKNOW.getCode();

}
