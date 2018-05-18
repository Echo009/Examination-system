package cn.ech0.examinationsystem.controller.admin;

import cn.ech0.examinationsystem.controller.BaseController;
import cn.ech0.examinationsystem.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/18/2018 10:05 AM
 */

@RestController
@RequestMapping("/admin/question")
@CrossOrigin(origins = "*")
@Slf4j
public class AdminQuestionController extends BaseController{



    @RequestMapping("/audit")
    public BaseResponse audit(
            Long questionId , Long commentId) {
// TODO: 05/18/2018
        return new BaseResponse(true, null);

    }

}
