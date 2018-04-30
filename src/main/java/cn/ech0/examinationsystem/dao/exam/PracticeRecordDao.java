package cn.ech0.examinationsystem.dao.exam;

import cn.ech0.examinationsystem.entity.exam.PracticeRecordEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/29/2018 04:14 PM
 */
public interface PracticeRecordDao extends JpaRepository<PracticeRecordEntity,Long>,JpaSpecificationExecutor<PracticeRecordEntity>{



}
