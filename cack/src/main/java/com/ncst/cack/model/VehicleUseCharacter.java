package com.ncst.cack.model;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author liuxuelian
 * @date 2021/04/16
 */
@Data
public class VehicleUseCharacter implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    @NotNull(message = "使用性质名称不能为空")
    @Length(max = 10, message = "类型名称必须小于10个字符")
    private String name;
}
