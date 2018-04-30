package cn.ech0.examinationsystem.dao.exam;

import cn.ech0.examinationsystem.entity.exam.ExamRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/29/2018 04:16 PM
 */
public interface ExamRecordDao extends JpaRepository<ExamRecordEntity,Long>,JpaSpecificationExecutor<ExamRecordEntity> {



}
