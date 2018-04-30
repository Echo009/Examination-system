package cn.ech0.examinationsystem.convertor;

import cn.ech0.examinationsystem.dto.exam.ExaminationPaperDTO;
import cn.ech0.examinationsystem.entity.exam.ExaminationPaperEntity;
import org.springframework.beans.BeanUtils;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/01/2018 12:07 AM
 */
public class ExaminationPaperEntityConvertor {

    public static ExaminationPaperDTO toExaminationPaperDTO(ExaminationPaperEntity examinationPaperEntity) {
        ExaminationPaperDTO examinationPaperDTO = new ExaminationPaperDTO();
        BeanUtils.copyProperties(examinationPaperEntity, examinationPaperDTO);
        return examinationPaperDTO;

    }
}
