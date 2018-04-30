package cn.ech0.examinationsystem.dao.exam;

import cn.ech0.examinationsystem.entity.exam.QuestionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/29/2018 04:02 PM
 */
public interface QuestionDao  extends JpaRepository<QuestionEntity,Long> ,JpaSpecificationExecutor<QuestionEntity>{


    /**
     * 查出不在列表中的所有题目Id且满足条件
     * @param idList
     * @param categories
     * @return
     */
    @Query("select distinct q.id from QuestionEntity q " +
            "where q.id not in :idList and q.category in :categories")
    List<Long> findAllQuestionsNotInList(@Param("idList") List<Long> idList,@Param("categories") Integer[] categories);


    /**
     * 查出满足条件的所有题目Id
     * @param categories
     * @return
     */
    @Query("select distinct q.id from QuestionEntity q " +
            "where  q.category in :categories")
    List<Long> findAllQuestions(@Param("categories") Integer[] categories);

    /**
     * 查出Id在列表中的所有题目
     * @param idList
     * @return
     */
    @Query("select q from QuestionEntity q " +
            "where  q.id in :idList")
    List<QuestionEntity> findAllQuestionsInList(@Param("idList") List<Long> idList);
}
