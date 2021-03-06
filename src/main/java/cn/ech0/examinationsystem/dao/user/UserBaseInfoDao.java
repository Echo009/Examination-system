package cn.ech0.examinationsystem.dao.user;


import cn.ech0.examinationsystem.entity.user.UserBaseInfoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Author : Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 03/16/2018 02:24 PM
 *
 * @author Ech0
 */

public interface UserBaseInfoDao extends JpaRepository<UserBaseInfoEntity, Integer> {

    UserBaseInfoEntity findByUserName(String userName);

    UserBaseInfoEntity findByUserId(Long id);

    UserBaseInfoEntity findAllByPhone(Long phone);

    UserBaseInfoEntity findByUserNameAndPassword(String username, String password);

    UserBaseInfoEntity findByPhoneEqualsAndPassword(Long phone, String password);

}
