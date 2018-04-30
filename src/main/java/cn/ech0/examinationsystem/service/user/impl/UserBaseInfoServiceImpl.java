package cn.ech0.examinationsystem.service.user.impl;


import cn.ech0.examinationsystem.dto.ResultDTO;
import cn.ech0.examinationsystem.dao.user.UserBaseInfoDao;
import cn.ech0.examinationsystem.dto.UserBaseInfoDTO;
import cn.ech0.examinationsystem.entity.user.UserBaseInfoEntity;
import cn.ech0.examinationsystem.service.user.IUserBaseInfoService;
import cn.ech0.examinationsystem.util.Encrypter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static cn.ech0.examinationsystem.util.JsonUtil.toJson;

/**
 * Author : Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 03/16/2018 02:58 PM
 * @author Ech0
 */
@Service
@Slf4j
public class UserBaseInfoServiceImpl implements IUserBaseInfoService {

    @Autowired
    private UserBaseInfoDao userBaseInfoDao;

    /**
     * 注册用户
     *
     * @param userBaseInfoEntity
     * @return
     */
    @Override
    public boolean register(UserBaseInfoEntity userBaseInfoEntity) {

        return !(userBaseInfoDao.save(userBaseInfoEntity) == null);

    }

    /**
     * 校验用户信息
     *
     * @param userBaseInfoDTO
     * @return
     */
    @Override
    public ResultDTO<UserBaseInfoEntity> check(UserBaseInfoDTO userBaseInfoDTO) {

        userBaseInfoDTO.setPassword(Encrypter.md5(userBaseInfoDTO.getPassword()));

        UserBaseInfoEntity currentUser;
        if (userBaseInfoDTO.getPhone() != null) {
            currentUser = userBaseInfoDao.findByPhoneEqualsAndPassword(
                    userBaseInfoDTO.getPhone(),
                    userBaseInfoDTO.getPassword());
            if (currentUser == null) {
                return ResultDTO.BAD_RESULT;
            } else {
                return new ResultDTO<>(true, currentUser);
            }
        } else {
            currentUser = userBaseInfoDao.findByUserNameAndPassword(userBaseInfoDTO.getUserName(), userBaseInfoDTO.getPassword());
            if (currentUser == null) {
                return ResultDTO.BAD_RESULT;
            } else {
                log.info("current user is : {}", toJson(currentUser,true));
                return new ResultDTO<>(true, currentUser);
            }
        }
    }

    /**
     * 校验用户名是否存在
     *
     * @param userName
     * @return true 如果存在
     */
    @Override
    public boolean checkUserName(String userName) {
        return userBaseInfoDao.findByUserName(userName) == null ? false : true;
    }

    /**
     * 校验手机号码是否已经被注册
     *
     * @param phone
     * @return true 如果存在
     */
    @Override
    public boolean checkPhone(Long phone) {
        return userBaseInfoDao.findAllByPhone(phone) == null ? false : true;
    }

    /**
     * 查询用户信息
     *
     * @param id
     * @return
     */
    @Override
    public UserBaseInfoEntity findUserInfoById(Long id) {
        return userBaseInfoDao.findByUserId(id);
    }

    @Override
    public UserBaseInfoEntity updateUserInfo(UserBaseInfoEntity userBaseInfoEntity) {
        return userBaseInfoDao.saveAndFlush(userBaseInfoEntity);
    }
}
