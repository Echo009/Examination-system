package cn.ech0.examinationsystem.question;

import cn.ech0.examinationsystem.structure.Choice;
import cn.ech0.examinationsystem.util.JsonUtil;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import sun.misc.Regexp;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/29/2018 10:09 PM
 * <p>
 * 题目数据格式测试
 */

@Slf4j
public class QuestionStructureTest {

    String demoChoices = "{" +
            "\"A\":\"this is a.\"," +
            "\"B\":\"this is b.\"," +
            "\"C\":\"this is c.\"," +
            "\"D\":\"this is d.\"," +
            "\"E\":\"this is e.\"" +
            "}";
    String badChoices = "{" +
            "\"A\":\"this is a.\"," +
            "\"B\":\"this is b.\"," +
            "\"C\":\"this is c.\"," +
            ":\"this is d.\"," +

            "}";

    String demoGapFilling = "最帅的人是_blank_？？？那么问题来了，最美的人是_blank_ ？ emmmmmm 。。。";


    @Test
    public void testGapFilling (){
        String pattern = "_blank_";
        Pattern r = Pattern.compile(pattern);
        Matcher matcher = r.matcher(demoGapFilling);
        int matchNum = 0;
        while (matcher.find()) {
            matchNum++;
        }
        System.out.println(matchNum);

    }

    @Test
    public void genDemoChoice() {

        Choice choice = new Choice();
        choice.setA("this is a.");
        choice.setB("this is b.");
        choice.setC("this is c.");
        choice.setD("this is d.");
        choice.setE("this is e.");
        log.info(JsonUtil.toJson(choice, false));


    }
    @Test
    public void validChoice(){
        log.info(JsonUtil.toBean(demoChoices, Choice.class).toString());

        try {
            log.info(JsonUtil.toBean(badChoices, Choice.class).toString());
        } catch (Exception e) {
            log.error("Invalid Choices");
        }
    }

}
