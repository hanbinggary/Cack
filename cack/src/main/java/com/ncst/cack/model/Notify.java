package com.ncst.cack.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author liuxuelian
 * @date 2021/04/11
 */
@Data
public class Notify implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotBlank(message = "标题不能为空")
    @Length(max = 30, message = "标题必须小于30个字符")
    private String title;

    @NotBlank(message = "内容不能为空")
    @Length(max = 255, message = "内容必须小于255个字符")
    private String content;

    private int type = 0;

    @NotNull(message = "发布者ID不能为空")
    private Long adminId;
}
