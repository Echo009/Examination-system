package cn.ech0.examinationsystem.controller.common;

import cn.ech0.examinationsystem.controller.BaseController;
import cn.ech0.examinationsystem.dto.exam.QuestionDTO;
import cn.ech0.examinationsystem.response.BaseResponse;
import cn.ech0.examinationsystem.service.exam.IQuestionService;
import cn.ech0.examinationsystem.wrapper.QuestionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/30/2018 08:17 AM
 */

@RestController
@RequestMapping("/questionInfo")
@CrossOrigin(origins = "*")
@Slf4j
public class QuestionInfoController extends BaseController {
    @Autowired
    private IQuestionService iQuestionService;

    @RequestMapping("/search")
    public BaseResponse search(@RequestParam(required = false) String keyWord,
                               @RequestParam(required = false) Integer type,
                               @RequestParam(required = false) Integer category,
                               @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                               @RequestParam(required = false, defaultValue = "8") Integer pageSize
    ) {

       QuestionWrapper questionWrapper = iQuestionService.searchQuestions(keyWord, category, type, pageNum, pageSize);
        return new BaseResponse(true, questionWrapper);
    }
    @RequestMapping("/find")
    public BaseResponse find(Long questionId) {
        QuestionDTO questionDTO = iQuestionService.findOne(questionId);
        return new BaseResponse(true, questionDTO);
    }

}
