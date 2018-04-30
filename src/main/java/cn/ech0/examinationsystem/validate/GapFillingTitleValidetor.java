package cn.ech0.examinationsystem.validate;

import cn.ech0.examinationsystem.constant.Constant;
import cn.ech0.examinationsystem.dto.ResultDTO;
import lombok.extern.slf4j.Slf4j;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/30/2018 06:10 AM
 */
@Slf4j
public class GapFillingTitleValidetor {

    public static final Pattern pattern = Pattern.compile(Constant.GAP_FILLING_BLANK);

    /**
     *
     * @param title
     * @return
     */
    public static ResultDTO check(String title) {

        Matcher matcher = pattern.matcher(title);
        int matchNum = 0;
        while (matcher.find()) {
            matchNum++;
        }
        if (matchNum != 0) {
            return new ResultDTO(true, matchNum);
        } else {
            return new ResultDTO(false, "填空题至少要有一个空！");
        }

    }
}
