package cn.ech0.examinationsystem.controller.user;

import cn.ech0.examinationsystem.controller.BaseController;
import cn.ech0.examinationsystem.response.BaseResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 05/18/2018 09:48 AM
 */
@RestController
@RequestMapping("/favorite")
@CrossOrigin(origins = "*")
@Slf4j
public class FavoriteController extends BaseController {

    @RequestMapping("/add")
    public BaseResponse add(Long questionId) {
// TODO: 05/18/2018  
        return BaseResponse.SUCCESS;
    }

    @RequestMapping("/remove")
    public BaseResponse remove(Long questionId) {
// TODO: 05/18/2018
        return new BaseResponse(true, null);
    }
}
