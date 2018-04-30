package cn.ech0.examinationsystem.validate;

import cn.ech0.examinationsystem.dto.ResultDTO;
import cn.ech0.examinationsystem.entity.exam.QuestionEntity;
import cn.ech0.examinationsystem.structure.Choice;
import cn.ech0.examinationsystem.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/30/2018 05:50 AM
 */
@Slf4j
public class ChoicesValidtor {

    public static ResultDTO<String> check(QuestionEntity questionEntity) {
        if (StringUtils.isEmpty(questionEntity.getChoices())) {
            return new ResultDTO(false, "选择题，选项不能为空！");
        }
        try {
            JsonUtil.toBean(questionEntity.getChoices(), Choice.class);
        } catch (Exception e) {
            log.warn("【校验试题有效性】选项格式错误：{}",questionEntity.getChoices());
            return new ResultDTO(false,"选项格式有误！, 需为 Json 格式");
        }
        return new ResultDTO<>(true, null);
    }
}
