package cn.ech0.examinationsystem.setting;

import lombok.Data;

/**
 * @author: Ech0
 * Email  : ech0.extreme@foxmail.com
 * Time   : 04/30/2018 04:09 PM
 */
@Data
public class ExamPreference {
    public ExamPreference(boolean onlyError, boolean onlyNew) {
        this.onlyError = onlyError;
        this.onlyNew = onlyNew;
    }

    /**
     * 仅含错题
     */
    private boolean onlyError;
    /**
     * 仅含新题
     */
    private boolean onlyNew;


}
