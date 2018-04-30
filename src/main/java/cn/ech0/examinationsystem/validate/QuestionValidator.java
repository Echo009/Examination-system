package cn.ech0.examinationsystem.validate;

import cn.ech0.examinationsystem.constant.QuestionTypeConstant;
import cn.ech0.examinationsystem.dto.ResultDTO;
import cn.ech0.examinationsystem.entity.exam.QuestionEntity;
import cn.ech0.examinationsystem.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/29/2018 09:36 PM
 * <p>
 * 校验问题是否有效，格式是否符合要求
 */
@Slf4j
public class QuestionValidator {

    public static ResultDTO<String> check(QuestionEntity questionEntity) {

        if (questionEntity.getType() == null) {
            return new ResultDTO(false, "题目类型不能为空！");
        }
        if (questionEntity.getAnswer() == null) {
            return new ResultDTO(false, "题目答案不能为空 ！");
        }
        // 仅仅包含图片标题的
        if (questionEntity.getOnlyImgTitle()) {
            if (questionEntity.getTitleImg() == null) {
                return new ResultDTO(false, "仅含图片标题时，标题图片不能为空！");
            }
        }
        ResultDTO<String> resultDTO1;
        ResultDTO<String> resultDTO2;
        switch (questionEntity.getType()) {
            // 单选，选项不能为空
            case QuestionTypeConstant.SINGLE_SELECT:
                resultDTO1 = ChoicesValidtor.check(questionEntity);
                if (!resultDTO1.isSuccess()) {
                    return resultDTO1;
                }
                resultDTO2 = ChoiceAnswerValidtor.check(questionEntity.getAnswer(), true);
                if (!resultDTO2.isSuccess()) {
                    return resultDTO2;
                } else {
                    questionEntity.setAnswer(resultDTO2.getData());
                }
                break;
            // 多选
            case QuestionTypeConstant.MULTI_SELECT:
                resultDTO1 = ChoicesValidtor.check(questionEntity);
                if (!resultDTO1.isSuccess()) {
                    return resultDTO1;
                }
                resultDTO2 = ChoiceAnswerValidtor.check(questionEntity.getAnswer(), false);
                if (!resultDTO2.isSuccess()) {
                    return resultDTO2;
                } else {
                    questionEntity.setAnswer(resultDTO2.getData());
                }
                break;
            // 填空
            case QuestionTypeConstant.GAP_FILLING:
                int gapCount = 0;
                ResultDTO resultDTO= GapFillingTitleValidetor.check(questionEntity.getTitle());
                if (!resultDTO.isSuccess()) {
                    return resultDTO;
                }
                else {
                    gapCount = (int)resultDTO.getData();
                }
                try {
                    String answer[] = JsonUtil.toBean(questionEntity.getAnswer(), String[].class);
                    if (answer.length != gapCount) {
                        log.warn("【校验试题有效性】填空题答案与空数量不一致！");
                        return new ResultDTO<>(false, "填空题答案与空数量不一致!");
                    }
                    // 格式化保存
                    questionEntity.setAnswer(JsonUtil.toJson(answer,false));
                } catch (Exception e) {
                    return new ResultDTO<>(false, "填空题答案格式有误！请使用String数字的json形式！");
                }
                break;
            // 问答 以及 代码题
            default:
                if (StringUtils.isEmpty(questionEntity.getTitle()) ||
                        StringUtils.isEmpty(questionEntity.getAnswer())) {
                    return new ResultDTO<>(false, "问答题（编程题）题目和答案不能为空！");
                }

        }
        return new ResultDTO(true, questionEntity);
    }


}
