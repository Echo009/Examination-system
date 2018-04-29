package cn.ech0.examinationsystem.service.common;


import cn.ech0.examinationsystem.dto.ResultDTO;
import cn.ech0.examinationsystem.dto.UserBaseInfoDTO;
import cn.ech0.examinationsystem.entity.user.UserBaseInfoEntity;

/**
 * Author : Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 03/16/2018 02:29 PM
 * @author Ech0
 */
public interface IUserBaseInfoService {

    /**
     * 注册用户
     * @param userBaseInfoEntity
     * @return
     */
    boolean register(UserBaseInfoEntity userBaseInfoEntity);

    /**
     * 校验用户信息
     * @param userBaseInfoDTO
     * @return
     */
    ResultDTO<UserBaseInfoEntity> check(UserBaseInfoDTO userBaseInfoDTO);

    /**
     * 校验用户名是否存在
     * @param userName
     * @return
     */
    boolean checkUserName(String userName);

    /**
     * 校验手机号码是否已经被注册
     * @param phone
     * @return
     */
    boolean checkPhone(Long phone);

    /**
     * 查询用户信息
     * @param id
     * @return
     */
    UserBaseInfoEntity findUserInfoById(Long id);

    UserBaseInfoEntity updateUserInfo(UserBaseInfoEntity userBaseInfoEntity);
}
