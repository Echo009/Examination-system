package cn.ech0.examinationsystem.dao;

import cn.ech0.examinationsystem.dao.exam.PracticeRecordDao;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/30/2018 10:37 PM
 */

@RunWith(SpringRunner.class)
@SpringBootTest
public class PracticeRecordDaoTest {
    @Autowired
    private PracticeRecordDao practiceRecordDao;
    @Test
    public void testFindErrorQuestions(){
        System.out.println(practiceRecordDao.findAllErrorQuestions("1"));
    }

}
