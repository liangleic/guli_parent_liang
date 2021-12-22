package com.atguigu.demo.excel;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Description: ExcelListener$
 * @Author liang
 * @Date: 2021/12/11$ 17:59$
 * @Version 1.0
 */
public class ExcelListener extends AnalysisEventListener<DemoData> {

    //创建list集合封装最终的数据
    List<DemoData> list = new ArrayList<DemoData>();

    //一行一行去读取excle内容
    @Override
    public void invoke(DemoData user, AnalysisContext analysisContext) {
        System.out.println("***"+user);
        list.add(user);
    }

    //读取excel表头信息
    @Override
    public void invokeHeadMap(Map<Integer, String> headMap, AnalysisContext context) {
        System.out.println("表头信息："+headMap);
    }

    //读取完成后执行
    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {
    }
}