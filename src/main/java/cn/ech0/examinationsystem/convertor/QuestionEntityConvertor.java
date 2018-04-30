package cn.ech0.examinationsystem.convertor;

import cn.ech0.examinationsystem.dto.exam.QuestionDTO;
import cn.ech0.examinationsystem.entity.exam.QuestionEntity;
import cn.ech0.examinationsystem.util.GradeUtil;
import org.springframework.beans.BeanUtils;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/30/2018 07:45 AM
 */
public class QuestionEntityConvertor {

    public static QuestionDTO convertoQuestionDTO(QuestionEntity questionEntity){
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(questionEntity, questionDTO);
        if (questionEntity.getUseTimes() == 0) {
            questionDTO.setErrorRate("0.00%");
        }else {
            double errorRate = questionEntity.getErrorTimes()/questionEntity.getUseTimes();
            questionDTO.setErrorRate(GradeUtil.formatRate(errorRate));
        }

        return questionDTO;
    }
}
