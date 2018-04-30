package cn.ech0.examinationsystem.question;

import cn.ech0.examinationsystem.util.JsonUtil;
import org.junit.Test;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/30/2018 12:25 AM
 */
public class AnswerTest {

    private String demoChoiceAnswer = "[\"a\",\"b\"]";


    @Test
    public void choiceAnswer(){
        String[] choices = {"a","b"};
        System.out.println(choices);
        System.out.println(JsonUtil.toJson(choices,false));
        System.out.println(JsonUtil.toBean(demoChoiceAnswer,String[].class));
    }
}
