package cn.ech0.examinationsystem.controller.admin;

import cn.ech0.examinationsystem.controller.BaseController;
import cn.ech0.examinationsystem.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/18/2018 10:08 AM
 */
@RestController
@RequestMapping("/admin/comment")
@CrossOrigin(origins = "*")
@Slf4j
public class CommentController extends BaseController {

    @RequestMapping("/list")
    public BaseResponse list(@RequestParam(required = false, defaultValue = "1") Integer pageNum,
                             @RequestParam(required = false, defaultValue = "8") Integer pageSize
    ) {
// TODO: 05/18/2018
        return new BaseResponse(true, null);

    }



    @RequestMapping("/detail")
    public BaseResponse detail(Long commentId) {
// TODO: 05/18/2018
        return new BaseResponse(true, null);

    }
}
