package cn.ech0.examinationsystem.controller.user;

import cn.ech0.examinationsystem.controller.BaseController;
import cn.ech0.examinationsystem.convertor.QuestionEntityConvertor;
import cn.ech0.examinationsystem.dto.exam.QuestionDTO;
import cn.ech0.examinationsystem.entity.exam.QuestionEntity;
import cn.ech0.examinationsystem.form.question.QuestionForm;
import cn.ech0.examinationsystem.response.BaseResponse;
import cn.ech0.examinationsystem.service.exam.IQuestionService;
import cn.ech0.examinationsystem.wrapper.QuestionWrapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/30/2018 08:26 AM
 */
@RestController
@RequestMapping("/question")
@CrossOrigin(origins = "*")
@Slf4j
public class QuestionController extends BaseController {

    @Autowired
    private IQuestionService iQuestionService;


    @RequestMapping("/errorQuestions")
    public BaseResponse errorQuestions( @RequestParam(required = false, defaultValue = "1") Integer pageNum,
                                        @RequestParam(required = false, defaultValue = "8") Integer pageSize) {
// TODO: 05/18/2018
        return BaseResponse.SUCCESS;
    }

    @RequestMapping("/delete")
    public BaseResponse delete(Long questionId) {
        iQuestionService.delete(getCurrentUserInfo().getUserId(), questionId);
        return BaseResponse.SUCCESS;
    }

    @RequestMapping("/clone")
    public BaseResponse clone(Long questionId) {
        QuestionDTO questionDTO = iQuestionService.clone(getCurrentUserInfo().getUserId(), getCurrentUserInfo().getUserName(), questionId);
        return new BaseResponse(true, questionDTO);
    }

    @RequestMapping("/find")
    public BaseResponse find(
            @RequestParam(required = false) Integer type,
            @RequestParam(required = false) Integer category,
            @RequestParam(required = false, defaultValue = "1") Integer pageNum,
            @RequestParam(required = false, defaultValue = "8") Integer pageSize) {

        QuestionWrapper questionWrapper = iQuestionService.findQuestionsByUserId(getCurrentUserInfo().getUserId(), category, type, pageNum, pageSize);
        return new BaseResponse(true, questionWrapper);
    }

    @RequestMapping("/add")
    public BaseResponse add(QuestionForm questionForm) {
        QuestionEntity questionEntity = new QuestionEntity();
        BeanUtils.copyProperties(questionForm, questionEntity);
        QuestionDTO questionDTO = iQuestionService.add(
                getCurrentUserInfo().getUserId(), getCurrentUserInfo().getUserName(),
                questionEntity);
        return new BaseResponse(true, questionDTO);

    }

    @RequestMapping("/update")
    public BaseResponse update(QuestionForm questionForm) {


        if (questionForm.getId() == null) {
            return new BaseResponse(false, "题目Id不能为空 ! ");
        }
        QuestionEntity questionEntity = new QuestionEntity();
        BeanUtils.copyProperties(questionForm, questionEntity);
        QuestionDTO questionDTO= iQuestionService.update(getCurrentUserInfo().getUserId(), questionEntity);
        return new BaseResponse(true, questionDTO);

    }


}
