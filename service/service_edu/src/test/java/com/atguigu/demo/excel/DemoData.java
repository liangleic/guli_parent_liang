package com.atguigu.demo.excel;

import com.alibaba.excel.annotation.ExcelProperty;
import lombok.Data;

/**
 * @Description: DemoData$
 * @Author liang
 * @Date: 2021/12/11$ 17:15$
 * @Version 1.0
 */
@Data
public class DemoData {
    //设置表头名称
    @ExcelProperty(value = "学生编号",index = 0)
    private Integer sno;

    @ExcelProperty(value = "学生姓名",index = 1)
    private String sname;
}
