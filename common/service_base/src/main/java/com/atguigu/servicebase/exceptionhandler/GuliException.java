package com.atguigu.servicebase.exceptionhandler;

/**
 * @Description: GuliException$
 * @Author liang
 * @Date: 2021/10/24$ 18:57$
 * @Version 1.0
 */

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GuliException extends RuntimeException {
    @ApiModelProperty(value = "状态码")
    private Integer code;

    private String msg;

}
