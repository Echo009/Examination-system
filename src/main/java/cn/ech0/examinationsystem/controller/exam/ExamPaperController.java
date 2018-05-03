package cn.ech0.examinationsystem.controller.exam;

import cn.ech0.examinationsystem.constant.ExamConstant;
import cn.ech0.examinationsystem.controller.BaseController;
import cn.ech0.examinationsystem.dto.exam.ExaminationPaperDTO;
import cn.ech0.examinationsystem.response.BaseResponse;
import cn.ech0.examinationsystem.service.exam.IExaminationPaperService;
import cn.ech0.examinationsystem.setting.ExamPreference;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/03/2018 12:51 PM
 */
@RestController
@RequestMapping("/examPaper")
@CrossOrigin(origins = "*")
@Slf4j
public class ExamPaperController extends BaseController {
    @Autowired
    private IExaminationPaperService examinationPaperService;

    /**
     * 返回试卷
     * @param titleNum
     * @param categories
     * @param onlyError
     * @param onlyNew
     * @return
     */
    @RequestMapping("/generate")
    public BaseResponse generateExamPaper(@RequestParam(defaultValue = "8") Integer titleNum,
                                          Integer[] categories,
                                          boolean onlyError,
                                          boolean onlyNew) {

        if (categories == null) {
            categories = ExamConstant.ALL_CATEGORY;
        }
        ExamPreference examPreference = new ExamPreference(onlyError, onlyNew);

        ExaminationPaperDTO examinationPaperDTO = examinationPaperService.genExaminaionPaper(getCurrentUserInfo().getUserId(),categories,titleNum,examPreference);

        return new BaseResponse(true, examinationPaperDTO);

    }

}
