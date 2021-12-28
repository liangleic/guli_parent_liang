package com.atguigu.eduservice.entity.chapter;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: ChapterVo$
 * @Author liang
 * @Date: 2021/12/26$ 12:00$
 * @Version 1.0
 */
@Data
public class ChapterVo {
    private String id;
    private String title;
    private List<VideoVo> children = new ArrayList<>();
}
