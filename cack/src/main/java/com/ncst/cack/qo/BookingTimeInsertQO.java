package com.ncst.cack.qo;

import lombok.Data;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;

/**
 * @author liuxuelian
 * @date 2021/04/02
 */
@Data
public class BookingTimeInsertQO implements Serializable {
    private static final long serialVersionUID = 1L;

    @NotNull(message = "开始日期不能为空")
    @FutureOrPresent(message = "开始日期必须是现在或未来日期")
    private LocalDate startDate;

    @NotNull(message = "结束日期不能为空")
    @FutureOrPresent(message = "结束日期必须是现在或未来日期")
    private LocalDate endDate;

    @NotNull(message = "开始时间不能为空")
    @Range(min = 6, max = 20, message = "开始时间必须在6到20之间")
    private int startTime;

    @NotNull(message = "结束时间不能为空")
    @Range(min = 7, max = 21, message = "结束时间必须在7到21之间")
    private int endTime;

    @NotEmpty(message = "天数不能为空")
    private List<String> weeklyList;
}
