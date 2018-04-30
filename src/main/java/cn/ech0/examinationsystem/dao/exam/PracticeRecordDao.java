package cn.ech0.examinationsystem.dao.exam;

import cn.ech0.examinationsystem.entity.exam.PracticeRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/29/2018 04:14 PM
 */
public interface PracticeRecordDao extends JpaRepository<PracticeRecordEntity,Long>,JpaSpecificationExecutor<PracticeRecordEntity>{

    /**
     * 查出该用户所有的错题ID
     * @param userId
     * @param categories
     * @return
     */
    @Query("select distinct p.questionId from PracticeRecordEntity p " +
            "where p.userId = :userId and p.category in :categories and p.errorTimes > 0")
    List<Long> findAllErrorQuestionsWithInCategories(@Param("userId") String userId,@Param("categories") Integer[] categories);

    /**
     * 查出该用户练习过的所有的题目ID
     * @param userId
     * @param categories
     * @return
     */
    @Query("select distinct p.questionId from PracticeRecordEntity p " +
            "where p.userId = :userId and p.category in :categories")
    List<Long> findAllPracticedQuestionsWithInCategories(@Param("userId") String userId,@Param("categories") Integer[] categories);


}
