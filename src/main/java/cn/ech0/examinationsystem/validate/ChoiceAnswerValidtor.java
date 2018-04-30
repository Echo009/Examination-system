package cn.ech0.examinationsystem.validate;

import cn.ech0.examinationsystem.dto.ResultDTO;
import cn.ech0.examinationsystem.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.Arrays;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/30/2018 12:28 AM
 */
@Slf4j
public class ChoiceAnswerValidtor {
    /**
     * @param choiceAnswer
     * @param isSingle
     * @return 排序后的正确答案
     */
    public static ResultDTO<String> check(String choiceAnswer,boolean isSingle) {
        String[] answer;
        try {
            answer  = JsonUtil.toBean(choiceAnswer, String[].class);
            for (int i = 0; i < answer.length; i++) {
                // 转换成小写
                answer[i]= answer[i].toLowerCase();
                // a - f 之间
                if (answer[i].length() != 1 || answer[i].charAt(0) < 'a' || answer[i].charAt(0) > 'f') {
                    return new ResultDTO(false, "正确答案选项格式不对，请用字母a~f表示!");
                }
            }
        } catch (Exception e) {
            return new ResultDTO(false, "正确答案选项格式不对，需要为数组的json格式！");
        }
        if (isSingle && answer.length != 1) {
            return new ResultDTO(false, "正确答案选项格式不对，单选题正确答案应只有一个!");
        }
        // 排序
        Arrays.sort(answer);
        // 转换成json串
        choiceAnswer = JsonUtil.toJson(answer, false);
        log.info("【校验选择题答案格式】 it is valid ! ，result is {} ",choiceAnswer);
        return new ResultDTO(true, choiceAnswer);
    }
}
