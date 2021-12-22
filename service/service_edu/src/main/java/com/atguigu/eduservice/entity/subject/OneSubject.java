package com.atguigu.eduservice.entity.subject;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: OneSubject$
 * @Author liang
 * @Date: 2021/12/15$ 22:53$
 * @Version 1.0
 */

@Data
public class OneSubject {
    private String id;

    private String title;

    //一个一级分类下面有多个二级分类
    private List<TwoSubject> children = new ArrayList<>();
}
