package cn.ech0.examinationsystem.service.exam;

import cn.ech0.examinationsystem.entity.exam.QuestionEntity;
import cn.ech0.examinationsystem.wrapper.QuestionWrapper;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/29/2018 04:18 PM
 */
public interface IQuestionService {
    /**
     * 克隆题目
     * @param userId
     * @param userName
     * @param questionId
     * @return
     */
    QuestionEntity clone(String userId, String userName, Long questionId);
    /**
     * 删除题目,逻辑删除
     * @param userId
     * @param questionId
     * @return
     */
    void delete(String userId, Long questionId);

    /**
     * 修改题目
     * @param userId
     * @param questionEntity
     * @return
     */
    QuestionEntity update(String userId, QuestionEntity questionEntity);

    /**
     * 添加题目
     * @param questionEntity
     * @return
     */
    QuestionEntity add(String userId ,String UserName,QuestionEntity questionEntity);

    /**
     * 查找属于该用户的所有题目
     * @param userId
     * @param category
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    QuestionWrapper findQuestionsByUserId(String userId , Integer category, Integer type,Integer pageNum , Integer pageSize);

    /**
     * 给定条件搜索题目
     * @param keyword
     * @param category
     * @param type
     * @param pageNum
     * @param pageSize
     * @return
     */
    QuestionWrapper searchQuestions(String keyword, Integer category, Integer type, Integer pageNum, Integer pageSize);

}
